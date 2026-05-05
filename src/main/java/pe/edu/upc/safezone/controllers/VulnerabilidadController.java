package pe.edu.upc.safezone.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.safezone.dtos.UsuarioGeneralDTO;
import pe.edu.upc.safezone.dtos.VulnerabilidadAlertaDTO;
import pe.edu.upc.safezone.dtos.VulnerabilidadDTO;
import pe.edu.upc.safezone.entities.Actividad;
import pe.edu.upc.safezone.entities.Usuario;
import pe.edu.upc.safezone.entities.Vulnerabilidad;
import pe.edu.upc.safezone.serviceinterfaces.IActividadService;
import pe.edu.upc.safezone.serviceinterfaces.IVulnerabilidadService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Vulnerabilidad")
public class VulnerabilidadController {
    @Autowired
    private IVulnerabilidadService Vu;


    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','SECURITY_ANALYST')")
    public ResponseEntity<List<VulnerabilidadDTO>> ListarVulnerabilidades() {
        ModelMapper m = new ModelMapper();
        List<VulnerabilidadDTO>listaVul=Vu.ListarVulnerabilidad().stream()
                .map(y->m.map(y,VulnerabilidadDTO.class))
                .collect(Collectors.toList());
        return  ResponseEntity.ok().body(listaVul);
    }

    @PostMapping("/registrar")
    @PreAuthorize("hasAnyAuthority('ADMIN','SECURITY_ANALYST')")
    public ResponseEntity<?> registrar(@RequestBody VulnerabilidadDTO dto){
        if (dto.getDateVulnerabilidad().isAfter(java.time.LocalDateTime.now())) {
            return ResponseEntity.badRequest()
                    .body("La fecha de registro del usuario no es valida");
        }
        ModelMapper m=new ModelMapper();
        Vulnerabilidad c=m.map(dto, Vulnerabilidad.class);
        Vulnerabilidad cur= Vu.insert(c);
        VulnerabilidadDTO responseDTO=m.map(cur,VulnerabilidadDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }

    @PutMapping("/modificar")
    @PreAuthorize("hasAnyAuthority('ADMIN','SECURITY_ANALYST')")
    public ResponseEntity<String> actualizar(@RequestBody VulnerabilidadDTO dto) {

        Optional<Vulnerabilidad> existente = Vu.listId(dto.getIdActividad());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Vulnerabilidad no encontrado");
        }

        Vulnerabilidad ar = existente.get();

        ar.setNameVulnerabilidad(dto.getNameVulnerabilidad());
        ar.setDescriptionVulnerabilidad(dto.getDescriptionVulnerabilidad());
        ar.setTypeVulnerabilidad(dto.getTypeVulnerabilidad());
        ar.setRiskLevelVulnerabilidad(dto.getRiskLevelVulnerabilidad());
        ar.setDateVulnerabilidad(dto.getDateVulnerabilidad());
        Vu.update(ar);
        return ResponseEntity.ok("Vulnerabilidad actualizado correctamente");

    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Vulnerabilidad> vulnerabilidad  = Vu.listId(id);
        if (vulnerabilidad.isPresent()) {
            Vu.delete(id);
            return ResponseEntity.ok("Vulnerabilidad eliminada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Vulnerabilidad no encontrada");
        }

    }

    @GetMapping("/reporte-alertas")
    @PreAuthorize("hasAnyAuthority('ADMIN','SECURITY_ANALYST')")
    public ResponseEntity<List<VulnerabilidadAlertaDTO>> reporteAlertas() {
        List<VulnerabilidadAlertaDTO> resultado = Vu.obtenerVulnerabilidadesConTotalAlertas()
                .stream()
                .map(row -> {
                    VulnerabilidadAlertaDTO dto = new VulnerabilidadAlertaDTO();
                    dto.setIdVulnerabilidad(((Number) row[0]).intValue());
                    dto.setNameVulnerabilidad((String) row[1]);
                    dto.setRiskLevelVulnerabilidad((String) row[2]);
                    dto.setTypeVulnerabilidad((String) row[3]);
                    dto.setTotalAlertas(((Number) row[4]).intValue());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultado);
    }
}
