package pe.edu.upc.safezone.dtos;

public class ModuloListDTO {

    private String titleModulo;
    private String categoryModulo;
    private float progressModulo;

    public String getTitleModulo() { return titleModulo; }
    public void setTitleModulo(String titleModulo) { this.titleModulo = titleModulo; }

    public String getCategoryModulo() { return categoryModulo; }
    public void setCategoryModulo(String categoryModulo) { this.categoryModulo = categoryModulo; }

    public float getProgressModulo() { return progressModulo; }
    public void setProgressModulo(float progressModulo) { this.progressModulo = progressModulo; }
}