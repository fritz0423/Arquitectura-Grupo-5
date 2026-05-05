package pe.edu.upc.safezone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.safezone.entities.Actividad;


@Repository
public interface IActividadrepository extends JpaRepository<Actividad,Integer> {
}



