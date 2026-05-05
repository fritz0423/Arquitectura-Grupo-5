package pe.edu.upc.safezone.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Reporte")
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReporte")
    private int idReporte;

    @ManyToOne
    @JoinColumn(name = "idVulnerabilidad", nullable = false)
    private Vulnerabilidad vulnerabilidad;

    @Column(name = "typeReporte", length = 50, nullable = false)
    private String typeReporte;

    @Column(name = "descriptionReporte", length = 400, nullable = false)
    private String descriptionReporte;

    @Column(name = "contentReporte", length = 400, nullable = false)
    private String contentReporte;

    @Column(name = "actionTypeReporte", length = 50, nullable = false)
    private String actionTypeReporte;

    @Column(name = "resultReporte", length = 200, nullable = false)
    private String resultReporte;

    @Column(name = "dateReporte", nullable = false)
    private LocalDateTime dateReporte;

    public Reporte() {}

    public Reporte(int idReporte, Vulnerabilidad vulnerabilidad, String typeReporte, String descriptionReporte,
                   String contentReporte, String actionTypeReporte, String resultReporte, LocalDateTime dateReporte) {
        this.idReporte = idReporte;
        this.vulnerabilidad = vulnerabilidad;
        this.typeReporte = typeReporte;
        this.descriptionReporte = descriptionReporte;
        this.contentReporte = contentReporte;
        this.actionTypeReporte = actionTypeReporte;
        this.resultReporte = resultReporte;
        this.dateReporte = dateReporte;
    }

    public int getIdReporte() { return idReporte; }
    public void setIdReporte(int idReporte) { this.idReporte = idReporte; }

    public Vulnerabilidad getVulnerabilidad() { return vulnerabilidad; }
    public void setVulnerabilidad(Vulnerabilidad vulnerabilidad) { this.vulnerabilidad = vulnerabilidad; }

    public String getTypeReporte() { return typeReporte; }
    public void setTypeReporte(String typeReporte) { this.typeReporte = typeReporte; }

    public String getDescriptionReporte() { return descriptionReporte; }
    public void setDescriptionReporte(String descriptionReporte) { this.descriptionReporte = descriptionReporte; }

    public String getContentReporte() { return contentReporte; }
    public void setContentReporte(String contentReporte) { this.contentReporte = contentReporte; }

    public String getActionTypeReporte() { return actionTypeReporte; }
    public void setActionTypeReporte(String actionTypeReporte) { this.actionTypeReporte = actionTypeReporte; }

    public String getResultReporte() { return resultReporte; }
    public void setResultReporte(String resultReporte) { this.resultReporte = resultReporte; }

    public LocalDateTime getDateReporte() { return dateReporte; }
    public void setDateReporte(LocalDateTime dateReporte) { this.dateReporte = dateReporte; }
}