package app.bechedhli.solar.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "contrats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String typeContrat;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private Double salaireBrut;

    private String description;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
    }
}