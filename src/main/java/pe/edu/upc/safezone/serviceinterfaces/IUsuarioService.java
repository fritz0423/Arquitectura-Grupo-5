package pe.edu.upc.safezone.serviceinterfaces;

import pe.edu.upc.safezone.entities.Modulo;
import pe.edu.upc.safezone.entities.Usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    public List<Usuario> ListarUsuarios();
    public Usuario insert(Usuario usu);
    public Optional<Usuario> listId(int id);
    public void update(Usuario u);
    public void delete(int id);
    List<Object[]> obtenerUsuariosConTotalActividades();
    List<Usuario> listarPorIntervaloFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    List<Usuario> listarusuarioxestado(Boolean statusUsuario);

    List<Object[]> Usuariosresumenmodulo();

}
