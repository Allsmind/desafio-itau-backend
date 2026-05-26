package com.somer.renato.desafioitaubackend.estatistica;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Estatísticas das transações dos últimos 60 segundos")
public class EstatisticaResponse {

    @Schema(description = "Quantidade de transações no intervalo", example = "10")
    private final long count;

    @Schema(description = "Média dos valores", example = "123.456")
    private final Double avg;

    @Schema(description = "Maior valor", example = "123.56")
    private final Double max;

    @Schema(description = "Menor valor", example = "12.34")
    private final Double min;

    @Schema(description = "Soma dos valores", example = "1234.56")
    private final Double sum;

    public EstatisticaResponse(long count, Double avg, Double max, Double min, Double sum) {
        this.count = count;
        this.avg = avg;
        this.max = max;
        this.min = min;
        this.sum = sum;
    }

    public long getCount() {
        return count;
    }

    public Double getAvg() {
        return avg;
    }

    public Double getMax() {
        return max;
    }

    public Double getMin() {
        return min;
    }

    public Double getSum() {
        return sum;
    }
}
