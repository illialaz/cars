package by.laziuk.dao;

import by.laziuk.cars.impl.Identifiable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonDao<T extends Identifiable> extends Dao<T> {

    private final String fileName;

    public JsonDao(Class<T> type, String fileName) {
        super(type);
        this.fileName = fileName;
    }

    @Override
    public List<T> read() {
        ObjectMapper mapper = new ObjectMapper();
        List<T> data = new ArrayList<>();
        try {
            data = mapper.readValue(new File(fileName), TypeFactory.defaultInstance().constructCollectionType(List.class, this.type));
        } catch(Exception e) { e.printStackTrace(); }
        return data;
    }

    @Override
    public void write(List<T> data) {
        try {
            (new ObjectMapper())
                    .configure(SerializationFeature.INDENT_OUTPUT, true)
                    .writeValue(new File(fileName), data);
        } catch (Exception e) { e.printStackTrace(); }
    }
}
