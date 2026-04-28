package app.bechedhli.solar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MouvementStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_PIECE")
    private Piece piece;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private User utilisateur;

    @Column(nullable = false)
    private Integer quantite;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeMouvement type;

    @Column(nullable = false)
    private LocalDateTime date;

    private String motif;

    @PrePersist
    protected void onCreate() {
        date = LocalDateTime.now();
    }
}