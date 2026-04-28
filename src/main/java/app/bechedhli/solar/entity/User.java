package app.bechedhli.solar.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    private String telephone;

    @Column(nullable = false)
    private boolean actif = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "contrat_id")
    private Contrat contrat;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDate.now();
    }

    public enum Role {
        ADMIN,
        TECHNICIEN,
        CLIENT
    }
}