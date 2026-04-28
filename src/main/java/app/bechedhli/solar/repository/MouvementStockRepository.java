package app.bechedhli.solar.repository;

import app.bechedhli.solar.entity.MouvementStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MouvementStockRepository extends JpaRepository<MouvementStock, Long> {
    List<MouvementStock> findByPiece_id(Long pieceId);
    List<MouvementStock> findByUtilisateur_id(Long utilisateurId);
}