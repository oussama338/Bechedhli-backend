package app.bechedhli.solar.controller;

import app.bechedhli.solar.entity.Piece;
import app.bechedhli.solar.exceptions.PieceNotFoundException;
import app.bechedhli.solar.repository.PieceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pieces")
public class PieceController {

    private final PieceRepository pieceRepository;

    @Autowired
    public PieceController(PieceRepository pieceRepository) {
        this.pieceRepository = pieceRepository;
    }

    @GetMapping("/allPieces")
    public List<Piece> getAllPieces() {
        return pieceRepository.findAll();
    }

    @GetMapping("/pieces/{id}")
    public Piece getPieceById(@PathVariable Long id) {
        return pieceRepository.findById(id)
                .orElseThrow(() -> new PieceNotFoundException(id));
    }

    @GetMapping("/pieceByNom")
    public Piece getPieceByNom(@RequestParam("nom") String nom) {
        return pieceRepository.findByNom(nom)
                .orElseThrow(() -> new PieceNotFoundException((long) 0));
    }

    @GetMapping("/pieceByCategorie")
    public List<Piece> getPieceByCategorie(@RequestParam("categorie") String categorie) {
        return pieceRepository.findByCategorie(categorie);
    }

    @PostMapping("/piece")
    public Piece addPiece(@RequestBody Piece piece) {
        return pieceRepository.save(piece);
    }

    @PutMapping("/updatePiece")
    public Piece updatePiece(@RequestBody Piece piece) {
        return pieceRepository.save(piece);
    }

    @PutMapping("/pieces/{id}")
    public Piece replacePiece(@RequestBody Piece newPiece, @PathVariable Long id) {
        return pieceRepository.findById(id)
                .map(piece -> {
                    piece.setNom(newPiece.getNom());
                    piece.setDescription(newPiece.getDescription());
                    piece.setQuantite(newPiece.getQuantite());
                    piece.setPrixAchat(newPiece.getPrixAchat());
                    piece.setPrixVente(newPiece.getPrixVente());
                    piece.setStockMin(newPiece.getStockMin());
                    piece.setReference(newPiece.getReference());
                    piece.setCategorie(newPiece.getCategorie());
                    return pieceRepository.save(piece);
                })
                .orElseGet(() -> {
                    newPiece.setId(id);
                    return pieceRepository.save(newPiece);
                });
    }

    @DeleteMapping("/pieces/{id}")
    public void deletePiece(@PathVariable Long id) {
        pieceRepository.deleteById(id);
    }

    @GetMapping("/pieceById")
    public Piece getPiece(@RequestParam("id") Long id) {
        return pieceRepository.findById(id)
                .orElseThrow(() -> new PieceNotFoundException(id));
    }
}