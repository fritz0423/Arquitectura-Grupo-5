package pe.edu.upc.safezone.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.safezone.entities.Actividad;
import pe.edu.upc.safezone.repositories.IActividadrepository;
import pe.edu.upc.safezone.serviceinterfaces.IActividadService;

import java.util.List;
import java.util.Optional;

@Service
public class ActividadServiceImplement implements IActividadService {

    @Autowired
    private IActividadrepository aR;


    @Override
    public List<Actividad> ListarActividades() {
        return aR.findAll();
    }

    @Override
    public Actividad insert(Actividad ac) {
        return aR.save(ac);
    }

    @Override
    public Optional<Actividad> listId(int id) {
        return aR.findById(id);
    }

    @Override
    public void update(Actividad a) {
        aR.save(a);
    }

    @Override
    public void delete(int id) {
        aR.deleteById(id);
    }
}
