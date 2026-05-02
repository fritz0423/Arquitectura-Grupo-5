package pe.edu.upc.safezone.dtos;

public class MultimediaListDTO {

    private String denominationMultimedia;
    private String typeMultimedia;
    private String urlMultimedia;
    private int durationMultimedia;

    public String getDenominationMultimedia() { return denominationMultimedia; }
    public void setDenominationMultimedia(String denominationMultimedia) { this.denominationMultimedia = denominationMultimedia; }

    public String getTypeMultimedia() { return typeMultimedia; }
    public void setTypeMultimedia(String typeMultimedia) { this.typeMultimedia = typeMultimedia; }

    public String getUrlMultimedia() { return urlMultimedia; }
    public void setUrlMultimedia(String urlMultimedia) { this.urlMultimedia = urlMultimedia; }

    public int getDurationMultimedia() { return durationMultimedia; }
    public void setDurationMultimedia(int durationMultimedia) { this.durationMultimedia = durationMultimedia; }
}