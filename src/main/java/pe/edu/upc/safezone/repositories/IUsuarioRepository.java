package pe.edu.upc.safezone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.safezone.entities.Modulo;
import pe.edu.upc.safezone.entities.Usuario;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> {

    @Query(value = """
    SELECT u.id_usuario,
           u.name_usuario,
           u.email_usuario,
           u.status_usuario,
           COUNT(a.id_actividad) AS total_actividades
    FROM usuario u
    LEFT JOIN actividad a ON u.id_usuario = a.id_usuario
    GROUP BY u.id_usuario, u.name_usuario, u.email_usuario, u.status_usuario
    ORDER BY total_actividades DESC
    """, nativeQuery = true)
    List<Object[]> findUsuariosConTotalActividades();

    List<Usuario> findByDateRegisterUsuarioBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    List<Usuario> findByStatusUsuario(Boolean statusUsuario);

    @Query(value = """
    SELECT u.id_usuario,
           u.name_usuario,
           u.email_usuario,
           u.status_usuario,
           COUNT(m.id_modulo) AS total_modulos,
           COALESCE(AVG(m.progress_modulo), 0) AS promedio_progreso
    FROM usuario u
    LEFT JOIN modulo m ON u.id_usuario = m.id_usuario
    GROUP BY u.id_usuario,
             u.name_usuario,
             u.email_usuario,
             u.status_usuario
    ORDER BY promedio_progreso DESC
    """, nativeQuery = true)
    List<Object[]> findUsuariosConResumenModulos();
}
