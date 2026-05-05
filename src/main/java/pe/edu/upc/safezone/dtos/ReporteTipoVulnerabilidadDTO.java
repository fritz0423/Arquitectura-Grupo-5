package pe.edu.upc.safezone.dtos;

public class ReporteTipoVulnerabilidadDTO {
    private String vulnerabilityType;
    private int reportCount;

    public String getVulnerabilityType() {
        return vulnerabilityType;
    }

    public void setVulnerabilityType(String vulnerabilityType) {
        this.vulnerabilityType = vulnerabilityType;
    }

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }
}
