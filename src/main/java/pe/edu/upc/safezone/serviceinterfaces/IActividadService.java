package pe.edu.upc.safezone.serviceinterfaces;

import pe.edu.upc.safezone.entities.Actividad;

import java.util.List;
import java.util.Optional;

public interface IActividadService {
    public List<Actividad> ListarActividades();
    public Actividad insert(Actividad ac);
    public Optional<Actividad> listId(int id);
    public void update(Actividad a);
    public void delete(int id);
}
