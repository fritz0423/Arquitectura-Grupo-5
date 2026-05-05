package pe.edu.upc.safezone.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.safezone.entities.Recomendacion;
import pe.edu.upc.safezone.repositories.IRecomendacionRepository;
import pe.edu.upc.safezone.serviceinterfaces.IRecomendacionService;

import java.util.List;
import java.util.Optional;

@Service
public class RecomendacionServiceImplement implements IRecomendacionService {

    @Autowired
    private IRecomendacionRepository rcR;

    @Override
    public List<Recomendacion> listarRecomendaciones() { return rcR.findAll(); }

    @Override
    public Recomendacion insert(Recomendacion rc) { return rcR.save(rc); }

    @Override
    public Optional<Recomendacion> listId(int id) { return rcR.findById(id); }

    @Override
    public void update(Recomendacion rc) { rcR.save(rc); }

    @Override
    public void delete(int id) { rcR.deleteById(id); }

    @Override
    public List<Recomendacion> findByPriorityRecomendacionContainingIgnoreCase(String priority) {
        return rcR.findByPriorityRecomendacionContainingIgnoreCase(priority);
    }

    @Override
    public List<String[]> countByPriority() {
        return rcR.countByPriority();
    }
}