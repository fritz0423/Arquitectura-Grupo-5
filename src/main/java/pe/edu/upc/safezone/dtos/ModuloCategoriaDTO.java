package pe.edu.upc.safezone.dtos;

public class ModuloCategoriaDTO {
    private Integer idModulo;
    private String titleModulo;
    private String categoryModulo;
    private Integer progressModulo;

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public String getTitleModulo() {
        return titleModulo;
    }

    public void setTitleModulo(String titleModulo) {
        this.titleModulo = titleModulo;
    }

    public String getCategoryModulo() {
        return categoryModulo;
    }

    public void setCategoryModulo(String categoryModulo) {
        this.categoryModulo = categoryModulo;
    }

    public Integer getProgressModulo() {
        return progressModulo;
    }

    public void setProgressModulo(Integer progressModulo) {
        this.progressModulo = progressModulo;
    }
}
