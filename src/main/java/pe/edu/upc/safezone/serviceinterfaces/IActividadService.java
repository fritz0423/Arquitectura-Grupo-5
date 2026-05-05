package pe.edu.upc.safezone.serviceinterfaces;

import pe.edu.upc.safezone.entities.Actividad;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IActividadService {
    List<Actividad> ListarActividades();
    Actividad insert(Actividad ac);
    Optional<Actividad> listId(int id);
    void update(Actividad a);
    void delete(int id);

    // SIMPLE 1
    List<Actividad> listarPorTipoAccion(String actionType);

    // SIMPLE 2
    List<Actividad> listarPorIp(String ip);

    // FILTRO 1
    List<Actividad> listarDeUsuariosActivos();

    List<Actividad> listarPorUsuarioYRangoFechas(int idUsuario,
                                                 LocalDate fechaInicio,
                                                 LocalDate fechaFin);

    // FILTRO 2
    List<Actividad> listarPorNivelRiesgoVulnerabilidad(String riskLevel);
}