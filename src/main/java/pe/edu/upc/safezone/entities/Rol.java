package pe.edu.upc.safezone.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "Rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRol;

    @Column(name = "nameRol",length =50 ,nullable =false )
    private String nameRol;

    @OneToOne
    @JoinColumn(name = "idUsuario",  nullable = false) // FK en la tabla Rol
    private Usuario usuario;

    public Rol() {
    }

    public Rol(int idRol, String nameRol, Usuario usuario) {
        this.idRol = idRol;
        this.nameRol = nameRol;
        this.usuario = usuario;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNameRol() {
        return nameRol;
    }

    public void setNameRol(String nameRol) {
        this.nameRol = nameRol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
