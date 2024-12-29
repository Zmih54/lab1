import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class CarStatisticsCollector implements Collector<CarModel, List<Double>, CarStatistics> {
    @Override
    public Supplier<List<Double>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<Double>, CarModel> accumulator() {
        return (list, car) -> list.add(car.getPriceUAH());
    }

    @Override
    public BinaryOperator<List<Double>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<Double>, CarStatistics> finisher() {
        return prices -> {
            double min = Collections.min(prices);
            double max = Collections.max(prices);
            double avg = prices.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            
            double variance = prices.stream()
                    .mapToDouble(price -> Math.pow(price - avg, 2))
                    .average()
                    .orElse(0.0);
            double stdDev = Math.sqrt(variance);
            
            return new CarStatistics(min, max, avg, stdDev);
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of();
    }
}