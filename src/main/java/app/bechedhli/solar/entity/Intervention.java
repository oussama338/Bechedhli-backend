package app.bechedhli.solar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Intervention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENT")
    private User client;

    @ManyToOne
    @JoinColumn(name = "ID_TECHNICIEN")
    private User technician;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutIntervention statut;

    private LocalDate datePrevue;

    private LocalDateTime dateDebut;

    private LocalDateTime dateFin;

    @Column(length = 1000)
    private String description;

    private String adresse;

    private Double prix;

    @PrePersist
    protected void onCreate() {
        if (statut == null) {
            statut = StatutIntervention.EN_ATTENTE;
        }
    }
}