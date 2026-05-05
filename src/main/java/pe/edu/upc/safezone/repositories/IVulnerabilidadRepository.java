package pe.edu.upc.safezone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.safezone.entities.Vulnerabilidad;

import java.util.List;

@Repository
public interface IVulnerabilidadRepository extends JpaRepository<Vulnerabilidad,Integer> {

    @Query(value = """
        SELECT v.id_vulnerabilidad,
               v.name_vulnerabilidad,
               v.risk_level_vulnerabilidad,
               v.type_vulnerabilidad,
               COUNT(al.id_alerta) AS total_alertas
        FROM vulnerabilidad v
        LEFT JOIN alerta al ON v.id_vulnerabilidad = al.id_vulnerabilidad
        GROUP BY v.id_vulnerabilidad, v.name_vulnerabilidad,
                 v.risk_level_vulnerabilidad, v.type_vulnerabilidad
        ORDER BY total_alertas DESC
        """, nativeQuery = true)
    List<Object[]> findVulnerabilidadesConTotalAlertas();

}

