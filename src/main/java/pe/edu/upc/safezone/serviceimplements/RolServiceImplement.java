package pe.edu.upc.safezone.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.safezone.entities.Rol;
import pe.edu.upc.safezone.repositories.IRolRepository;
import pe.edu.upc.safezone.serviceinterfaces.IRolService;

import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImplement implements IRolService {

    @Autowired
    private IRolRepository rR;

    @Override
    public List<Rol> ListarRoles() {
        return rR.findAll();
    }

    @Override
    public Rol insert(Rol ro) {
        return rR.save(ro);
    }

    @Override
    public Optional<Rol> listId(int id) {
        return rR.findById(id);
    }

    @Override
    public void update(Rol r) {
        rR.save(r);
    }

    @Override
    public void delete(int id) {
        rR.deleteById(id);
    }
}
