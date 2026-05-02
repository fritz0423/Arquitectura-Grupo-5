package pe.edu.upc.safezone.entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "Modulo")
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idModulo")
    private int idModulo;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @Column(name = "titleModulo", length = 100, nullable = false)
    private String titleModulo;

    @Column(name = "descriptionModulo", nullable = false)
    private String descriptionModulo;

    @Column(name = "categoryModulo", length = 50, nullable = false)
    private String categoryModulo;

    @Column(name = "progressModulo", nullable = false)
    private float progressModulo;

    @Column(name = "dateUpdateModulo", nullable = false)
    private LocalDateTime dateUpdateModulo;

    public Modulo() {
    }

    public Modulo(int idModulo, Usuario usuario, String titleModulo, String descriptionModulo, String categoryModulo, float progressModulo, LocalDateTime dateUpdateModulo) {
        this.idModulo = idModulo;
        this.usuario = usuario;
        this.titleModulo = titleModulo;
        this.descriptionModulo = descriptionModulo;
        this.categoryModulo = categoryModulo;
        this.progressModulo = progressModulo;
        this.dateUpdateModulo = dateUpdateModulo;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTitleModulo() {
        return titleModulo;
    }

    public void setTitleModulo(String titleModulo) {
        this.titleModulo = titleModulo;
    }

    public String getDescriptionModulo() {
        return descriptionModulo;
    }

    public void setDescriptionModulo(String descriptionModulo) {
        this.descriptionModulo = descriptionModulo;
    }

    public String getCategoryModulo() {
        return categoryModulo;
    }

    public void setCategoryModulo(String categoryModulo) {
        this.categoryModulo = categoryModulo;
    }

    public float getProgressModulo() {
        return progressModulo;
    }

    public void setProgressModulo(float progressModulo) {
        this.progressModulo = progressModulo;
    }

    public LocalDateTime getDateUpdateModulo() {
        return dateUpdateModulo;
    }

    public void setDateUpdateModulo(LocalDateTime dateUpdateModulo) {
        this.dateUpdateModulo = dateUpdateModulo;
    }
}