package pe.edu.upc.safezone.serviceinterfaces;

import pe.edu.upc.safezone.entities.Reporte;
import java.util.List;
import java.util.Optional;

public interface IReporteService {
    List<Reporte> listarReportes();
    Reporte insert(Reporte re);
    Optional<Reporte> listId(int id);
    void update(Reporte re);
    void delete(int id);

    List<Reporte> findByTypeReporteContainingIgnoreCase(String type);
    List<String[]> countReportsByVulnerabilityType();
    List<String[]> countReportsByUser();

}