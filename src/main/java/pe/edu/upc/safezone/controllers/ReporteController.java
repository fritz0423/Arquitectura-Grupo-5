package pe.edu.upc.safezone.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.safezone.dtos.ReporteDTO;
import pe.edu.upc.safezone.dtos.ReporteTipoVulnerabilidadDTO;
import pe.edu.upc.safezone.dtos.UsuarioReporteDTO;
import pe.edu.upc.safezone.entities.Reporte;
import pe.edu.upc.safezone.entities.Vulnerabilidad;
import pe.edu.upc.safezone.serviceinterfaces.IReporteService;
import pe.edu.upc.safezone.serviceinterfaces.IVulnerabilidadService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reporte")
public class    ReporteController {

    @Autowired
    private IReporteService rS;

    @Autowired
    private IVulnerabilidadService vS;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','SECURITY_ANALYST')")
    public ResponseEntity<List<ReporteDTO>> listarReportes() {
        ModelMapper m = new ModelMapper();
        List<ReporteDTO> lista = rS.listarReportes().stream()
                .map(re -> {
                    ReporteDTO dto = m.map(re, ReporteDTO.class);
                    dto.setIdVulnerabilidad(re.getVulnerabilidad().getIdVulnerabilidad());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/registrar")
    @PreAuthorize("hasAnyAuthority('ADMIN','SECURITY_ANALYST')")
    public ResponseEntity<?> registrar(@RequestBody ReporteDTO dto) {
        if (dto.getDateReporte().isAfter(java.time.LocalDateTime.now())) {
            return ResponseEntity.badRequest()
                    .body("La fecha del reporte no es válida");
        }

        Optional<Vulnerabilidad> vulnerabilidad = vS.listId(dto.getIdVulnerabilidad());
        if (vulnerabilidad.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Vulnerabilidad no encontrada");
        }

        Reporte re = new Reporte();
        re.setVulnerabilidad(vulnerabilidad.get());
        re.setTypeReporte(dto.getTypeReporte());
        re.setDescriptionReporte(dto.getDescriptionReporte());
        re.setContentReporte(dto.getContentReporte());
        re.setActionTypeReporte(dto.getActionTypeReporte());
        re.setResultReporte(dto.getResultReporte());
        re.setDateReporte(dto.getDateReporte());

        Reporte saved = rS.insert(re);

        ModelMapper m = new ModelMapper();
        ReporteDTO responseDTO = m.map(saved, ReporteDTO.class);
        responseDTO.setIdVulnerabilidad(saved.getVulnerabilidad().getIdVulnerabilidad());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/modificar")
    @PreAuthorize("hasAnyAuthority('ADMIN','SECURITY_ANALYST')")
    public ResponseEntity<String> actualizar(@RequestBody ReporteDTO dto) {
        if (dto.getDateReporte().isAfter(java.time.LocalDateTime.now())) {
            return ResponseEntity.badRequest()
                    .body("La fecha del reporte no es válida");
        }

        Optional<Reporte> existente = rS.listId(dto.getIdReporte());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Reporte no encontrado");
        }

        Optional<Vulnerabilidad> vulnerabilidad = vS.listId(dto.getIdVulnerabilidad());
        if (vulnerabilidad.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Vulnerabilidad no encontrada");
        }

        Reporte re = existente.get();
        re.setVulnerabilidad(vulnerabilidad.get());
        re.setTypeReporte(dto.getTypeReporte());
        re.setDescriptionReporte(dto.getDescriptionReporte());
        re.setContentReporte(dto.getContentReporte());
        re.setActionTypeReporte(dto.getActionTypeReporte());
        re.setResultReporte(dto.getResultReporte());
        re.setDateReporte(dto.getDateReporte());

        rS.update(re);
        return ResponseEntity.ok("Reporte actualizado correctamente");
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Reporte> reporte = rS.listId(id);
        if (reporte.isPresent()) {
            rS.delete(id);
            return ResponseEntity.ok("Reporte eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Reporte no encontrado");
        }
    }

    // --- QUERY SIMPLE 1: Filtro por Tipo ---
    @GetMapping("/buscarportipo/{tipo}")
    @PreAuthorize("hasAnyAuthority('ADMIN','SECURITY_ANALYST')")
    public ResponseEntity<?> buscarPorTipo(@PathVariable String tipo) {
        List<Reporte> lista = rS.findByTypeReporteContainingIgnoreCase(tipo);
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron reportes del tipo: " + tipo);
        }
        ModelMapper m = new ModelMapper();
        List<ReporteDTO> listaDTO = lista.stream()
                .map(re -> {
                    ReporteDTO dto = m.map(re, ReporteDTO.class);
                    dto.setIdVulnerabilidad(re.getVulnerabilidad().getIdVulnerabilidad());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/vulnerabilidades-frecuentes")
    @PreAuthorize("hasAnyAuthority('ADMIN','SECURITY_ANALYST')")
    public ResponseEntity<?> obtenerCantidadPorVulnerabilidad(){
        List<String[]> lista = rS.countReportsByVulnerabilityType();

        if (lista.isEmpty()) {
            return ResponseEntity.status((HttpStatus.NOT_FOUND))
                    .body("No hay reportes ni vulnerabilidades registradas para este análisis");
        }

        List<ReporteTipoVulnerabilidadDTO> listaDTO = new java.util.ArrayList<>();
        for (String[] columna : lista) {
            ReporteTipoVulnerabilidadDTO dto = new ReporteTipoVulnerabilidadDTO();
            dto.setVulnerabilityType(columna[0]);
            dto.setReportCount(Integer.parseInt(columna[1]));
            listaDTO.add(dto);
        }
        return ResponseEntity.ok(listaDTO);

    }

    @GetMapping("/reportes-por-usuario")
    @PreAuthorize("hasAnyAuthority('ADMIN','SECURITY_ANALYST')")
    public ResponseEntity<?> obtenerReportesPorUsuario() {
        List<String[]> lista = rS.countReportsByUser();

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay datos de actividad para generar el reporte por usuario.");
        }

        List<UsuarioReporteDTO> listaDTO = new java.util.ArrayList<>();
        for (String[] col : lista) {
            UsuarioReporteDTO dto = new UsuarioReporteDTO();
            dto.setNameUsuario(col[0]);
            dto.setReportCount(Integer.parseInt(col[1]));
            listaDTO.add(dto);
        }
        return ResponseEntity.ok(listaDTO);
    }


}