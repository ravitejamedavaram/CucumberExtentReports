package data;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class DataMapper {

    public static Model getTestData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Model model = objectMapper.readValue(new File("./src/test/resources/testData/data.json"), Model.class);
        return model;
    }
}
