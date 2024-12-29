public class CarStatistics {
    private double minPrice;
    private double maxPrice;
    private double avgPrice;
    private double stdDeviation;

    public CarStatistics(double minPrice, double maxPrice, double avgPrice, double stdDeviation) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.avgPrice = avgPrice;
        this.stdDeviation = stdDeviation;
    }

    // Getters
    public double getMinPrice() { return minPrice; }
    public double getMaxPrice() { return maxPrice; }
    public double getAvgPrice() { return avgPrice; }
    public double getStdDeviation() { return stdDeviation; }

    @Override
    public String toString() {
        return String.format("Statistics{min=%.2f, max=%.2f, avg=%.2f, stdDev=%.2f}",
                minPrice, maxPrice, avgPrice, stdDeviation);
    }
}