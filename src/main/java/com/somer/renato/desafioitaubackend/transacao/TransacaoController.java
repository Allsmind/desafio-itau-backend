package com.somer.renato.desafioitaubackend.transacao;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
@Tag(name = "Transação", description = "Recebimento e limpeza de transações")
public class TransacaoController {

    private static final Logger log = LoggerFactory.getLogger(TransacaoController.class);

    private final TransacaoRepository transacaoRepository;

    public TransacaoController(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @PostMapping
    @Operation(summary = "Registrar transação", description = "Aceita transações com valor >= 0 e dataHora no passado ou presente (ISO 8601).")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Transação registrada", content = @Content),
            @ApiResponse(responseCode = "400", description = "JSON inválido", content = @Content),
            @ApiResponse(responseCode = "422", description = "Transação rejeitada pela validação", content = @Content)
    })
    public ResponseEntity<Void> createTransacao(
            @Valid @RequestBody @Schema(description = "Dados da transação") TransacaoRequest transacaoRequest) {
        try {
            log.info("Transação recebida");
            transacaoRepository.salvaTransacao(transacaoRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            log.error("Erro no recebimento da transação", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    @Operation(summary = "Limpar transações", description = "Remove todas as transações armazenadas em memória.")
    @ApiResponse(responseCode = "200", description = "Transações removidas", content = @Content)
    public ResponseEntity<Void> deleteTransacao() {
        try {
            log.info("Removendo transações");
            transacaoRepository.deletaTransacao();
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.unprocessableContent().build();
        }
    }

}
