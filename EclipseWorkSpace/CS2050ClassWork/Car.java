// Car class represents a single car with year, price, manufacturer, and model
class Car {
    int year;
    double price;
    String manufacturer;
    String model;

    public Car(int year, double price, String manufacturer, String model) {
        this.year = year;
        this.price = price;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    // Returns a string representation of the car
    public String toString() {
        return manufacturer + " " + model + " " + year + " - $" + price;
    }
}
