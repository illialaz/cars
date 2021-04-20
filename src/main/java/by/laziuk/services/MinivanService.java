package by.laziuk.services;

import by.laziuk.cars.impl.Minivan;
import by.laziuk.cars.impl.Statistics;
import by.laziuk.cars.impl.Vehicle;
import by.laziuk.dao.CsvDao;
import by.laziuk.dao.Dao;
import by.laziuk.dao.JsonDao;
import by.laziuk.dao.XMLDao;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class MinivanService implements ICarService {
    final private Dao<Minivan> dao;
    final private String fileName;
    final public ReentrantLock lock = new ReentrantLock();

    public MinivanService(String type) {
        switch (type){
            case "CSV": {
                dao = new CsvDao<>(Minivan.class);
                fileName = "/Users/illia/projects/cars/minivans.csv";
                break;
            }
            case "XML": {
                dao = new XMLDao<>(Minivan.class);
                fileName = "/Users/illia/projects/cars/minivans.xml";
                break;
            }
            default:
                dao = new JsonDao<>(Minivan.class);
                fileName = "/Users/illia/projects/cars/minivans.json";
                break;
        }
     }

    @Override
    public void add(Map<String, String> car) {
        List<Minivan> cars = get("none");
        String id = car.get("id");
        boolean unique = true;
        if(!id.equals("")) {
            for (Minivan vehicle : cars) {
                if (vehicle.getId().equals(id)) {
                    unique = false;
                    break;
                }
            }
        }
        if(!unique) {
            patch(car.get("id"), car);
            return;
        }

        Minivan tmp = new Minivan(car);
        cars.add(new Minivan(car));
        lock.lock();
        dao.write(cars, fileName);
        lock.unlock();
    }

    @Override
    public void delete(String id) {
        List<Minivan> cars = get("none");
        boolean isDeleted = false;
        for(Minivan vehicle : cars) {
            if (vehicle.getId().equals(id)) {
                isDeleted = true;
                cars.remove(vehicle);
                break;
            }
        };
        lock.lock();
        if(isDeleted) dao.write(cars, fileName);
        lock.unlock();
    }

    @Override
    public void add(Vehicle car) {
        Minivan car1 = (Minivan) car;
        Map<String, String> res = new HashMap<>();
        car1.setId("");
        res.put("id", car1.getId());
        res.put("company", car1.getCompany());
        res.put("country", car1.getCountry());
        res.put("model", car1.getModel());
        res.put("type", car1.getType());
        res.put("cost", String.valueOf(car1.getCost()));
        res.put("maxSpeed", String.valueOf(car1.getMaxSpeed()));
        res.put("numWheels", String.valueOf(car1.getNumWheels()));
        res.put("weight", String.valueOf(car1.getWeight()));
        res.put("seats", String.valueOf(car1.getSeats()));
        res.put("ageUnder18", String.valueOf(car1.getStatistics().getAgeUnder18()));
        res.put("ageBetween18And30", String.valueOf(car1.getStatistics().getAgeBetween18And30()));
        res.put("ageBetween30And50", String.valueOf(car1.getStatistics().getAgeBetween30And50()));
        res.put("ageAfter50", String.valueOf(car1.getStatistics().getAgeAfter50()));
        add(res);
    }

    @Override
    public void patch(String id, Map<String, String> newCar) {
        List<Minivan> cars = get("none");
        boolean isChanged = false;
        for(Minivan vehicle : cars) {
            if (vehicle.getId().equals(id)) {
                isChanged = true;
                break;
            }
        };

        if(!isChanged) {
            return;
        }

        delete(id);
        add(newCar);
    }

    @Override
    public Vehicle getOne(String id) {
        Optional<Minivan> res = get("none").stream().filter((item) -> { return item.getId().equals(id); }).findFirst();
        return res.orElse(new Minivan());
    }

    @Override
    public List<Minivan> get(String sortParam) {
        lock.lock();
        List<Minivan> res = dao.read(fileName);
        lock.unlock();
        if(sortParam.equals("none")) {
            return res;
        }
        if(sortParam.equals("seats")) {
            res.sort(Minivan.getComparatorMinivan(sortParam));
            return res;
        }
        if(sortParam.equals("ageUnder18") ||
                sortParam.equals("ageBetween18And30") ||
                sortParam.equals("ageBetween30And50") ||
                sortParam.equals("ageAfter50")) {
            res.sort(Statistics.getComparator(sortParam));
            return res;
        }

        for(Vehicle.SortType a : Vehicle.SortType.values()) {
            if (a.toString().equals(sortParam)) {
                res.sort(Vehicle.getComparator(a));
                break;
            }
        }
        return res;
    }
}
