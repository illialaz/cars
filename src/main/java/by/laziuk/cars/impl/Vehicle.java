package by.laziuk.cars.impl;

import by.laziuk.cars.annotations.construct;
import com.fasterxml.jackson.annotation.*;

import java.util.Comparator;
import java.util.Map;
import java.util.UUID;

@JsonPropertyOrder({"type", "country", "company", "model", "weight", "maxSpeed", "numWheels", "cost", "statistics"})
public class Vehicle {

    public enum SortType {
        WEIGHT, NUMWHEELS, MAXSPEED, COST, MODEL, COMPANY, COUNTRY, STATISTICS, ID;

        public String toString(){
            switch(this){
                case WEIGHT : return "weight";
                case NUMWHEELS: return "numWheels";
                case MAXSPEED: return "maxSpeed";
                case COST: return "cost";
                case MODEL: return "model";
                case COMPANY: return "company";
                case COUNTRY: return "country";
                case STATISTICS: return "statistics";
                case ID: return "id";
            }
            return "id";
        }
    }

    final private static  String WEIGHT = "weight";
    final private static String NUMWHEELS = "numWheels";
    final private static String MAXSPEED = "maxSpeed";
    final private static String COST = "cost";
    final private static String MODEL = "model";
    final private static String COMPANY = "company";
    final private static String COUNTRY = "country";
    final private static String STATISTICS = "statistics";
    final private static String TYPE = "type";
    final private static String ID = "id";


    private int weight;
    private int numWheels;
    private int maxSpeed;
    private int cost;
    private String id;
    private String model;
    private String company;
    private String country;
    private Statistics statistics;
    protected String type;

    public Vehicle() {
        this.country = "";
        this.company = "";
        this.model = "";
        this.weight = 0;
        this.numWheels = 0;
        this.maxSpeed = 0;
        this.cost = 0;
        this.statistics = null;
        this.type = "";
        this.id = "";
    }

    @construct
    public Vehicle(Map<String, String> data){
        this.country = data.get(COUNTRY);
        this.company = data.get(COMPANY);
        this.model = data.get(MODEL);
        this.weight = Integer.parseInt(data.get(WEIGHT));
        this.numWheels = Integer.parseInt(data.get(NUMWHEELS));
        this.maxSpeed = Integer.parseInt(data.get(MAXSPEED));
        this.cost = Integer.parseInt(data.get(COST));
        this.type = data.get(TYPE);
        this.statistics = new Statistics(data);
        this.id = data.get(ID);
    }

    public Vehicle(String country,
                      String company,
                      String model,
                      int weight,
                      int numWheels,
                      int maxSpeed,
                      int cost,
                      String id,
                      String type,
                      Statistics statistics) {
        this.country = country;
        this.company = company;
        this.model = model;
        this.weight = weight;
        this.numWheels = numWheels;
        this.maxSpeed = maxSpeed;
        this.cost = cost;
        this.id = id;
        this.type = type;
        this.statistics = statistics;
    }

    public Vehicle(Vehicle car) {
        this(car.country, car.company, car.model, car.weight, car.numWheels, car.maxSpeed, car.cost, car.id, car.type, car.statistics);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getId() { return id; }

    public void setId(String id) {
        if(id.length() != 0) this.id = id;
        else this.id = UUID.randomUUID().toString();
    }

    public int getNumWheels() {
        return numWheels;
    }

    public void setNumWheels(int numWheels) {
        this.numWheels = numWheels;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = "vehicle"; }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = new Statistics(statistics);
    }

    public static Comparator<Vehicle> getComparator(SortType sorttype) {
        return (a, b) -> {
            switch (sorttype) {
                case WEIGHT:
                    return Integer.compare(a.getWeight(), b.getWeight());
                case NUMWHEELS:
                    return Integer.compare(a.getNumWheels(), b.getNumWheels());
                case MAXSPEED:
                    return Integer.compare(a.getMaxSpeed(), b.getMaxSpeed());
                case COMPANY:
                    return a.getCompany().compareTo(b.getCompany());
                case COUNTRY:
                    return a.getCountry().compareTo(b.getCountry());
                case STATISTICS:
                    return a.getStatistics().compareTo(b.getStatistics());
                case COST:
                    return Integer.compare(a.getCost(), b.getCost());
                case MODEL:
                    return a.getModel().compareTo(b.getModel());
                default:
                    return 0;
            }
        };
    }

    @Override
    public String toString() {
        return "\nVehicle{" +
                "\n\tweight=" + weight +
                "\n\tnumWheels=" + numWheels +
                "\n\tmaxSpeed=" + maxSpeed +
                "\n\tcost=" + cost +
                "\n\tmodel='" + model + '\'' +
                "\n\tcompany='" + company + '\'' +
                "\n\tcountry='" + country + '\'' +
                statistics +
                "\n}";
    }
}
