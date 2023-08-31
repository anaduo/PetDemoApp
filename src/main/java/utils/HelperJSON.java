package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HelperJSON {
    private static final String jsonFilePath = "/Users/solvd/Projects/Tets/PetStoreApp/src/test/resources/";

    public static String jSONtoString(String path) throws IOException {

        String body = new String(Files.readAllBytes(Paths.get(jsonFilePath + path)));
        return body;
    }

}
