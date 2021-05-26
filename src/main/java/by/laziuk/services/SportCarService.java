package by.laziuk.services;

import by.laziuk.cars.impl.SportCar;
import by.laziuk.dao.IDao;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SportCarService extends CarService<SportCar> {

    public SportCarService(IDao<SportCar> dao) {
        super(SportCar.class, dao);
    }

    @Override
    public List<SportCar> get(@Nullable String sortParam) {
        List<SportCar> res = super.get(sortParam);
        if(sortParam == null) return res;
        if(sortParam.equals(SportCar.QUARTERMILETIME)) res.sort(SportCar.getComparatorSportCars(sortParam));
        return res;
    }
}
