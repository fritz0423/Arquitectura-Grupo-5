package pe.edu.upc.safezone.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.safezone.entities.Multimedia;
import pe.edu.upc.safezone.repositories.IMultimediaRepository;
import pe.edu.upc.safezone.serviceinterfaces.IMultimediaService;

import java.util.List;
import java.util.Optional;

@Service
public class MultimediaServiceImplement implements IMultimediaService {

    @Autowired
    private IMultimediaRepository muR;

    @Override
    public List<Multimedia> listarMultimedia() {
        return muR.findAll();
    }

    @Override
    public Multimedia insert(Multimedia mu) {
        return muR.save(mu);
    }

    @Override
    public Optional<Multimedia> listId(int id) {
        return muR.findById(id);
    }

    @Override
    public void update(Multimedia mu) {
        muR.save(mu);
    }

    @Override
    public void delete(int id) {
        muR.deleteById(id);
    }
}