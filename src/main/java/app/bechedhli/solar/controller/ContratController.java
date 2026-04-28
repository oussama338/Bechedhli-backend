package app.bechedhli.solar.controller;

import app.bechedhli.solar.entity.Contrat;
import app.bechedhli.solar.repository.ContratRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contrats")
@RequiredArgsConstructor
public class ContratController {

    private final ContratRepository contratService;


}