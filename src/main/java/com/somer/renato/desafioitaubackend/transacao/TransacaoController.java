package com.somer.renato.desafioitaubackend.transacao;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private static final Logger log = LoggerFactory.getLogger(TransacaoController.class);

    private final TransacaoRepository transacaoRepository;

    public TransacaoController(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @PostMapping
    public ResponseEntity createTransacao(@Valid @RequestBody TransacaoRequest transacaoRequest) {
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
    public ResponseEntity deleteTransacao() {
        try {
            log.info("Removendo transações");
            transacaoRepository.deletaTransacao();
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.unprocessableContent().build();
        }
    }

}
