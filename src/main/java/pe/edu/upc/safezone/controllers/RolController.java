package pe.edu.upc.safezone.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.safezone.dtos.RolDTO;
import pe.edu.upc.safezone.entities.Rol;
import pe.edu.upc.safezone.serviceinterfaces.IRolService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rol")
public class RolController {
    @Autowired
    private IRolService rS;

    @GetMapping
    public ResponseEntity<List<RolDTO>> ListarRoles() {
        ModelMapper m = new ModelMapper();
        List<RolDTO>listaRol=rS.ListarRoles().stream()
                .map(y->m.map(y,RolDTO.class))
                .collect(Collectors.toList());
        return  ResponseEntity.ok().body(listaRol);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody RolDTO dto){
        ModelMapper m=new ModelMapper();
        Rol c=m.map(dto, Rol.class);
        Rol cur= rS.insert(c);
        RolDTO responseDTO=m.map(cur,RolDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }

    @PutMapping("/modificar")

    public ResponseEntity<String> actualizar(@RequestBody RolDTO dto) {

        Optional<Rol> existente = rS.listId(dto.getIdRol());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Rol no encontrado");
        }

        Rol ro = existente.get();

        ro.setNameRol(dto.getNameRol());


        rS.update(ro);
        return ResponseEntity.ok("Rol actualizado correctamente");

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Rol> rol = rS.listId(id);
        if (rol.isPresent()) {
            rS.delete(id);
            return ResponseEntity.ok("Rol eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Rol no encontrado");
        }

    }

}
