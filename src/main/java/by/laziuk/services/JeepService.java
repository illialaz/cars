package by.laziuk.services;

import by.laziuk.cars.impl.Jeep;
import by.laziuk.dao.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JeepService extends CarService<Jeep> {

    public JeepService(IDao<Jeep> dao) {
        super(Jeep.class, dao);
    }

    @Override
    public List<Jeep> get(@Nullable String sortParam) {
        List<Jeep> res = super.get(sortParam);
        if(sortParam == null) return res;
        if(sortParam.equals(Jeep.CLEARANCE)) res.sort(Jeep.getComparatorJeeps(sortParam));
        return res;
    }
}
