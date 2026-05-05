package pe.edu.upc.safezone.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.safezone.dtos.*;
import pe.edu.upc.safezone.entities.Modulo;
import pe.edu.upc.safezone.entities.Usuario;
import pe.edu.upc.safezone.serviceinterfaces.IModuloService;
import pe.edu.upc.safezone.serviceinterfaces.IUsuarioService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private IUsuarioService uS;
    @Autowired
    private IModuloService iModuloService;



    @GetMapping
    public ResponseEntity<List<UsuarioListDTO>> ListarUsuarios() {
        ModelMapper m = new ModelMapper();
        List<UsuarioListDTO>listaUsuarios=uS.ListarUsuarios().stream()
                .map(y->m.map(y,UsuarioListDTO.class))
                .collect(Collectors.toList());
        return  ResponseEntity.ok().body(listaUsuarios);
    }


    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody UsuarioGeneralDTO dto){
        if (dto.getDateRegisterUsuario().isAfter(java.time.LocalDateTime.now())) {
            return ResponseEntity.badRequest()
                    .body("La fecha de registro del usuario no es valida");
        }
        ModelMapper m=new ModelMapper();
        Usuario c=m.map(dto, Usuario.class);
        Usuario cur= uS.insert(c);
        UsuarioGeneralDTO responseDTO=m.map(cur,UsuarioGeneralDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }

    @PutMapping("/modificar")
    public ResponseEntity<String> actualizar(@RequestBody UsuarioGeneralDTO dto) {
        if (dto.getDateRegisterUsuario().isAfter(java.time.LocalDateTime.now())) {
            return ResponseEntity.badRequest()
                    .body("La fecha de registro del usuario no es valida");

        }

        Optional<Usuario> existente = uS.listId(dto.getIdUsuario());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }

        Usuario uso = existente.get();

        uso.setNameUsuario(dto.getNameUsuario());
        uso.setEmailUsuario(dto.getEmailUsuario());
        uso.setPasswordUsuario(dto.getPasswordUsuario());
        uso.setStatusUsuario(dto.isStatusUsuario());
        uso.setDateRegisterUsuario(dto.getDateRegisterUsuario());
        uS.update(uso);
        return ResponseEntity.ok("Usuario actualizado correctamente");

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Usuario> usuario = uS.listId(id);
        if (usuario.isPresent()) {
            uS.delete(id);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }


    }

    @GetMapping("/reporte-actividades")
    public ResponseEntity<List<UsuarioActividadDTO>> reporteActividades() {
        List<UsuarioActividadDTO> resultado = uS.obtenerUsuariosConTotalActividades()
                .stream()
                .map(row -> {
                    UsuarioActividadDTO dto = new UsuarioActividadDTO();
                    dto.setIdUsuario(((Number) row[0]).intValue());
                    dto.setNameUsuario((String) row[1]);
                    dto.setEmailUsuario((String) row[2]);
                    dto.setStatusUsuario((Boolean) row[3]);
                    dto.setTotalActividades(((Number) row[4]).intValue());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/listar-fechas")
    public ResponseEntity<?> listarPorIntervaloFechas(
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {

        // Validar formato de fecha
        LocalDateTime inicio;
        LocalDateTime fin;
        try {
            inicio = LocalDateTime.parse(fechaInicio);
            fin = LocalDateTime.parse(fechaFin);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("El formato de fecha no es correcto. Debe ser: yyyy-MM-ddTHH:mm:ss");
        }
        if (inicio.isAfter(fin)) {
            return ResponseEntity.badRequest()
                    .body("La fecha inicio no puede ser mayor a la fecha fin");
        }

        List<Usuario> usuarios = uS.listarPorIntervaloFechas(inicio, fin);

        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se han registrado usuarios en las fechas");
        }

        ModelMapper m = new ModelMapper();
        List<UsuarioFechaDTO> resultado = usuarios.stream()
                .map(u -> m.map(u, UsuarioFechaDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/listar-estado")
    public ResponseEntity<?> listarPorEstado(@RequestParam String estado) {

        Boolean estadoBoolean;


        if (estado == null || estado.isBlank()) {
            return ResponseEntity.badRequest()
                    .body("El parámetro 'estado' es obligatorio (true o false)");
        }


        if (estado.equalsIgnoreCase("true")) {
            estadoBoolean = true;
        } else if (estado.equalsIgnoreCase("false")) {
            estadoBoolean = false;
        } else {
            return ResponseEntity.badRequest()
                    .body("El valor de 'estado' debe ser 'true' o 'false'");
        }

        List<Usuario> usuarios = uS.listarusuarioxestado(estadoBoolean);

        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron usuarios con ese estado");
        }


        ModelMapper m = new ModelMapper();
        List<UsuarioStatusDTO> resultado = usuarios.stream()
                .map(u -> m.map(u, UsuarioStatusDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/resumen-modulos")
    public ResponseEntity<?> listarUsuariosConResumenModulos() {

        List<Object[]> lista = uS.Usuariosresumenmodulo();

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron usuarios con resumen de módulos");
        }

        List<UsuarioModuloResumenDTO> resultado = lista.stream().map(fila -> {
            UsuarioModuloResumenDTO dto = new UsuarioModuloResumenDTO();

            dto.setIdUsuario(((Number) fila[0]).intValue());
            dto.setNameUsuario((String) fila[1]);
            dto.setEmailUsuario((String) fila[2]);
            dto.setStatusUsuario((Boolean) fila[3]);
            dto.setTotalModulos(((Number) fila[4]).longValue());
            dto.setPromedioProgreso(((Number) fila[5]).doubleValue());

            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(resultado);
    }
}
