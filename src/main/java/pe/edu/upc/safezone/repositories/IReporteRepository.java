package pe.edu.upc.safezone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.safezone.entities.Reporte;

import java.util.List;


@Repository
public interface IReporteRepository extends JpaRepository<Reporte, Integer> {

    List<Reporte> findByTypeReporteContainingIgnoreCase(String type);

    @Query(value = "SELECT v.type_vulnerabilidad, COUNT(r.id_reporte) " +
            "FROM vulnerabilidad v " +
            "LEFT JOIN reporte r ON v.id_vulnerabilidad = r.id_vulnerabilidad " +
            "GROUP BY v.type_vulnerabilidad", nativeQuery = true)
    List<String[]> countReportsByVulnerabilityType();

    @Query(value = "SELECT u.name_usuario, COUNT(rep.id_reporte) " +
            "FROM usuario u " +
            "JOIN actividad a ON u.id_usuario = a.id_usuario " +
            "JOIN vulnerabilidad v ON a.id_actividad = v.id_actividad " +
            "JOIN reporte rep ON v.id_vulnerabilidad = rep.id_vulnerabilidad " +
            "GROUP BY u.name_usuario " +
            "ORDER BY COUNT(rep.id_reporte) DESC", nativeQuery = true)
    List<String[]> countReportsByUser();


}