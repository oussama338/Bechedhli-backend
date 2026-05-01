package app.bechedhli.solar.graphql;

import app.bechedhli.solar.entity.Piece;
import app.bechedhli.solar.exceptions.PieceNotFoundException;
import app.bechedhli.solar.repository.PieceRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class PieceGraphQLController {

    private final PieceRepository pieceRepository;

    public PieceGraphQLController(PieceRepository pieceRepository) {
        this.pieceRepository = pieceRepository;
    }

    @QueryMapping
    public List<Piece> allPieces() {
        return pieceRepository.findAll();
    }

    @QueryMapping
    public Piece pieceById(@Argument Long id) {
        return pieceRepository.findById(id)
                .orElseThrow(() -> new PieceNotFoundException(id));
    }

    @QueryMapping
    public List<Piece> piecesByCategorie(@Argument String categorie) {
        return pieceRepository.findByCategorie(categorie);
    }

    @QueryMapping
    public List<Piece> lowStockPieces() {
        return pieceRepository.findLowStockPieces();
    }

    @MutationMapping
    public Piece createPiece(@Argument String nom,
                            @Argument String description,
                            @Argument Integer quantite,
                            @Argument BigDecimal prixAchat,
                            @Argument BigDecimal prixVente,
                            @Argument Integer stockMin,
                            @Argument String reference,
                            @Argument String categorie) {
        Piece piece = Piece.builder()
                .nom(nom)
                .description(description)
                .quantite(quantite != null ? quantite : 0)
                .prixAchat(prixAchat)
                .prixVente(prixVente)
                .stockMin(stockMin != null ? stockMin : 10)
                .reference(reference)
                .categorie(categorie)
                .build();
        return pieceRepository.save(piece);
    }

    @MutationMapping
    public Piece updatePiece(@Argument Long id,
                            @Argument String nom,
                            @Argument String description,
                            @Argument Integer quantite,
                            @Argument BigDecimal prixAchat,
                            @Argument BigDecimal prixVente,
                            @Argument Integer stockMin,
                            @Argument String reference,
                            @Argument String categorie) {
        Piece existing = pieceRepository.findById(id)
                .orElseThrow(() -> new PieceNotFoundException(id));

        if (nom != null) existing.setNom(nom);
        if (description != null) existing.setDescription(description);
        if (quantite != null) existing.setQuantite(quantite);
        if (prixAchat != null) existing.setPrixAchat(prixAchat);
        if (prixVente != null) existing.setPrixVente(prixVente);
        if (stockMin != null) existing.setStockMin(stockMin);
        if (reference != null) existing.setReference(reference);
        if (categorie != null) existing.setCategorie(categorie);

        return pieceRepository.save(existing);
    }

    @MutationMapping
    public Boolean deletePiece(@Argument Long id) {
        if (!pieceRepository.existsById(id)) {
            throw new PieceNotFoundException(id);
        }
        pieceRepository.deleteById(id);
        return true;
    }
}