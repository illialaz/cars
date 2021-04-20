package by.laziuk.cars.impl;


import by.laziuk.cars.annotations.construct;

import java.util.Comparator;
import java.util.Map;

public class Minivan extends Vehicle implements Comparable<Minivan> {

    final static private String SEATS = "seats";

    private int seats;

    public Minivan() {
        super();
        this.seats = 0;
    }

    public Minivan(Vehicle vehicle) {
        super(vehicle);
        seats = 0;
    }

    @construct
    public Minivan(Map<String, String> data) {
        super(data);
        this.seats = Integer.parseInt(data.get(SEATS));
    }

    public Minivan(String country,
                   String company,
                   String model,
                   int weight,
                   int numWheels,
                   int maxSpeed,
                   int cost,
                   int seats,
                   String id,
                   String type,
                   Statistics statistics) {
        super(country, company, model, weight, numWheels, maxSpeed, cost, id, type, statistics);
        this.seats = seats;
    }

    public static Comparator<Minivan> getComparatorMinivan(String SortType) {
        return (a, b) -> { if(SortType.equals(SEATS)) {
            return a.seats - b.seats;
        }
        return 0;
        };
    }

    @Override
    public void setType(String type) { this.type = "minivan"; }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public int compareTo(Minivan o) {
        return Integer.compare(this.getSeats(), o.getSeats());
    }

    @Override
    public String toString() {
        return "\nMinivan{" +
                "\n\tweight=" + getWeight() +
                "\n\tnumWheels=" + getNumWheels() +
                "\n\tmaxSpeed=" + getMaxSpeed() +
                "\n\tcost=" + getCost() +
                "\n\tmodel='" + getModel() + '\'' +
                "\n\tcompany='" + getCompany() + '\'' +
                "\n\tcountry='" + getCountry() + '\'' +
                getStatistics() +
                "\n\tseats=" + seats +
                "\n}";
    }
}
