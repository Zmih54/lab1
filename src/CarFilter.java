import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class CarFilter {
    public static Stream<CarModel> skipByBrandAndLimit(Stream<CarModel> stream, String brand, int skipCount, int limit) {
        AtomicInteger skipped = new AtomicInteger(0);

        return stream
                .filter(car -> !car.getBrand().equals(brand) || skipped.incrementAndGet() > skipCount)
                .limit(limit);
    }
}