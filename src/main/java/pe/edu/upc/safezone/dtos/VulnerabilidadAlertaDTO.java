package pe.edu.upc.safezone.dtos;

public class VulnerabilidadAlertaDTO {
    private int idVulnerabilidad;
    private String nameVulnerabilidad;
    private String riskLevelVulnerabilidad;
    private String typeVulnerabilidad;
    private int totalAlertas;

    public int getIdVulnerabilidad() { return idVulnerabilidad; }
    public void setIdVulnerabilidad(int idVulnerabilidad) { this.idVulnerabilidad = idVulnerabilidad; }

    public String getNameVulnerabilidad() { return nameVulnerabilidad; }
    public void setNameVulnerabilidad(String nameVulnerabilidad) { this.nameVulnerabilidad = nameVulnerabilidad; }

    public String getRiskLevelVulnerabilidad() { return riskLevelVulnerabilidad; }
    public void setRiskLevelVulnerabilidad(String riskLevelVulnerabilidad) { this.riskLevelVulnerabilidad = riskLevelVulnerabilidad; }

    public String getTypeVulnerabilidad() { return typeVulnerabilidad; }
    public void setTypeVulnerabilidad(String typeVulnerabilidad) { this.typeVulnerabilidad = typeVulnerabilidad; }

    public int getTotalAlertas() { return totalAlertas; }
    public void setTotalAlertas(int totalAlertas) { this.totalAlertas = totalAlertas; }
}
