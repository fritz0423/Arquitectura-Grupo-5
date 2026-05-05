package pe.edu.upc.safezone.serviceinterfaces;

import pe.edu.upc.safezone.entities.Alerta;
import java.util.List;
import java.util.Optional;

public interface IAlertaService {
    List<Alerta> listarAlertas();
    Alerta insert(Alerta al);
    Optional<Alerta> listId(int id);
    void update(Alerta al);
    void delete(int id);
}