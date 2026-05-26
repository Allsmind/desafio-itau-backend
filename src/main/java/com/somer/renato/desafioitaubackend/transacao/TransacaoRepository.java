package com.somer.renato.desafioitaubackend.transacao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransacaoRepository {

    private static final Logger log = LoggerFactory.getLogger(TransacaoRepository.class);
    List<TransacaoRequest> listaDeTransacoes = new ArrayList<>();

    //SALVA A TRANSACAO RECEBIDA NA LISTA
    public void salvaTransacao(TransacaoRequest transacaoRequest) {
        listaDeTransacoes.add(transacaoRequest);
        log.info("Salvando transação");
        log.info(Integer.toString(listaDeTransacoes.size()));
    }

    //REMOVE TODAS AS TRANSAÇÕES DA LISTA
    public void deletaTransacao() {
        log.info("Removendo todas as transaçãoes");
        listaDeTransacoes.clear();
    }
}
