package com.somer.renato.desafioitaubackend.estatistica;

import com.somer.renato.desafioitaubackend.transacao.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class EstatisticaService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    public EstatisticaResponse estatistica(OffsetDateTime dataHoraInicial) {
        if (transacaoRepository.getListaDeTransacoes().isEmpty()) {
            return new EstatisticaResponse(0, 0.0, 0.0, 0.0, 0.0);
        }
        final var summary = transacaoRepository.getListaDeTransacoes().stream()
                .filter(t ->
                        t.getDataHora().isAfter(dataHoraInicial) || t.getDataHora().isEqual(dataHoraInicial))
                .mapToDouble(t -> t.getValor().doubleValue())
                .summaryStatistics();
        return new EstatisticaResponse(
                summary.getCount(),
                summary.getAverage(),
                summary.getMax(),
                summary.getMin(),
                summary.getSum()
        );
    }

}
