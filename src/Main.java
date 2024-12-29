import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Generate 500 cars, skipping first N Toyota cars
        List<CarModel> cars = CarFilter.skipByBrandAndLimit(
                CarGenerator.generate(),
                "Toyota",
                5,
                500
        ).toList();

        // Filter by months since manufacture and group by class
        Map<CarClass, List<CarModel>> groupedByClass = cars.stream()
                .filter(car -> {
                    long months = car.getMonthsSinceManufacture();
                    return months >= 12 && months <= 36;
                })
                .collect(Collectors.groupingBy(CarModel::getCarClass));

        // Calculate statistics
        CarStatistics statistics = cars.stream()
                .collect(new CarStatisticsCollector());
        System.out.println("Car Statistics: " + statistics);

        // Calculate quartiles and outliers
        List<Double> prices = cars.stream()
                .map(CarModel::getPriceUAH)
                .sorted()
                .collect(Collectors.toList());

        int size = prices.size();
        double q1 = prices.get(size / 4);
        double q3 = prices.get(3 * size / 4);
        double iqr = q3 - q1;
        double lowerBound = q1 - 1.5 * iqr;
        double upperBound = q3 + 1.5 * iqr;

        Map<String, Long> outlierStats = cars.stream()
                .collect(Collectors.groupingBy(
                        car -> {
                            double price = car.getPriceUAH();
                            return (price < lowerBound || price > upperBound) ? "outliers" : "data";
                        },
                        Collectors.counting()
                ));

        System.out.println("Outlier Statistics: " + outlierStats);
    }
}