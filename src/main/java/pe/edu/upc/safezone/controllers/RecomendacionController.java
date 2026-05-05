package pe.edu.upc.safezone.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.safezone.dtos.RecomendacionDTO;
import pe.edu.upc.safezone.dtos.RecomendacionPrioridadDTO;
import pe.edu.upc.safezone.entities.Recomendacion;
import pe.edu.upc.safezone.entities.Reporte;
import pe.edu.upc.safezone.serviceinterfaces.IRecomendacionService;
import pe.edu.upc.safezone.serviceinterfaces.IReporteService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recomendacion")
public class RecomendacionController {

    @Autowired
    private IRecomendacionService rcS;

    @Autowired
    private IReporteService rS;

    @GetMapping
    public ResponseEntity<List<RecomendacionDTO>> listarRecomendaciones() {
        ModelMapper m = new ModelMapper();
        List<RecomendacionDTO> lista = rcS.listarRecomendaciones().stream()
                .map(rc -> {
                    RecomendacionDTO dto = m.map(rc, RecomendacionDTO.class);
                    dto.setIdReporte(rc.getReporte().getIdReporte());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody RecomendacionDTO dto) {
        if (dto.getDateRecomendacion().isAfter(java.time.LocalDateTime.now())) {
            return ResponseEntity.badRequest()
                    .body("La fecha de la recomendación no es válida");
        }

        Optional<Reporte> reporte = rS.listId(dto.getIdReporte());
        if (reporte.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Reporte no encontrado");
        }

        Recomendacion rc = new Recomendacion();
        rc.setReporte(reporte.get());
        rc.setDescriptionRecomendacion(dto.getDescriptionRecomendacion());
        rc.setPriorityRecomendacion(dto.getPriorityRecomendacion());
        rc.setDateRecomendacion(dto.getDateRecomendacion());

        Recomendacion saved = rcS.insert(rc);

        ModelMapper m = new ModelMapper();
        RecomendacionDTO responseDTO = m.map(saved, RecomendacionDTO.class);
        responseDTO.setIdReporte(saved.getReporte().getIdReporte());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/modificar")
    public ResponseEntity<String> actualizar(@RequestBody RecomendacionDTO dto) {
        if (dto.getDateRecomendacion().isAfter(java.time.LocalDateTime.now())) {
            return ResponseEntity.badRequest()
                    .body("La fecha de la recomendación no es válida");
        }

        Optional<Recomendacion> existente = rcS.listId(dto.getIdRecomendacion());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Recomendación no encontrada");
        }

        Optional<Reporte> reporte = rS.listId(dto.getIdReporte());
        if (reporte.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Reporte no encontrado");
        }

        Recomendacion rc = existente.get();
        rc.setReporte(reporte.get());
        rc.setDescriptionRecomendacion(dto.getDescriptionRecomendacion());
        rc.setPriorityRecomendacion(dto.getPriorityRecomendacion());
        rc.setDateRecomendacion(dto.getDateRecomendacion());

        rcS.update(rc);
        return ResponseEntity.ok("Recomendación actualizada correctamente");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Recomendacion> recomendacion = rcS.listId(id);
        if (recomendacion.isPresent()) {
            rcS.delete(id);
            return ResponseEntity.ok("Recomendación eliminada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Recomendación no encontrada");
        }
    }

    @GetMapping("/buscarporprioridad/{prioridad}")
    public ResponseEntity<?> buscarPorPrioridad(@PathVariable String prioridad) {
        List<Recomendacion> lista = rcS.findByPriorityRecomendacionContainingIgnoreCase(prioridad);
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay recomendaciones con prioridad: " + prioridad);
        }
        ModelMapper m = new ModelMapper();
        List<RecomendacionDTO> listaDTO = lista.stream()
                .map(rc -> {
                    RecomendacionDTO dto = m.map(rc, RecomendacionDTO.class);
                    dto.setIdReporte(rc.getReporte().getIdReporte());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/prioridades")
    public ResponseEntity<?> obtenerCantidadPorPrioridad() {
        List<String[]> lista = rcS.countByPriority();

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron recomendaciones para generar el reporte de decisión.");
        }

        List<RecomendacionPrioridadDTO> listaDTO = new java.util.ArrayList<>();

        for (String[] columna : lista){
            RecomendacionPrioridadDTO dto = new RecomendacionPrioridadDTO();
            dto.setPriority(columna[0]);
            dto.setRecomendationCount(Integer.parseInt(columna[1]));
            listaDTO.add(dto);
        }
        return ResponseEntity.ok(listaDTO);
    }
}