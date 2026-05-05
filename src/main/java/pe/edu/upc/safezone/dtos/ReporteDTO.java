package pe.edu.upc.safezone.dtos;

import java.time.LocalDateTime;

public class ReporteDTO {

    private int idReporte;
    private int idVulnerabilidad;
    private String typeReporte;
    private String descriptionReporte;
    private String contentReporte;
    private String actionTypeReporte;
    private String resultReporte;
    private LocalDateTime dateReporte;

    public int getIdReporte() { return idReporte; }
    public void setIdReporte(int idReporte) { this.idReporte = idReporte; }

    public int getIdVulnerabilidad() { return idVulnerabilidad; }
    public void setIdVulnerabilidad(int idVulnerabilidad) { this.idVulnerabilidad = idVulnerabilidad; }

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