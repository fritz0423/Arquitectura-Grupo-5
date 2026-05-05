package pe.edu.upc.safezone.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.safezone.entities.Vulnerabilidad;
import pe.edu.upc.safezone.repositories.IVulnerabilidadRepository;
import pe.edu.upc.safezone.serviceinterfaces.IVulnerabilidadService;

import java.util.List;
import java.util.Optional;

@Service
public class VulnerabilidadServiceImplement implements IVulnerabilidadService {

    @Autowired
    private IVulnerabilidadRepository vU;

    @Override
    public List<Vulnerabilidad> ListarVulnerabilidad() {
        return vU.findAll();
    }

    @Override
    public Vulnerabilidad insert(Vulnerabilidad vu) {
        return vU.save(vu);
    }

    @Override
    public Optional<Vulnerabilidad> listId(int id) {
        return vU.findById(id);
    }

    @Override
    public void update(Vulnerabilidad v) {
        vU.save(v);
    }

    @Override
    public void delete(int id) {
        vU.deleteById(id);
    }

    @Override
    public List<Object[]> obtenerVulnerabilidadesConTotalAlertas() {
        return vU.findVulnerabilidadesConTotalAlertas();
    }
}
