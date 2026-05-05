package pe.edu.upc.safezone.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.safezone.entities.Alerta;
import pe.edu.upc.safezone.repositories.IAlertaRepository;
import pe.edu.upc.safezone.serviceinterfaces.IAlertaService;

import java.util.List;
import java.util.Optional;

@Service
public class AlertaServiceImplement implements IAlertaService {

    @Autowired
    private IAlertaRepository alR;

    @Override
    public List<Alerta> listarAlertas() { return alR.findAll(); }

    @Override
    public Alerta insert(Alerta al) { return alR.save(al); }

    @Override
    public Optional<Alerta> listId(int id) { return alR.findById(id); }

    @Override
    public void update(Alerta al) { alR.save(al); }

    @Override
    public void delete(int id) { alR.deleteById(id); }
}