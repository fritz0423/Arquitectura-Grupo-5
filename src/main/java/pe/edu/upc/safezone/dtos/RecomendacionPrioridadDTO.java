package pe.edu.upc.safezone.dtos;

public class RecomendacionPrioridadDTO {
    private String priority;
    private int recomendationCount;

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getRecomendationCount() {
        return recomendationCount;
    }

    public void setRecomendationCount(int recomendationCount) {
        this.recomendationCount = recomendationCount;
    }
}
