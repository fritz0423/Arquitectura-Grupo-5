package pe.edu.upc.safezone.dtos;

import java.time.LocalDateTime;

public class VulnerabilidadDTO {

    private int idVulnerabilidad;
    private String nameVulnerabilidad;
    private String descriptionVulnerabilidad;
    private String typeVulnerabilidad;
    private String riskLevelVulnerabilidad ;
    private LocalDateTime dateVulnerabilidad;
    private int idActividad;

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

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }
}
