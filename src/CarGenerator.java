import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Stream;

public class CarGenerator {
    private static final String[] BRANDS = {"Toyota", "Honda", "BMW", "Mercedes", "Audi", "Volkswagen"};
    private static final String[][] MODELS = {
        {"Corolla", "Camry", "RAV4"}, // Toyota
        {"Civic", "Accord", "CR-V"}, // Honda
        {"3 Series", "5 Series", "X5"}, // BMW
        {"C-Class", "E-Class", "GLC"}, // Mercedes
        {"A4", "A6", "Q5"}, // Audi
        {"Golf", "Passat", "Tiguan"} // Volkswagen
    };
    private static final Random random = new Random();

    public static Stream<CarModel> generate() {
        return Stream.generate(() -> {
            int brandIndex = random.nextInt(BRANDS.length);
            String brand = BRANDS[brandIndex];
            String model = MODELS[brandIndex][random.nextInt(MODELS[brandIndex].length)];
            
            LocalDate manufactureDate = LocalDate.now().minusMonths(random.nextInt(60));
            CarClass carClass = CarClass.values()[random.nextInt(CarClass.values().length)];
            
            // Generate realistic price between 300,000 and 2,000,000 UAH
            double price = 300000 + random.nextDouble() * 1700000;
            
            return new CarModel(brand, model, manufactureDate, carClass, price);
        });
    }
}