package pe.edu.upc.safezone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.safezone.entities.Alerta;

@Repository
public interface IAlertaRepository extends JpaRepository<Alerta, Integer> {
}