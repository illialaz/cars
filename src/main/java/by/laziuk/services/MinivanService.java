package by.laziuk.services;

import by.laziuk.cars.impl.Minivan;
import by.laziuk.dao.IDao;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MinivanService extends CarService<Minivan> {

    public MinivanService(IDao<Minivan> dao) {
        super(Minivan.class, dao);
     }

    @Override
    public List<Minivan> get(@Nullable String sortParam) {
        List<Minivan> res = super.get(sortParam);
        if(sortParam == null) return res;
        if(sortParam.equals(Minivan.SEATS)) res.sort(Minivan.getComparatorMinivan(sortParam));
        return res;
    }
}
