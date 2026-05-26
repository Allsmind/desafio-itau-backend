package com.somer.renato.desafioitaubackend.transacao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Validated
@Schema(description = "Requisição de transação")
public class TransacaoRequest {

    @Schema(description = "Valor da transação (>= 0)", example = "123.45")
    @NotNull(message = "Erro: Campo valor nulo")
    @Positive(message = "Erro: valor da transação menor ou igual a zero")
    private BigDecimal valor;

    @Schema(description = "Data e hora da transação (ISO 8601)", example = "2020-08-07T12:34:56.789-03:00")
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
