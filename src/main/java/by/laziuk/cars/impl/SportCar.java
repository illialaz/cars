package by.laziuk.cars.impl;

import by.laziuk.cars.annotations.construct;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Comparator;
import java.util.Map;

@Entity
@dev.morphia.annotations.Entity("sportcars")
@Table(name = "sportcars")
public class SportCar extends Vehicle implements Comparable<SportCar> {

    final public static String QUARTERMILETIME = "quarterMileTime";

    private int quarterMileTime;

    public SportCar() {
        super();
        this.quarterMileTime = 0;
    }

    @construct
    public SportCar(Map<String, String> data) {
        super(data);
        this.quarterMileTime = Integer.parseInt(data.get(QUARTERMILETIME));
    }

    public SportCar(String country,
                    String company,
                    String model,
                    int weight,
                    int numWheels,
                    int maxSpeed,
                    int cost,
                    int quarterMileTime,
                    Statistics statistics) {
        super(country, company, model, weight, numWheels, maxSpeed, cost, statistics);
        this.quarterMileTime = quarterMileTime;
    }

    public SportCar(Vehicle vehicle) {
        super(vehicle);
        quarterMileTime = 0;
    }

    @Column(name = "quarter")
    public int getQuarterMileTime() {
        return quarterMileTime;
    }

    public void setQuarterMileTime(int quarterMileTime) {
        this.quarterMileTime = quarterMileTime;
    }

    public static Comparator<SportCar> getComparatorSportCars(String SortType) {
        return (a, b) -> { if(SortType.equals(QUARTERMILETIME)) {
            return a.quarterMileTime - b.quarterMileTime;
        }
            return 0;
        };
    }

    @Override
    public int compareTo(SportCar o) {
        return Integer.compare(this.getQuarterMileTime(), o.getQuarterMileTime());
    }

    @Override
    public String toString() {
        return "\nSportCar{" +
                "\n\tweight=" + getWeight() +
                "\n\tnumWheels=" + getNumWheels() +
                "\n\tmaxSpeed=" + getMaxSpeed() +
                "\n\tcost=" + getCost() +
                "\n\tmodel='" + getModel() + '\'' +
                "\n\tcompany='" + getCompany() + '\'' +
                "\n\tcountry='" + getCountry() + '\'' +
                getStatistics() +
                "\n\tquarterMileTime=" + quarterMileTime +
                "\n}";
    }
}
