package com.somer.renato.desafioitaubackend.transacao;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Validated
public class TransacaoRequest {
    @NotNull(message = "Erro: Campo valor nulo")
    @Positive(message = "Erro: valor da transação menor ou igual a zero")
    private BigDecimal valor;
    @NotNull(message = "Erro: Campo dataHora nulo")
    @PastOrPresent(message = "Erro: DataHora futura")
    private OffsetDateTime dataHora;

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        return "TransacaoRequest{" +
                "valor=" + valor +
                ", dataHora=" + dataHora +
                '}';
    }
}
