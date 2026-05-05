package pe.edu.upc.safezone.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.safezone.dtos.*;
import pe.edu.upc.safezone.entities.Modulo;
import pe.edu.upc.safezone.entities.Usuario;
import pe.edu.upc.safezone.serviceinterfaces.IModuloService;
import pe.edu.upc.safezone.serviceinterfaces.IUsuarioService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/modulo")
public class ModuloController {

    @Autowired
    private IModuloService mS;

    @Autowired
    private IUsuarioService uS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ModuloListDTO>> listarModulos() {
        ModelMapper m = new ModelMapper();
        List<ModuloListDTO> lista = mS.listarModulos().stream()
                .map(mo -> m.map(mo, ModuloListDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/registrar")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> registrar(@RequestBody ModuloGeneralDTO dto) {
        if (dto.getProgressModulo() < 0 || dto.getProgressModulo() > 100) {
            return ResponseEntity.badRequest()
                    .body("El progreso del módulo debe estar entre 0 y 100");
        }

        Optional<Usuario> usuario = uS.listId(dto.getIdUsuario());
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }

        Modulo mo = new Modulo();
        mo.setUsuario(usuario.get());
        mo.setTitleModulo(dto.getTitleModulo());
        mo.setDescriptionModulo(dto.getDescriptionModulo());
        mo.setCategoryModulo(dto.getCategoryModulo());
        mo.setProgressModulo(dto.getProgressModulo());
        mo.setDateUpdateModulo(dto.getDateUpdateModulo());

        Modulo saved = mS.insert(mo);

        ModelMapper m = new ModelMapper();
        ModuloGeneralDTO responseDTO = m.map(saved, ModuloGeneralDTO.class);
        responseDTO.setIdUsuario(saved.getUsuario().getIdUsuario());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/modificar")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> actualizar(@RequestBody ModuloGeneralDTO dto) {
        if (dto.getProgressModulo() < 0 || dto.getProgressModulo() > 100) {
            return ResponseEntity.badRequest()
                    .body("El progreso del módulo debe estar entre 0 y 100");
        }

        Optional<Modulo> existente = mS.listId(dto.getIdModulo());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Módulo no encontrado");
        }

        Optional<Usuario> usuario = uS.listId(dto.getIdUsuario());
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }

        Modulo mo = existente.get();
        mo.setUsuario(usuario.get());
        mo.setTitleModulo(dto.getTitleModulo());
        mo.setDescriptionModulo(dto.getDescriptionModulo());
        mo.setCategoryModulo(dto.getCategoryModulo());
        mo.setProgressModulo(dto.getProgressModulo());
        mo.setDateUpdateModulo(dto.getDateUpdateModulo());

        mS.update(mo);
        return ResponseEntity.ok("Módulo actualizado correctamente");
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Modulo> modulo = mS.listId(id);
        if (modulo.isPresent()) {
            mS.delete(id);
            return ResponseEntity.ok("Módulo eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Módulo no encontrado");
        }
    }

    @GetMapping("/listar-progreso-avanzado")
    @PreAuthorize("hasAnyAuthority('ADMIN','CLIENT')")
    public ResponseEntity<?> listarPorProgresoMayorIgual() {

        float progresoMinimo = 65f;

        List<Modulo> modulos = mS.listarPorProgresoMayorIgual(progresoMinimo);

        if (modulos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron módulos con progreso mayor o igual a 65");
        }

        List<ModuloAvanzadoDTO> resultado = modulos.stream()
                .map(mod -> {
                    ModuloAvanzadoDTO dto = new ModuloAvanzadoDTO();
                    dto.setIdModulo(mod.getIdModulo());
                    dto.setTitleModulo(mod.getTitleModulo());
                    dto.setDescriptionModulo(mod.getDescriptionModulo());
                    dto.setCategoryModulo(mod.getCategoryModulo());
                    dto.setProgressModulo(mod.getProgressModulo());
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(resultado);
    }


    @GetMapping("/listar-categoria")
    @PreAuthorize("hasAnyAuthority('ADMIN','CLIENT')")
    public ResponseEntity<?> listarPorCategoria(@RequestParam String categoria) {

        if (categoria == null || categoria.isBlank()) {
            return ResponseEntity.badRequest()
                    .body("El parámetro 'categoria' es obligatorio");
        }

        String categoriaLimpia = categoria.trim();

        if (categoriaLimpia.length() < 3) {
            return ResponseEntity.badRequest()
                    .body("La categoría debe tener al menos 3 caracteres");
        }

        List<Modulo> modulos = mS.listarmodulosxcategoria(categoriaLimpia);

        if (modulos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron módulos con esa categoría");
        }

        ModelMapper m = new ModelMapper();
        List<ModuloCategoriaDTO> resultado = modulos.stream()
                .map(mod -> m.map(mod, ModuloCategoriaDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(resultado);
    }
    @GetMapping("/total-multimedia")
    @PreAuthorize("hasAnyAuthority('ADMIN','CLIENT')")
    public ResponseEntity<?> listarModulosConTotalMultimedia() {

        List<Object[]> lista = mS.listarmodulosconmultimedia();

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron módulos con multimedia");
        }

        List<ModuloMultimediaDTO> resultado = lista.stream().map(fila -> {
            ModuloMultimediaDTO dto = new ModuloMultimediaDTO();

            dto.setIdModulo(((Number) fila[0]).intValue());
            dto.setTitleModulo((String) fila[1]);
            dto.setCategoryModulo((String) fila[2]);
            dto.setProgressModulo(((Number) fila[3]).intValue());
            dto.setTotalMultimedia(((Number) fila[4]).intValue());

            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(resultado);
    }
}