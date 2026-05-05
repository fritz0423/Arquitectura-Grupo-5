package pe.edu.upc.safezone.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.safezone.dtos.AlertaDTO;
import pe.edu.upc.safezone.entities.Alerta;
import pe.edu.upc.safezone.entities.Vulnerabilidad;
import pe.edu.upc.safezone.serviceinterfaces.IAlertaService;
import pe.edu.upc.safezone.serviceinterfaces.IVulnerabilidadService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alerta")
public class AlertaController {

    @Autowired
    private IAlertaService alS;

    @Autowired
    private IVulnerabilidadService vS;

    @GetMapping
    public ResponseEntity<List<AlertaDTO>> listarAlertas() {
        ModelMapper m = new ModelMapper();
        List<AlertaDTO> lista = alS.listarAlertas().stream()
                .map(al -> {
                    AlertaDTO dto = m.map(al, AlertaDTO.class);
                    dto.setIdVulnerabilidad(al.getVulnerabilidad().getIdVulnerabilidad());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody AlertaDTO dto) {
        if (dto.getDateAlerta().isAfter(java.time.LocalDateTime.now())) {
            return ResponseEntity.badRequest()
                    .body("La fecha de la alerta no es válida");
        }

        Optional<Vulnerabilidad> vulnerabilidad = vS.listId(dto.getIdVulnerabilidad());
        if (vulnerabilidad.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Vulnerabilidad no encontrada");
        }

        Alerta al = new Alerta();
        al.setVulnerabilidad(vulnerabilidad.get());
        al.setTypeAlerta(dto.getTypeAlerta());
        al.setDescriptionAlerta(dto.getDescriptionAlerta());
        al.setRiskLevelAlerta(dto.getRiskLevelAlerta());
        al.setMessageAlerta(dto.getMessageAlerta());
        al.setStatusAlerta(dto.isStatusAlerta());
        al.setDateAlerta(dto.getDateAlerta());

        Alerta saved = alS.insert(al);

        ModelMapper m = new ModelMapper();
        AlertaDTO responseDTO = m.map(saved, AlertaDTO.class);
        responseDTO.setIdVulnerabilidad(saved.getVulnerabilidad().getIdVulnerabilidad());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/modificar")
    public ResponseEntity<String> actualizar(@RequestBody AlertaDTO dto) {
        if (dto.getDateAlerta().isAfter(java.time.LocalDateTime.now())) {
            return ResponseEntity.badRequest()
                    .body("La fecha de la alerta no es válida");
        }

        Optional<Alerta> existente = alS.listId(dto.getIdAlerta());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Alerta no encontrada");
        }

        Optional<Vulnerabilidad> vulnerabilidad = vS.listId(dto.getIdVulnerabilidad());
        if (vulnerabilidad.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Vulnerabilidad no encontrada");
        }

        Alerta al = existente.get();
        al.setVulnerabilidad(vulnerabilidad.get());
        al.setTypeAlerta(dto.getTypeAlerta());
        al.setDescriptionAlerta(dto.getDescriptionAlerta());
        al.setRiskLevelAlerta(dto.getRiskLevelAlerta());
        al.setMessageAlerta(dto.getMessageAlerta());
        al.setStatusAlerta(dto.isStatusAlerta());
        al.setDateAlerta(dto.getDateAlerta());

        alS.update(al);
        return ResponseEntity.ok("Alerta actualizada correctamente");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Alerta> alerta = alS.listId(id);
        if (alerta.isPresent()) {
            alS.delete(id);
            return ResponseEntity.ok("Alerta eliminada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Alerta no encontrada");
        }
    }
}