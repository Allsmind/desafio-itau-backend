package com.somer.renato.desafioitaubackend.estatistica;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/estatistica")
@Tag(name = "Estatística", description = "Estatísticas das transações dos últimos 60 segundos")
public class EstatisticaController {

    private static final Logger log = LoggerFactory.getLogger(EstatisticaController.class);

    private final EstatisticaService estatisticaService;

    public EstatisticaController(EstatisticaService estatisticaService) {
        this.estatisticaService = estatisticaService;
    }

    @GetMapping
    @Operation(summary = "Consultar estatísticas", description = "Retorna count, sum, avg, min e max das transações dos últimos 60 segundos. Sem transações no intervalo, todos os valores são 0.")
    @ApiResponse(responseCode = "200", description = "Estatísticas calculadas",
            content = @Content(schema = @Schema(implementation = EstatisticaResponse.class)))
    public ResponseEntity<EstatisticaResponse> getEstatistica() {
        log.info("Calculando estatisticas das transações ");

        final Integer INTERVALO_ESTATISTICAS = 60;
        final var horaInicial = OffsetDateTime.now()
                .minusSeconds(INTERVALO_ESTATISTICAS);

        return ResponseEntity.ok(estatisticaService.estatistica(horaInicial));
    }
}
