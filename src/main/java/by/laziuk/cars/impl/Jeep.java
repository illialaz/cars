package by.laziuk.cars.impl;

import by.laziuk.cars.annotations.construct;

import java.util.Comparator;
import java.util.Map;

public class Jeep extends Vehicle implements Comparable<Jeep> {

final static private String CLEARANCE = "clearance";

    private int clearance;

    public Jeep() {
        super();
        this.clearance = 0;
    }

    public Jeep(Vehicle vehicle) {
        super(vehicle);
        clearance = 0;
    }

    @construct
    public Jeep(Map<String, String> data) {
        super(data);
        this.clearance = Integer.parseInt(data.get(CLEARANCE));
    }

    public Jeep(String country,
                String company,
                String model,
                int weight,
                int numWheels,
                int maxSpeed,
                int cost,
                String id,
                int clearance,
                String type,
                Statistics statistics) {
        super(country, company, model, weight, numWheels, maxSpeed, cost, id, type, statistics);
        this.clearance = clearance;
    }

    public int getClearance() {
        return clearance;
    }

    public void setClearance(int clearance) {
        this.clearance = clearance;
    }

    @Override
    public void setType(String type) { this.type = "jeep"; }

    public static Comparator<Jeep> getComparatorJeeps(String SortType) {
        return (a, b) -> { if(SortType.equals(CLEARANCE)) {
            return a.clearance - b.clearance;
        }
            return 0;
        };
    }

    @Override
    public int compareTo(Jeep o) {
        return Integer.compare(this.getClearance(), o.getClearance());
    }

    @Override
    public String toString() {
        return "\nJeep{" +
                "\n\tweight=" + getWeight() +
                "\n\tnumWheels=" + getNumWheels() +
                "\n\tmaxSpeed=" + getMaxSpeed() +
                "\n\tcost=" + getCost() +
                "\n\tmodel='" + getModel() + '\'' +
                "\n\tcompany='" + getCompany() + '\'' +
                "\n\tcountry='" + getCountry() + '\'' +
                getStatistics() +
                "\n\tclearance=" + clearance +
                "\n}";
    }
}
