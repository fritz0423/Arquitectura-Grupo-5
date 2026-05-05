package pe.edu.upc.safezone.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.safezone.dtos.ActividadDTO;
import pe.edu.upc.safezone.dtos.UsuarioGeneralDTO;
import pe.edu.upc.safezone.entities.Actividad;
import pe.edu.upc.safezone.entities.Usuario;
import pe.edu.upc.safezone.serviceinterfaces.IActividadService;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/actividad")
public class ActividadController {

    @Autowired
    private IActividadService As;

    @GetMapping
    public ResponseEntity<List<ActividadDTO>> ListarActividades() {
        ModelMapper m = new ModelMapper();
        List<ActividadDTO>listaRol=As.ListarActividades().stream()
                .map(y->m.map(y,ActividadDTO.class))
                .collect(Collectors.toList());
        return  ResponseEntity.ok().body(listaRol);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody ActividadDTO dto){
        if (dto.getDateActividad().isAfter(java.time.LocalDate.now())) {
            return ResponseEntity.badRequest()
                    .body("La fecha de registro de la actividad no es valida");
        }
        ModelMapper m=new ModelMapper();
        Actividad c=m.map(dto, Actividad.class);
        Actividad cur= As.insert(c);
        ActividadDTO responseDTO=m.map(cur,ActividadDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }

    @PutMapping("/modificar")

    public ResponseEntity<String> actualizar(@RequestBody ActividadDTO dto) {

        Optional<Actividad> existente = As.listId(dto.getIdActividad());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Actividad no encontrado");
        }

        Actividad ar = existente.get();

        ar.setIpActividad(dto.getIpActividad());
        ar.setActionTypeActividad(dto.getActionTypeActividad());
        ar.setDescriptionActividad(dto.getDescriptionActividad());
        ar.setDateActividad(dto.getDateActividad());
        As.update(ar);
        return ResponseEntity.ok("Actvidad actualizado correctamente");

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Actividad> actividad = As.listId(id);
        if (actividad.isPresent()) {
            As.delete(id);
            return ResponseEntity.ok("Rol eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Rol no encontrado");
        }

    }
    // SIMPLE 1: Actividades por tipo de acción
    @GetMapping("/tipo/{actionType}")
    public ResponseEntity<?> listarPorTipoAccion(@PathVariable String actionType) {
        List<Actividad> lista = As.listarPorTipoAccion(actionType);
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron actividades del tipo: " + actionType);
        }
        ModelMapper m = new ModelMapper();
        List<ActividadDTO> listaDTO = lista.stream()
                .map(a -> {
                    ActividadDTO dto = m.map(a, ActividadDTO.class);
                    dto.setIdUsuario(a.getUsuario().getIdUsuario());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }

    // SIMPLE 2: Actividades por IP
    @GetMapping("/ip/{ip}")
    public ResponseEntity<?> listarPorIp(@PathVariable String ip) {
        List<Actividad> lista = As.listarPorIp(ip);
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron actividades para la IP: " + ip);
        }
        ModelMapper m = new ModelMapper();
        List<ActividadDTO> listaDTO = lista.stream()
                .map(a -> {
                    ActividadDTO dto = m.map(a, ActividadDTO.class);
                    dto.setIdUsuario(a.getUsuario().getIdUsuario());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }


    @GetMapping("/usuario/{idUsuario}/rango")
    public ResponseEntity<?> listarPorUsuarioYRangoFechas(
            @PathVariable int idUsuario,
            @RequestParam LocalDate fechaInicio,
            @RequestParam LocalDate fechaFin) {

        List<Actividad> lista = As.listarPorUsuarioYRangoFechas(idUsuario, fechaInicio, fechaFin);

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron actividades para el usuario en ese rango de fechas");
        }

        ModelMapper m = new ModelMapper();
        List<ActividadDTO> listaDTO = lista.stream()
                .map(a -> {
                    ActividadDTO dto = m.map(a, ActividadDTO.class);
                    dto.setIdUsuario(a.getUsuario().getIdUsuario());
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(listaDTO);
    }

    // FILTRO 1: Actividades de usuarios activos
    @GetMapping("/usuarios-activos")
    public ResponseEntity<?> listarDeUsuariosActivos() {
        List<Actividad> lista = As.listarDeUsuariosActivos();
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron actividades de usuarios activos");
        }
        ModelMapper m = new ModelMapper();
        List<ActividadDTO> listaDTO = lista.stream()
                .map(a -> {
                    ActividadDTO dto = m.map(a, ActividadDTO.class);
                    dto.setIdUsuario(a.getUsuario().getIdUsuario());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }

    // FILTRO 2: Actividades que generaron vulnerabilidades por nivel de riesgo
    @GetMapping("/riesgo/{riskLevel}")
    public ResponseEntity<?> listarPorNivelRiesgo(@PathVariable String riskLevel) {
        List<Actividad> lista = As.listarPorNivelRiesgoVulnerabilidad(riskLevel);
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron actividades con nivel de riesgo: " + riskLevel);
        }
        ModelMapper m = new ModelMapper();
        List<ActividadDTO> listaDTO = lista.stream()
                .map(a -> {
                    ActividadDTO dto = m.map(a, ActividadDTO.class);
                    dto.setIdUsuario(a.getUsuario().getIdUsuario());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }

}
