package pe.edu.upc.safezone.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Actividad")
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idActividad;

    @Column(name = "actionTypeActividad", length = 50, nullable = false)
    private String actionTypeActividad;

    @Column(name = "descriptionActividad", length = 400, nullable = false)
    private String descriptionActividad;

    @Column(name = "ipActividad", length = 400, nullable = false)
    private String ipActividad;

    @Column(name = "dateActividad", nullable = false)
    private LocalDate dateActividad;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false) // FK en la tabla Rol
    private Usuario usuario;

    public Actividad() {
    }

    public Actividad(int idActividad, String actionTypeActividad, String descriptionActividad, String ipActividad, LocalDate dateActividad, Usuario usuario) {
        this.idActividad = idActividad;
        this.actionTypeActividad = actionTypeActividad;
        this.descriptionActividad = descriptionActividad;
        this.ipActividad = ipActividad;
        this.dateActividad = dateActividad;
        this.usuario = usuario;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getActionTypeActividad() {
        return actionTypeActividad;
    }

    public void setActionTypeActividad(String actionTypeActividad) {
        this.actionTypeActividad = actionTypeActividad;
    }

    public String getDescriptionActividad() {
        return descriptionActividad;
    }

    public void setDescriptionActividad(String descriptionActividad) {
        this.descriptionActividad = descriptionActividad;
    }

    public String getIpActividad() {
        return ipActividad;
    }

    public void setIpActividad(String ipActividad) {
        this.ipActividad = ipActividad;
    }

    public LocalDate getDateActividad() {
        return dateActividad;
    }

    public void setDateActividad(LocalDate dateActividad) {
        this.dateActividad = dateActividad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}