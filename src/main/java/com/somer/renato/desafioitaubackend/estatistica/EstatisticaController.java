package com.somer.renato.desafioitaubackend.estatistica;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    private static final Logger log = LoggerFactory.getLogger(EstatisticaController.class);

    @Autowired
    private EstatisticaService estatisticaService;

    @GetMapping
    public ResponseEntity<EstatisticaResponse> getEstatistica() {
        log.info("Calculando estatisticas das transações ");

        final Integer INTERVALO_ESTATISTICAS = 60;
        final var horaInicial = OffsetDateTime.now()
                .minusSeconds(INTERVALO_ESTATISTICAS);

        return ResponseEntity.ok(estatisticaService.estatistica(horaInicial));
    }
}
