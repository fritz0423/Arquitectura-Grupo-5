package pe.edu.upc.safezone.dtos;

import java.time.LocalDateTime;

public class RecomendacionDTO {

    private int idRecomendacion;
    private int idReporte;
    private String descriptionRecomendacion;
    private String priorityRecomendacion;
    private LocalDateTime dateRecomendacion;

    public int getIdRecomendacion() { return idRecomendacion; }
    public void setIdRecomendacion(int idRecomendacion) { this.idRecomendacion = idRecomendacion; }

    public int getIdReporte() { return idReporte; }
    public void setIdReporte(int idReporte) { this.idReporte = idReporte; }

    public String getDescriptionRecomendacion() { return descriptionRecomendacion; }
    public void setDescriptionRecomendacion(String descriptionRecomendacion) { this.descriptionRecomendacion = descriptionRecomendacion; }

    public String getPriorityRecomendacion() { return priorityRecomendacion; }
    public void setPriorityRecomendacion(String priorityRecomendacion) { this.priorityRecomendacion = priorityRecomendacion; }

    public LocalDateTime getDateRecomendacion() { return dateRecomendacion; }
    public void setDateRecomendacion(LocalDateTime dateRecomendacion) { this.dateRecomendacion = dateRecomendacion; }
}