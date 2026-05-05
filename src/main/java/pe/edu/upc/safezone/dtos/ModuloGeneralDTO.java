package pe.edu.upc.safezone.dtos;

import java.time.LocalDateTime;

public class ModuloGeneralDTO {

    private int idModulo;
    private int idUsuario;
    private String titleModulo;
    private String descriptionModulo;
    private String categoryModulo;
    private float progressModulo;
    private LocalDateTime dateUpdateModulo;

    public int getIdModulo() { return idModulo; }
    public void setIdModulo(int idModulo) { this.idModulo = idModulo; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getTitleModulo() { return titleModulo; }
    public void setTitleModulo(String titleModulo) { this.titleModulo = titleModulo; }

    public String getDescriptionModulo() { return descriptionModulo; }
    public void setDescriptionModulo(String descriptionModulo) { this.descriptionModulo = descriptionModulo; }

    public String getCategoryModulo() { return categoryModulo; }
    public void setCategoryModulo(String categoryModulo) { this.categoryModulo = categoryModulo; }

    public float getProgressModulo() { return progressModulo; }
    public void setProgressModulo(float progressModulo) { this.progressModulo = progressModulo; }

    public LocalDateTime getDateUpdateModulo() { return dateUpdateModulo; }
    public void setDateUpdateModulo(LocalDateTime dateUpdateModulo) { this.dateUpdateModulo = dateUpdateModulo; }
}