package pe.edu.upc.safezone.dtos;

public class UsuarioActividadDTO {
    private int idUsuario;
    private String nameUsuario;
    private String emailUsuario;
    private boolean statusUsuario;
    private int totalActividades;

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNameUsuario() { return nameUsuario; }
    public void setNameUsuario(String nameUsuario) { this.nameUsuario = nameUsuario; }

    public String getEmailUsuario() { return emailUsuario; }
    public void setEmailUsuario(String emailUsuario) { this.emailUsuario = emailUsuario; }

    public boolean isStatusUsuario() { return statusUsuario; }
    public void setStatusUsuario(boolean statusUsuario) { this.statusUsuario = statusUsuario; }

    public int getTotalActividades() { return totalActividades; }
    public void setTotalActividades(int totalActividades) { this.totalActividades = totalActividades; }
}
