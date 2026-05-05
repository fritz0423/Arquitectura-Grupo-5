package pe.edu.upc.safezone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.safezone.entities.Multimedia;

@Repository
public interface IMultimediaRepository extends JpaRepository<Multimedia, Integer> {
}