package pe.edu.upc.safezone.dtos;

import java.time.LocalDateTime;

public class AlertaDTO {

    private int idAlerta;
    private int idVulnerabilidad;
    private String typeAlerta;
    private String descriptionAlerta;
    private String riskLevelAlerta;
    private String messageAlerta;
    private boolean statusAlerta;
    private LocalDateTime dateAlerta;

    public int getIdAlerta() { return idAlerta; }
    public void setIdAlerta(int idAlerta) { this.idAlerta = idAlerta; }

    public int getIdVulnerabilidad() { return idVulnerabilidad; }
    public void setIdVulnerabilidad(int idVulnerabilidad) { this.idVulnerabilidad = idVulnerabilidad; }

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