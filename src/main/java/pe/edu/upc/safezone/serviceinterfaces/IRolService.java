package pe.edu.upc.safezone.serviceinterfaces;

import pe.edu.upc.safezone.entities.Rol;

import java.util.List;
import java.util.Optional;

public interface IRolService {

    public List<Rol> ListarRoles();
    public Rol insert(Rol ro);
    public Optional<Rol> listId(int id);
    public void update(Rol r);
    public void delete(int id);
}
