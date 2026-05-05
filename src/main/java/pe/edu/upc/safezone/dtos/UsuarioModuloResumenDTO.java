package pe.edu.upc.safezone.dtos;

public class UsuarioModuloResumenDTO {
    private int idUsuario;
    private String nameUsuario;
    private String emailUsuario;
    private Boolean statusUsuario;
    private Long totalModulos;
    private Double promedioProgreso;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNameUsuario() {
        return nameUsuario;
    }

    public void setNameUsuario(String nameUsuario) {
        this.nameUsuario = nameUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public Boolean getStatusUsuario() {
        return statusUsuario;
    }

    public void setStatusUsuario(Boolean statusUsuario) {
        this.statusUsuario = statusUsuario;
    }

    public Long getTotalModulos() {
        return totalModulos;
    }

    public void setTotalModulos(Long totalModulos) {
        this.totalModulos = totalModulos;
    }

    public Double getPromedioProgreso() {
        return promedioProgreso;
    }

    public void setPromedioProgreso(Double promedioProgreso) {
        this.promedioProgreso = promedioProgreso;
    }
}
