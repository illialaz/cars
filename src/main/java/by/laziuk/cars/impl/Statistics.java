package by.laziuk.cars.impl;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Map;

@Embeddable
public class Statistics implements Comparable<Statistics> {

    final public static String AGEUNDER18 = "ageUnder18";
    final public static String AGEBETWEEN18AND30 = "ageBetween18And30";
    final public static String AGEBETWEEN30AND50 = "ageBetween30And50";
    final public static String AGEAFTER50 = "ageAfter50";

    @Column
    private int ageUnder18;
    @Column
    private int ageBetween18And30;
    @Column
    private int ageBetween30And50;
    @Column
    private int ageAfter50;

    public Statistics() {
        this.ageUnder18 = 0;
        this.ageBetween18And30 = 0;
        this.ageBetween30And50 = 0;
        this.ageAfter50 = 0;
    }

    public Statistics(Statistics statistics) {
        this.ageAfter50 = statistics.ageAfter50;
        this.ageBetween18And30 = statistics.ageBetween18And30;
        this.ageBetween30And50 = statistics.ageBetween30And50;
        this.ageUnder18 = statistics.ageUnder18;
    }

    public Statistics(Map<String, String> data) {
        this.ageUnder18 = Integer.parseInt(data.get(AGEUNDER18));
        this.ageBetween18And30 = Integer.parseInt(data.get(AGEBETWEEN18AND30));
        this.ageBetween30And50 = Integer.parseInt(data.get(AGEBETWEEN30AND50));
        this.ageAfter50 = Integer.parseInt(data.get(AGEAFTER50));
    }

    public Statistics(int ageUnder18,
                      int ageBetween18And30,
                      int ageBetween30And50,
                      int ageAfter50) {
        this.ageUnder18 = ageUnder18;
        this.ageBetween18And30 = ageBetween18And30;
        this.ageBetween30And50 = ageBetween30And50;
        this.ageAfter50 = ageAfter50;
    }

    public int getAgeUnder18() {
        return ageUnder18;
    }

    public void setAgeUnder18(int ageUnder18) {
        this.ageUnder18 = ageUnder18;
    }

    public int getAgeBetween18And30() {
        return ageBetween18And30;
    }

    public void setAgeBetween18And30(int ageBetween18And30) {
        this.ageBetween18And30 = ageBetween18And30;
    }

    public int getAgeBetween30And50() {
        return ageBetween30And50;
    }

    public void setAgeBetween30And50(int ageBetween30And50) {
        this.ageBetween30And50 = ageBetween30And50;
    }

    public int getAgeAfter50() {
        return ageAfter50;
    }

    public void setAgeAfter50(int ageAfter50) {
        this.ageAfter50 = ageAfter50;
    }

    public int allPeople() {
        return this.getAgeAfter50() + this.getAgeBetween30And50() + this.getAgeBetween18And30() + this.getAgeUnder18();
    }

    public static Comparator<Vehicle> getComparator(String sorttype) {
        return (a, b) -> {
            switch (sorttype) {
                case AGEUNDER18:
                    return Integer.compare(a.getStatistics().ageUnder18, b.getStatistics().ageUnder18);
                case AGEBETWEEN18AND30:
                    return Integer.compare(a.getStatistics().ageBetween18And30, b.getStatistics().ageBetween18And30);
                case AGEBETWEEN30AND50:
                    return Integer.compare(a.getStatistics().ageBetween30And50, b.getStatistics().ageBetween30And50);
                case AGEAFTER50:
                    return Integer.compare(a.getStatistics().ageAfter50, b.getStatistics().ageAfter50);
                default:
                    return 0;
            }
        };
    }

    @Override
    public int compareTo(Statistics o) {
        return Integer.compare(this.allPeople(), o.allPeople());
    }

    @Override
    public String toString() {
        return "\n\tStatistics{" +
                "\n\t\tageUnder18=" + ageUnder18 +
                "\n\t\tageBetween18And30=" + ageBetween18And30 +
                "\n\t\tageBetween30And50=" + ageBetween30And50 +
                "\n\t\tageAfter50=" + ageAfter50 +
                "\n\t}";
    }
}
