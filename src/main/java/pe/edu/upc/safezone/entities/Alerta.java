package pe.edu.upc.safezone.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Alerta")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAlerta")
    private int idAlerta;

    @ManyToOne
    @JoinColumn(name = "idVulnerabilidad", nullable = false)
    private Vulnerabilidad vulnerabilidad;

    @Column(name = "typeAlerta", length = 100, nullable = false)
    private String typeAlerta;

    @Column(name = "descriptionAlerta", length = 400, nullable = false)
    private String descriptionAlerta;

    @Column(name = "riskLevelAlerta", length = 20, nullable = false)
    private String riskLevelAlerta;

    @Column(name = "messageAlerta", length = 400, nullable = false)
    private String messageAlerta;

    @Column(name = "statusAlerta", nullable = false)
    private boolean statusAlerta;

    @Column(name = "dateAlerta", nullable = false)
    private LocalDateTime dateAlerta;

    public Alerta() {}

    public Alerta(int idAlerta, Vulnerabilidad vulnerabilidad, String typeAlerta, String descriptionAlerta,
                  String riskLevelAlerta, String messageAlerta, boolean statusAlerta, LocalDateTime dateAlerta) {
        this.idAlerta = idAlerta;
        this.vulnerabilidad = vulnerabilidad;
        this.typeAlerta = typeAlerta;
        this.descriptionAlerta = descriptionAlerta;
        this.riskLevelAlerta = riskLevelAlerta;
        this.messageAlerta = messageAlerta;
        this.statusAlerta = statusAlerta;
        this.dateAlerta = dateAlerta;
    }

    public int getIdAlerta() { return idAlerta; }
    public void setIdAlerta(int idAlerta) { this.idAlerta = idAlerta; }

    public Vulnerabilidad getVulnerabilidad() { return vulnerabilidad; }
    public void setVulnerabilidad(Vulnerabilidad vulnerabilidad) { this.vulnerabilidad = vulnerabilidad; }

    public String getTypeAlerta() { return typeAlerta; }
    public void setTypeAlerta(String typeAlerta) { this.typeAlerta = typeAlerta; }

    public String getDescriptionAlerta() { return descriptionAlerta; }
    public void setDescriptionAlerta(String descriptionAlerta) { this.descriptionAlerta = descriptionAlerta; }

    public String getRiskLevelAlerta() { return riskLevelAlerta; }
    public void setRiskLevelAlerta(String riskLevelAlerta) { this.riskLevelAlerta = riskLevelAlerta; }

    public String getMessageAlerta() { return messageAlerta; }
    public void setMessageAlerta(String messageAlerta) { this.messageAlerta = messageAlerta; }

    public boolean isStatusAlerta() { return statusAlerta; }
    public void setStatusAlerta(boolean statusAlerta) { this.statusAlerta = statusAlerta; }

    public LocalDateTime getDateAlerta() { return dateAlerta; }
    public void setDateAlerta(LocalDateTime dateAlerta) { this.dateAlerta = dateAlerta; }
}