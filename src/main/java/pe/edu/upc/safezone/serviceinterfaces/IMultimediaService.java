package pe.edu.upc.safezone.serviceinterfaces;

import pe.edu.upc.safezone.entities.Multimedia;

import java.util.List;
import java.util.Optional;

public interface IMultimediaService {

    List<Multimedia> listarMultimedia();
    Multimedia insert(Multimedia mu);
    Optional<Multimedia> listId(int id);
    void update(Multimedia mu);
    void delete(int id);
}