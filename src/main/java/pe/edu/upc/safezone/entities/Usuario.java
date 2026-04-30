package pe.edu.upc.safezone.entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private int idUsuario;

    @Column(name = "nameUsuario",length =50 ,nullable =false )
    private String nameUsuario;

    @Column(name = "emailUsuario",nullable =false )
    private String emailUsuario;

    @Column(name = "passwordUsuario",nullable =false )
    private String passwordUsuario;

    @Column(name = "statusUsuario",nullable =false )
    private boolean statusUsuario;

    @Column(name = "dateRegisterUsuario",nullable =false )
    private LocalDateTime dateRegisterUsuario;


    public Usuario() {
    }

    public Usuario(int idUsuario, String nameUsuario, String emailUsuario, String passwordUsuario, boolean statusUsuario, LocalDateTime dateRegisterUsuario) {
        this.idUsuario = idUsuario;
        this.nameUsuario = nameUsuario;
        this.emailUsuario = emailUsuario;
        this.passwordUsuario = passwordUsuario;
        this.statusUsuario = statusUsuario;
        this.dateRegisterUsuario = dateRegisterUsuario;
    }

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

    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }

    public boolean isStatusUsuario() {
        return statusUsuario;
    }

    public void setStatusUsuario(boolean statusUsuario) {
        this.statusUsuario = statusUsuario;
    }

    public LocalDateTime getDateRegisterUsuario() {
        return dateRegisterUsuario;
    }

    public void setDateRegisterUsuario(LocalDateTime dateRegisterUsuario) {
        this.dateRegisterUsuario = dateRegisterUsuario;
    }
}
