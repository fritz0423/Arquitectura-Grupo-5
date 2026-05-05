package pe.edu.upc.safezone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.safezone.entities.Recomendacion;

import java.util.List;

@Repository
public interface IRecomendacionRepository extends JpaRepository<Recomendacion, Integer> {

    @Query(value = "SELECT r.priority_recomendacion, COUNT(r.id_recomendacion) " +
            "FROM recomendacion r " +
            "GROUP BY r.priority_recomendacion", nativeQuery = true)
    List<String[]> countByPriority();

    List<Recomendacion> findByPriorityRecomendacionContainingIgnoreCase(String priority);
}