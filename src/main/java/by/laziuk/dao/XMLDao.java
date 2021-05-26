package by.laziuk.dao;

import by.laziuk.cars.impl.Identifiable;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLDao<T extends Identifiable> extends Dao<T> {
    private final String fileName;

    public XMLDao(Class<T> type, String fileName) {
        super(type);
        this.fileName = fileName;
    }

    @Override
    public List<T> read() {
        List<T> data = new ArrayList<>();
        XmlMapper mapper = new XmlMapper();
        try {
            data = mapper.readValue(new File(fileName), TypeFactory.defaultInstance().constructCollectionType(List.class, this.type));
        } catch(Exception e) { e.printStackTrace(); }
        return data;
    }

    @Override
    public void write(List<T> data) {
        try {
            (new XmlMapper())
                    .configure(SerializationFeature.INDENT_OUTPUT, true)
                    .writeValue(new File(fileName), data);
        } catch (Exception e) { e.printStackTrace(); }
    }
}
