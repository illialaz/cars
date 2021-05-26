package by.laziuk.cars.impl;

import by.laziuk.cars.annotations.construct;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Comparator;
import java.util.Map;

@Entity
@dev.morphia.annotations.Entity("jeeps")
@Table(name = "jeeps")
public class Jeep extends Vehicle implements Comparable<Jeep> {

final static public String CLEARANCE = "clearance";

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
                int clearance,
                Statistics statistics) {
        super(country, company, model, weight, numWheels, maxSpeed, cost, statistics);
        this.clearance = clearance;
    }

    @Column(name = "clearance")
    public int getClearance() {
        return clearance;
    }

    public void setClearance(int clearance) {
        this.clearance = clearance;
    }

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
