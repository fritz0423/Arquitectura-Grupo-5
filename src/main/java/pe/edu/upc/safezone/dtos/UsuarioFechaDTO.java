package pe.edu.upc.safezone.dtos;

import java.time.LocalDateTime;

public class UsuarioFechaDTO {
    private int idUsuario;
    private String nameUsuario;
    private String emailUsuario;
    private boolean statusUsuario;
    private LocalDateTime dateRegisterUsuario;

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNameUsuario() { return nameUsuario; }
    public void setNameUsuario(String nameUsuario) { this.nameUsuario = nameUsuario; }

    public String getEmailUsuario() { return emailUsuario; }
    public void setEmailUsuario(String emailUsuario) { this.emailUsuario = emailUsuario; }

    public boolean isStatusUsuario() { return statusUsuario; }
    public void setStatusUsuario(boolean statusUsuario) { this.statusUsuario = statusUsuario; }

    public LocalDateTime getDateRegisterUsuario() { return dateRegisterUsuario; }
    public void setDateRegisterUsuario(LocalDateTime dateRegisterUsuario) { this.dateRegisterUsuario = dateRegisterUsuario; }

}
