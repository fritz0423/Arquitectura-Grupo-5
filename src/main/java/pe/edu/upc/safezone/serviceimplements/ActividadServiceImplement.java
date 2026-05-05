package pe.edu.upc.safezone.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.safezone.entities.Actividad;
import pe.edu.upc.safezone.repositories.IActividadrepository;
import pe.edu.upc.safezone.serviceinterfaces.IActividadService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ActividadServiceImplement implements IActividadService {

    @Autowired
    private IActividadrepository aR;

    @Override
    public List<Actividad> ListarActividades() { return aR.findAll(); }

    @Override
    public Actividad insert(Actividad ac) { return aR.save(ac); }

    @Override
    public Optional<Actividad> listId(int id) { return aR.findById(id); }

    @Override
    public void update(Actividad a) { aR.save(a); }

    @Override
    public void delete(int id) { aR.deleteById(id); }

    // SIMPLE 1
    @Override
    public List<Actividad> listarPorTipoAccion(String actionType) {
        return aR.findByActionTypeActividadIgnoreCase(actionType);
    }

    // SIMPLE 2
    @Override
    public List<Actividad> listarPorIp(String ip) {
        return aR.findByIpActividad(ip);
    }

    // FILTRO 1
    @Override
    public List<Actividad> listarDeUsuariosActivos() {
        return aR.findActividadesDeUsuariosActivos();
    }



    @Override
    public List<Actividad> listarPorUsuarioYRangoFechas(int idUsuario,
                                                        LocalDate fechaInicio,
                                                        LocalDate fechaFin) {
        return aR.findActividadesPorUsuarioYRangoFechas(idUsuario, fechaInicio, fechaFin);
    }



    // FILTRO 2
    @Override
    public List<Actividad> listarPorNivelRiesgoVulnerabilidad(String riskLevel) {
        return aR.findActividadesPorNivelRiesgo(riskLevel);
    }
}