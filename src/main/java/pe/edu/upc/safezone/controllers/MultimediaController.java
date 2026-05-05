package pe.edu.upc.safezone.controllers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.safezone.dtos.MultimediaGeneralDTO;
import pe.edu.upc.safezone.dtos.MultimediaListDTO;
import pe.edu.upc.safezone.entities.Modulo;
import pe.edu.upc.safezone.entities.Multimedia;
import pe.edu.upc.safezone.serviceinterfaces.IModuloService;
import pe.edu.upc.safezone.serviceinterfaces.IMultimediaService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/multimedia")
public class MultimediaController {

    @Autowired
    private IMultimediaService muS;

    @Autowired
    private IModuloService mS;

    @GetMapping
    public ResponseEntity<List<MultimediaListDTO>> listarMultimedia() {
        ModelMapper m = new ModelMapper();
        List<MultimediaListDTO> lista = muS.listarMultimedia().stream()
                .map(mu -> m.map(mu, MultimediaListDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody MultimediaGeneralDTO dto) {
        if (dto.getDurationMultimedia() <= 0) {
            return ResponseEntity.badRequest()
                    .body("La duración de la multimedia debe ser mayor a 0");
        }

        Optional<Modulo> modulo = mS.listId(dto.getIdModulo());
        if (modulo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Módulo no encontrado");
        }

        Multimedia mu = new Multimedia();
        mu.setModulo(modulo.get());
        mu.setTypeMultimedia(dto.getTypeMultimedia());
        mu.setUrlMultimedia(dto.getUrlMultimedia());
        mu.setDurationMultimedia(dto.getDurationMultimedia());
        mu.setDenominationMultimedia(dto.getDenominationMultimedia());

        Multimedia saved = muS.insert(mu);

        ModelMapper m = new ModelMapper();
        MultimediaGeneralDTO responseDTO = m.map(saved, MultimediaGeneralDTO.class);
        responseDTO.setIdModulo(saved.getModulo().getIdModulo());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/modificar")
    public ResponseEntity<String> actualizar(@RequestBody MultimediaGeneralDTO dto) {
        if (dto.getDurationMultimedia() <= 0) {
            return ResponseEntity.badRequest()
                    .body("La duración de la multimedia debe ser mayor a 0");
        }

        Optional<Multimedia> existente = muS.listId(dto.getIdMultimedia());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Multimedia no encontrada");
        }

        Optional<Modulo> modulo = mS.listId(dto.getIdModulo());
        if (modulo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Módulo no encontrado");
        }

        Multimedia mu = existente.get();
        mu.setModulo(modulo.get());
        mu.setTypeMultimedia(dto.getTypeMultimedia());
        mu.setUrlMultimedia(dto.getUrlMultimedia());
        mu.setDurationMultimedia(dto.getDurationMultimedia());
        mu.setDenominationMultimedia(dto.getDenominationMultimedia());

        muS.update(mu);
        return ResponseEntity.ok("Multimedia actualizada correctamente");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Multimedia> multimedia = muS.listId(id);
        if (multimedia.isPresent()) {
            muS.delete(id);
            return ResponseEntity.ok("Multimedia eliminada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Multimedia no encontrada");
        }
    }
}