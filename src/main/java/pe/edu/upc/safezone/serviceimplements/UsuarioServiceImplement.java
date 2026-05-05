package pe.edu.upc.safezone.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.safezone.entities.Modulo;
import pe.edu.upc.safezone.entities.Usuario;
import pe.edu.upc.safezone.repositories.IUsuarioRepository;
import pe.edu.upc.safezone.serviceinterfaces.IUsuarioService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImplement implements IUsuarioService {

    @Autowired
    private IUsuarioRepository uR;

    @Override
    public List<Usuario> ListarUsuarios() {
        return uR.findAll();
    }

    @Override
    public Usuario insert(Usuario usu) {
        return uR.save(usu);
    }

    @Override
    public Optional<Usuario> listId(int id) {
        return uR.findById(id);
    }

    @Override
    public void update(Usuario u) {
        uR.save(u);
    }

    @Override
    public void delete(int id) {
        uR.deleteById(id);
    }

    @Override
    public List<Object[]> obtenerUsuariosConTotalActividades() {
        return uR.findUsuariosConTotalActividades();
    }

    @Override
    public List<Usuario> listarPorIntervaloFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return uR.findByDateRegisterUsuarioBetween(fechaInicio, fechaFin);
    }

    @Override
    public List<Usuario> listarusuarioxestado(Boolean statusUsuario) {
        return uR.findByStatusUsuario(statusUsuario);
    }

    @Override
    public List<Object[]> Usuariosresumenmodulo() {
        return uR.findUsuariosConResumenModulos();
    }


}
