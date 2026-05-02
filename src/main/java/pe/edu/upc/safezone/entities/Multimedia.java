package pe.edu.upc.safezone.entities;

import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Multimedia")
public class Multimedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMultimedia")
    private int idMultimedia;

    @ManyToOne
    @JoinColumn(name = "idModulo", nullable = false)
    private Modulo modulo;

    @Column(name = "typeMultimedia", length = 50, nullable = false)
    private String typeMultimedia;

    @Column(name = "urlMultimedia", nullable = false)
    private String urlMultimedia;

    @Column(name = "durationMultimedia", nullable = false)
    private int durationMultimedia;

    @Column(name = "denominationMultimedia", length = 100, nullable = false)
    private String denominationMultimedia;

    public Multimedia() {}

    public Multimedia(int idMultimedia, Modulo modulo, String typeMultimedia, String urlMultimedia,
                      int durationMultimedia, String denominationMultimedia) {
        this.idMultimedia = idMultimedia;
        this.modulo = modulo;
        this.typeMultimedia = typeMultimedia;
        this.urlMultimedia = urlMultimedia;
        this.durationMultimedia = durationMultimedia;
        this.denominationMultimedia = denominationMultimedia;
    }

    public int getIdMultimedia() { return idMultimedia; }
    public void setIdMultimedia(int idMultimedia) { this.idMultimedia = idMultimedia; }

    public Modulo getModulo() { return modulo; }
    public void setModulo(Modulo modulo) { this.modulo = modulo; }

    public String getTypeMultimedia() { return typeMultimedia; }
    public void setTypeMultimedia(String typeMultimedia) { this.typeMultimedia = typeMultimedia; }

    public String getUrlMultimedia() { return urlMultimedia; }
    public void setUrlMultimedia(String urlMultimedia) { this.urlMultimedia = urlMultimedia; }

    public int getDurationMultimedia() { return durationMultimedia; }
    public void setDurationMultimedia(int durationMultimedia) { this.durationMultimedia = durationMultimedia; }

    public String getDenominationMultimedia() { return denominationMultimedia; }
    public void setDenominationMultimedia(String denominationMultimedia) { this.denominationMultimedia = denominationMultimedia; }
}