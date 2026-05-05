package pe.edu.upc.safezone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.safezone.entities.Modulo;

import java.util.List;

@Repository
public interface IModuloRepository extends JpaRepository<Modulo, Integer> {
    @Query("SELECT m FROM Modulo m WHERE m.progressModulo >= :progresoMin")
    List<Modulo> findByProgresoMayorIgual(@Param("progresoMin") float progresoMin);

    List<Modulo> findByCategoryModulo(String categoryModulo);

    @Query(value = """
    SELECT m.id_modulo,
           m.title_modulo,
           m.category_modulo,
           m.progress_modulo,
           COUNT(mm.id_multimedia) AS total_multimedia
    FROM modulo m
    LEFT JOIN multimedia mm ON m.id_modulo = mm.id_modulo
    GROUP BY m.id_modulo,
             m.title_modulo,
             m.category_modulo,
             m.progress_modulo
    ORDER BY total_multimedia DESC
    """, nativeQuery = true)
    List<Object[]> findModulosConTotalMultimedia();
}
