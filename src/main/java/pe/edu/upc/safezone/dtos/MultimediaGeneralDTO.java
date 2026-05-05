package pe.edu.upc.safezone.dtos;

public class MultimediaGeneralDTO {

    private int idMultimedia;
    private int idModulo;
    private String typeMultimedia;
    private String urlMultimedia;
    private int durationMultimedia;
    private String denominationMultimedia;

    public int getIdMultimedia() { return idMultimedia; }
    public void setIdMultimedia(int idMultimedia) { this.idMultimedia = idMultimedia; }

    public int getIdModulo() { return idModulo; }
    public void setIdModulo(int idModulo) { this.idModulo = idModulo; }

    public String getTypeMultimedia() { return typeMultimedia; }
    public void setTypeMultimedia(String typeMultimedia) { this.typeMultimedia = typeMultimedia; }

    public String getUrlMultimedia() { return urlMultimedia; }
    public void setUrlMultimedia(String urlMultimedia) { this.urlMultimedia = urlMultimedia; }

    public int getDurationMultimedia() { return durationMultimedia; }
    public void setDurationMultimedia(int durationMultimedia) { this.durationMultimedia = durationMultimedia; }

    public String getDenominationMultimedia() { return denominationMultimedia; }
    public void setDenominationMultimedia(String denominationMultimedia) { this.denominationMultimedia = denominationMultimedia; }
}