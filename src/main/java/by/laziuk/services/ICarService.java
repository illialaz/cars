package by.laziuk.services;

import by.laziuk.cars.impl.Identifiable;
import by.laziuk.cars.impl.Vehicle;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public interface ICarService<T extends Identifiable> {
    String XML = "XML";
    String CSV = "CSV";
    String JSON = "JSON";
    String SQL = "SQL";
    String NoSQL = "NoSQL";

    void delete(String id);
    void add(T car);
    void patch(T car);
    List<T> get(@Nullable String sortParam);
    Optional<T> getOne(String id);
}
