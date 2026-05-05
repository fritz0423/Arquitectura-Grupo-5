package pe.edu.upc.safezone.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.safezone.entities.Reporte;
import pe.edu.upc.safezone.repositories.IReporteRepository;
import pe.edu.upc.safezone.serviceinterfaces.IReporteService;

import java.util.List;
import java.util.Optional;

@Service
public class ReporteServiceImplement implements IReporteService {

    @Autowired
    private IReporteRepository rR;

    @Override
    public List<Reporte> listarReportes() { return rR.findAll(); }

    @Override
    public Reporte insert(Reporte re) { return rR.save(re); }

    @Override
    public Optional<Reporte> listId(int id) { return rR.findById(id); }

    @Override
    public void update(Reporte re) { rR.save(re); }

    @Override
    public void delete(int id) { rR.deleteById(id); }

    @Override
    public List<Reporte> findByTypeReporteContainingIgnoreCase(String type) {
        return rR.findByTypeReporteContainingIgnoreCase(type);
    }

    @Override
    public List<String[]> countReportsByVulnerabilityType() {

        return rR.countReportsByVulnerabilityType();
    }

    @Override
    public List<String[]> countReportsByUser() {
        return rR.countReportsByUser();
    }

}