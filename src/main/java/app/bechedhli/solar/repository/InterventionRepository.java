package app.bechedhli.solar.repository;

import app.bechedhli.solar.entity.Intervention;
import app.bechedhli.solar.entity.StatutIntervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterventionRepository extends JpaRepository<Intervention, Long> {
    List<Intervention> findByClient_id(Long clientId);
    List<Intervention> findByTechnician_id(Long technicianId);
    List<Intervention> findByStatut(StatutIntervention statut);
}