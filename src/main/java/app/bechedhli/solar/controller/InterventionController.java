package app.bechedhli.solar.controller;

import app.bechedhli.solar.entity.Intervention;
import app.bechedhli.solar.entity.StatutIntervention;
import app.bechedhli.solar.exceptions.InterventionNotFoundException;
import app.bechedhli.solar.repository.InterventionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interventions")
public class InterventionController {

    private final InterventionRepository interventionRepository;

    @Autowired
    public InterventionController(InterventionRepository interventionRepository) {
        this.interventionRepository = interventionRepository;
    }

    @GetMapping("/allInterventions")
    public List<Intervention> getAllInterventions() {
        return interventionRepository.findAll();
    }

    @GetMapping("/interventions/{id}")
    public Intervention getInterventionById(@PathVariable Long id) {
        return interventionRepository.findById(id)
                .orElseThrow(() -> new InterventionNotFoundException(id));
    }

    @GetMapping("/interventionById")
    public Intervention getIntervention(@RequestParam("id") Long id) {
        return interventionRepository.findById(id)
                .orElseThrow(() -> new InterventionNotFoundException(id));
    }

@GetMapping("/interventionsByClient")
    public List<Intervention> getInterventionsByClient(@RequestParam("clientId") Long clientId) {
        return interventionRepository.findByClient_id(clientId);
    }

    @GetMapping("/interventionsByTechnicien")
    public List<Intervention> getInterventionsByTechnicien(@RequestParam("technicienId") Long technicienId) {
        return interventionRepository.findByTechnician_id(technicienId);
    }

    @GetMapping("/interventionsByStatut")
    public List<Intervention> getInterventionsByStatut(@RequestParam("statut") StatutIntervention statut) {
        return interventionRepository.findByStatut(statut);
    }

    @PostMapping("/intervention")
    public Intervention addIntervention(@RequestBody Intervention intervention) {
        return interventionRepository.save(intervention);
    }

    @PutMapping("/updateIntervention")
    public Intervention updateIntervention(@RequestBody Intervention intervention) {
        return interventionRepository.save(intervention);
    }

    @DeleteMapping("/interventions/{id}")
    public void deleteIntervention(@PathVariable Long id) {
        interventionRepository.deleteById(id);
    }
}