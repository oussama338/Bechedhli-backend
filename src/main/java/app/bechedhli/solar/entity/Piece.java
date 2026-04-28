package app.bechedhli.solar.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "pieces")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Piece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    private String description;

    @Column(nullable = false)
    private Integer quantite = 0;

    @Column(name = "prix_achat", nullable = false)
    private BigDecimal prixAchat;

    @Column(name = "prix_vente", nullable = false)
    private BigDecimal prixVente;

    @Column(name = "stock_min")
    private Integer stockMin = 10;

    private String reference;

    private String categorie;
}