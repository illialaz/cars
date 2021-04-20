package by.laziuk.cars.impl;

import by.laziuk.cars.annotations.construct;

import java.util.Comparator;
import java.util.Map;

public class SportCar extends Vehicle implements Comparable<SportCar> {

    final private static String QUARTERMILETIME = "quarterMileTime";

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
                    String id,
                    int quarterMileTime,
                    String type,
                    Statistics statistics) {
        super(country, company, model, weight, numWheels, maxSpeed, cost, id, type, statistics);
        this.quarterMileTime = quarterMileTime;
    }

    public SportCar(Vehicle vehicle) {
        super(vehicle);
        quarterMileTime = 0;
    }

    @Override
    public void setType(String type) { this.type = "sportcar"; }

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
