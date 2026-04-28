package app.bechedhli.solar.repository;

import app.bechedhli.solar.entity.Piece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PieceRepository extends JpaRepository<Piece, Long> {
    Optional<Piece> findByNom(String nom);
    List<Piece> findByCategorie(String categorie);
    
    @Query("SELECT p FROM Piece p WHERE p.stockMin IS NOT NULL AND p.quantite < p.stockMin")
    List<Piece> findLowStockPieces();
}