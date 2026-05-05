package pe.edu.upc.safezone.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Vulnerabilidad")
public class Vulnerabilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVulnerabilidad")
    private int idVulnerabilidad;

    @Column(name = "nameVulnerabilidad",nullable =false )
    private String nameVulnerabilidad;

    @Column(name = "descriptionVulnerabilidad", nullable = false)
    private String descriptionVulnerabilidad;

    @Column(name = "typeVulnerabilidad", nullable = false)
    private String typeVulnerabilidad;

    @Column(name = "riskLevelVulnerabilidad ", length = 20, nullable = false)
    private String riskLevelVulnerabilidad ;

    @Column(name = "dateVulnerabilidad",nullable =false )
    private LocalDateTime dateVulnerabilidad;

    @ManyToOne
    @JoinColumn(name = "idActividad", nullable = false) // FK en la tabla Rol
    private Actividad actividad;

    public Vulnerabilidad() {
    }


    public Vulnerabilidad(int idVulnerabilidad, String nameVulnerabilidad, String descriptionActividad, String typeVulnerabilidad, String riskLevelVulnerabilidad, LocalDateTime dateVulnerabilidad, Usuario usuario) {
        this.idVulnerabilidad = idVulnerabilidad;
        this.nameVulnerabilidad = nameVulnerabilidad;
        this.descriptionVulnerabilidad = descriptionActividad;
        this.typeVulnerabilidad = typeVulnerabilidad;
        this.riskLevelVulnerabilidad = riskLevelVulnerabilidad;
        this.dateVulnerabilidad = dateVulnerabilidad;
        this.actividad = actividad;
    }

    public int getIdVulnerabilidad() {
        return idVulnerabilidad;
    }

    public void setIdVulnerabilidad(int idVulnerabilidad) {
        this.idVulnerabilidad = idVulnerabilidad;
    }

    public String getNameVulnerabilidad() {
        return nameVulnerabilidad;
    }

    public void setNameVulnerabilidad(String nameVulnerabilidad) {
        this.nameVulnerabilidad = nameVulnerabilidad;
    }

    public String getDescriptionVulnerabilidad() {
        return descriptionVulnerabilidad;
    }

    public void setDescriptionVulnerabilidad(String descriptionVulnerabilidad) {
        this.descriptionVulnerabilidad = descriptionVulnerabilidad;
    }

    public String getTypeVulnerabilidad() {
        return typeVulnerabilidad;
    }

    public void setTypeVulnerabilidad(String typeVulnerabilidad) {
        this.typeVulnerabilidad = typeVulnerabilidad;
    }

    public String getRiskLevelVulnerabilidad() {
        return riskLevelVulnerabilidad;
    }

    public void setRiskLevelVulnerabilidad(String riskLevelVulnerabilidad) {
        this.riskLevelVulnerabilidad = riskLevelVulnerabilidad;
    }

    public LocalDateTime getDateVulnerabilidad() {
        return dateVulnerabilidad;
    }

    public void setDateVulnerabilidad(LocalDateTime dateVulnerabilidad) {
        this.dateVulnerabilidad = dateVulnerabilidad;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }
}
