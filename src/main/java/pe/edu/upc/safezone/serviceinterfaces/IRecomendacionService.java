package pe.edu.upc.safezone.serviceinterfaces;

import pe.edu.upc.safezone.entities.Recomendacion;
import java.util.List;
import java.util.Optional;

public interface IRecomendacionService {
    List<Recomendacion> listarRecomendaciones();
    Recomendacion insert(Recomendacion rc);
    Optional<Recomendacion> listId(int id);
    void update(Recomendacion rc);
    void delete(int id);

    List<Recomendacion> findByPriorityRecomendacionContainingIgnoreCase(String priority);
    List<String[]> countByPriority();
}