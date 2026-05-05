package pe.edu.upc.safezone.dtos;

public class ModuloMultimediaDTO {
    private int idModulo;
    private String titleModulo;
    private String categoryModulo;
    private Integer progressModulo;
    private int totalMultimedia;

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
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

    public int getTotalMultimedia() {
        return totalMultimedia;
    }

    public void setTotalMultimedia(int totalMultimedia) {
        this.totalMultimedia = totalMultimedia;
    }
}
