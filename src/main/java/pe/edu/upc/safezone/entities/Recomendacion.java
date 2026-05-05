package pe.edu.upc.safezone.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Recomendacion")
public class Recomendacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRecomendacion")
    private int idRecomendacion;

    @ManyToOne
    @JoinColumn(name = "idReporte", nullable = false)
    private Reporte reporte;

    @Column(name = "descriptionRecomendacion", length = 400, nullable = false)
    private String descriptionRecomendacion;

    @Column(name = "priorityRecomendacion", length = 50, nullable = false)
    private String priorityRecomendacion;

    @Column(name = "dateRecomendacion", nullable = false)
    private LocalDateTime dateRecomendacion;

    public Recomendacion() {}

    public Recomendacion(int idRecomendacion, Reporte reporte, String descriptionRecomendacion,
                         String priorityRecomendacion, LocalDateTime dateRecomendacion) {
        this.idRecomendacion = idRecomendacion;
        this.reporte = reporte;
        this.descriptionRecomendacion = descriptionRecomendacion;
        this.priorityRecomendacion = priorityRecomendacion;
        this.dateRecomendacion = dateRecomendacion;
    }

    public int getIdRecomendacion() { return idRecomendacion; }
    public void setIdRecomendacion(int idRecomendacion) { this.idRecomendacion = idRecomendacion; }

    public Reporte getReporte() { return reporte; }
    public void setReporte(Reporte reporte) { this.reporte = reporte; }

    public String getDescriptionRecomendacion() { return descriptionRecomendacion; }
    public void setDescriptionRecomendacion(String descriptionRecomendacion) { this.descriptionRecomendacion = descriptionRecomendacion; }

    public String getPriorityRecomendacion() { return priorityRecomendacion; }
    public void setPriorityRecomendacion(String priorityRecomendacion) { this.priorityRecomendacion = priorityRecomendacion; }

    public LocalDateTime getDateRecomendacion() { return dateRecomendacion; }
    public void setDateRecomendacion(LocalDateTime dateRecomendacion) { this.dateRecomendacion = dateRecomendacion; }
}