package app.bechedhli.solar.graphql;

import app.bechedhli.solar.entity.Intervention;
import app.bechedhli.solar.entity.StatutIntervention;
import app.bechedhli.solar.entity.User;
import app.bechedhli.solar.exceptions.InterventionNotFoundException;
import app.bechedhli.solar.exceptions.UserNotFoundException;
import app.bechedhli.solar.repository.InterventionRepository;
import app.bechedhli.solar.repository.UserRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class InterventionGraphQLController {

    private final InterventionRepository interventionRepository;
    private final UserRepository userRepository;

    public InterventionGraphQLController(InterventionRepository interventionRepository,
                                        UserRepository userRepository) {
        this.interventionRepository = interventionRepository;
        this.userRepository = userRepository;
    }

    @QueryMapping
    public List<Intervention> allInterventions() {
        return interventionRepository.findAll();
    }

    @QueryMapping
    public Intervention interventionById(@Argument Long id) {
        return interventionRepository.findById(id)
                .orElseThrow(() -> new InterventionNotFoundException(id));
    }

    @QueryMapping
    public List<Intervention> interventionsByClient(@Argument Long clientId) {
        return interventionRepository.findByClient_id(clientId);
    }

    @QueryMapping
    public List<Intervention> interventionsByTechnician(@Argument Long technicianId) {
        return interventionRepository.findByTechnician_id(technicianId);
    }

    @QueryMapping
    public List<Intervention> interventionsByStatut(@Argument String statut) {
        return interventionRepository.findByStatut(StatutIntervention.valueOf(statut.toUpperCase()));
    }

    @MutationMapping
    public Intervention createIntervention(@Argument Long clientId,
                                          @Argument Long technicianId,
                                          @Argument LocalDate datePrevue,
                                          @Argument String description,
                                          @Argument String adresse,
                                          @Argument Double prix) {
        User client = userRepository.findById(clientId)
                .orElseThrow(() -> new UserNotFoundException(clientId));

        User technician = null;
        if (technicianId != null) {
            technician = userRepository.findById(technicianId)
                    .orElseThrow(() -> new UserNotFoundException(technicianId));
        }

        Intervention intervention = Intervention.builder()
                .client(client)
                .technician(technician)
                .datePrevue(datePrevue)
                .description(description)
                .adresse(adresse)
                .prix(prix)
                .statut(StatutIntervention.EN_ATTENTE)
                .build();

        return interventionRepository.save(intervention);
    }

    @MutationMapping
    public Intervention startIntervention(@Argument Long id) {
        Intervention intervention = interventionRepository.findById(id)
                .orElseThrow(() -> new InterventionNotFoundException(id));

        if (intervention.getStatut() != StatutIntervention.EN_ATTENTE) {
            throw new RuntimeException("Cannot start intervention");
        }

        intervention.setStatut(StatutIntervention.EN_COURS);
        intervention.setDateDebut(LocalDateTime.now());
        return interventionRepository.save(intervention);
    }

    @MutationMapping
    public Intervention finishIntervention(@Argument Long id) {
        Intervention intervention = interventionRepository.findById(id)
                .orElseThrow(() -> new InterventionNotFoundException(id));

        if (intervention.getStatut() != StatutIntervention.EN_COURS) {
            throw new RuntimeException("Cannot finish intervention");
        }

        intervention.setStatut(StatutIntervention.TERMINEE);
        intervention.setDateFin(LocalDateTime.now());
        return interventionRepository.save(intervention);
    }

    @MutationMapping
    public Boolean deleteIntervention(@Argument Long id) {
        if (!interventionRepository.existsById(id)) {
            throw new InterventionNotFoundException(id);
        }
        interventionRepository.deleteById(id);
        return true;
    }
}