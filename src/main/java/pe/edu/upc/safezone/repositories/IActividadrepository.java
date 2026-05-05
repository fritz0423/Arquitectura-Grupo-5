package pe.edu.upc.safezone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.safezone.entities.Actividad;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IActividadrepository extends JpaRepository<Actividad, Integer> {

    // SIMPLE 1: Buscar por tipo de acción
    List<Actividad> findByActionTypeActividadIgnoreCase(String actionTypeActividad);

    // SIMPLE 2: Buscar por IP
    List<Actividad> findByIpActividad(String ipActividad);

    // FILTRO 1: Actividades de usuarios activos (Actividad + Usuario)
    @Query("SELECT a FROM Actividad a WHERE a.usuario.statusUsuario = true")
    List<Actividad> findActividadesDeUsuariosActivos();

    @Query("SELECT a FROM Actividad a " +
            "WHERE a.usuario.idUsuario = :idUsuario " +
            "AND a.dateActividad BETWEEN :fechaInicio AND :fechaFin")
    List<Actividad> findActividadesPorUsuarioYRangoFechas(
            int idUsuario,
            LocalDate fechaInicio,
            LocalDate fechaFin
    );



    // FILTRO 2: Actividades que generaron vulnerabilidades de riesgo alto (Actividad + Vulnerabilidad)
    @Query("SELECT DISTINCT a FROM Actividad a JOIN Vulnerabilidad v ON v.actividad.idActividad = a.idActividad WHERE v.riskLevelVulnerabilidad = :riskLevel")
    List<Actividad> findActividadesPorNivelRiesgo(String riskLevel);
}