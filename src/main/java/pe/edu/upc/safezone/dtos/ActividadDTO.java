package pe.edu.upc.safezone.dtos;

import java.time.LocalDateTime;

public class ActividadDTO {
    private int idActividad;
    private String actionTypeActividad;
    private String descriptionActividad;
    private String ipActividad;
    private LocalDateTime dateActividad;
    private int idUsuario;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public LocalDateTime getDateActividad() {
        return dateActividad;
    }

    public void setDateActividad(LocalDateTime dateActividad) {
        this.dateActividad = dateActividad;
    }
}
