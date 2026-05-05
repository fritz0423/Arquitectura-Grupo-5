package pe.edu.upc.safezone.dtos;

public class ModuloAvanzadoDTO {
    private int idModulo;
    private String titleModulo;
    private String descriptionModulo;
    private String categoryModulo;
    private float progressModulo;

    public int getIdModulo() { return idModulo; }
    public void setIdModulo(int idModulo) { this.idModulo = idModulo; }

    public String getTitleModulo() { return titleModulo; }
    public void setTitleModulo(String titleModulo) { this.titleModulo = titleModulo; }

    public String getDescriptionModulo() { return descriptionModulo; }
    public void setDescriptionModulo(String descriptionModulo) { this.descriptionModulo = descriptionModulo; }

    public String getCategoryModulo() { return categoryModulo; }
    public void setCategoryModulo(String categoryModulo) { this.categoryModulo = categoryModulo; }

    public float getProgressModulo() { return progressModulo; }
    public void setProgressModulo(float progressModulo) { this.progressModulo = progressModulo; }
}
