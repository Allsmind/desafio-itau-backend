package com.somer.renato.desafioitaubackend.estatistica;

public class EstatisticaResponse {

    private final long count;
    private final Double avg;
    private final Double max;
    private final Double min;
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
