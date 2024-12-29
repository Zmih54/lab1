import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CarModel {
    private String brand;
    private String model;
    private LocalDate manufactureDate;
    private CarClass carClass;
    private double priceUAH;

    public CarModel(String brand, String model, LocalDate manufactureDate, 
                   CarClass carClass, double priceUAH) {
        this.brand = brand;
        this.model = model;
        this.manufactureDate = manufactureDate;
        this.carClass = carClass;
        this.priceUAH = priceUAH;
    }

    // Getters and setters
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public LocalDate getManufactureDate() { return manufactureDate; }
    public CarClass getCarClass() { return carClass; }
    public double getPriceUAH() { return priceUAH; }
    
    public long getMonthsSinceManufacture() {
        return ChronoUnit.MONTHS.between(manufactureDate, LocalDate.now());
    }

    @Override
    public String toString() {
        return String.format("CarModel{brand='%s', model='%s', manufactureDate=%s, class=%s, price=%.2f UAH}",
                brand, model, manufactureDate, carClass, priceUAH);
    }
}