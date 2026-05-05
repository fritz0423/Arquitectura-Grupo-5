package pe.edu.upc.safezone.serviceimplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.safezone.entities.Modulo;
import pe.edu.upc.safezone.repositories.IModuloRepository;
import pe.edu.upc.safezone.serviceinterfaces.IModuloService;

import java.util.List;
import java.util.Optional;

@Service
public class ModuloServiceImplement implements IModuloService {

    @Autowired
    private IModuloRepository mR;

    @Override
    public List<Modulo> listarModulos() {
        return mR.findAll();
    }

    @Override
    public Modulo insert(Modulo mo) {
        return mR.save(mo);
    }

    @Override
    public Optional<Modulo> listId(int id) {
        return mR.findById(id);
    }

    @Override
    public void update(Modulo mo) {
        mR.save(mo);
    }

    @Override
    public void delete(int id) {
        mR.deleteById(id);
    }

    @Override
    public List<Modulo> listarPorProgresoMayorIgual(float progresoMin) {
        return mR.findByProgresoMayorIgual(progresoMin);
    }

    @Override
    public List<Modulo> listarmodulosxcategoria(String categoryModulo) {
        return mR.findByCategoryModulo(categoryModulo);
    }

    @Override
    public List<Object[]> listarmodulosconmultimedia() {
        return mR.findModulosConTotalMultimedia();
    }

}