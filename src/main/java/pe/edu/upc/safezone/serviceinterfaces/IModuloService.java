package pe.edu.upc.safezone.serviceinterfaces;
import pe.edu.upc.safezone.entities.Modulo;

import java.util.List;
import java.util.Optional;


public interface IModuloService {

    List<Modulo> listarModulos();
    Modulo insert(Modulo mo);
    Optional<Modulo> listId(int id);
    void update(Modulo mo);
    void delete(int id);
    List<Modulo> listarPorProgresoMayorIgual(float progresoMin);

    List<Modulo> listarmodulosxcategoria(String categoryModulo);

    List<Object[]> listarmodulosconmultimedia();
}