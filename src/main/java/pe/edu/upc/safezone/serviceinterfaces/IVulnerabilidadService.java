package pe.edu.upc.safezone.serviceinterfaces;

import pe.edu.upc.safezone.entities.Vulnerabilidad;

import java.util.List;
import java.util.Optional;

public interface IVulnerabilidadService {
    public List<Vulnerabilidad> ListarVulnerabilidad();
    public Vulnerabilidad insert(Vulnerabilidad vu);
    public Optional<Vulnerabilidad> listId(int id);
    public void update(Vulnerabilidad v);
    public void delete(int id);
    List<Object[]> obtenerVulnerabilidadesConTotalAlertas();
}
