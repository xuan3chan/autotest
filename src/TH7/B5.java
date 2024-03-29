package TH7;

import java.io.FileReader;
import java.io.IOException;

import org.testng.annotations.Test;

import com.google.gson.stream.JsonReader;

public class B5 {
    @Test
    public void ReadJSON_API() throws IOException {
        // Specify the path to your JSON file
        String filePath = "D:\\employee.json";

        try (FileReader fileReader = new FileReader(filePath);
             JsonReader reader = new JsonReader(fileReader)) {

            reader.beginObject(); // Start reading the JSON object

            while (reader.hasNext()) {
                String name = reader.nextName();

                if (name.equals("firstName")) {
                    System.out.println("First Name: " + reader.nextString());
                } else if (name.equals("lastName")) {
                    System.out.println("Last Name: " + reader.nextString());
                } else if (name.equals("age")) {
                    System.out.println("Age: " + reader.nextString());
                } else if (name.equals("school")) {
                    System.out.println("School: " + reader.nextString());
                } else if (name.equals("address")) {
                    // If the field is an array, we need to iterate through it
                    reader.beginArray();
                    while (reader.hasNext()) {
                        reader.beginObject();
                        while (reader.hasNext()) {
                            String addressName = reader.nextName();
                            if (addressName.equals("street")) {
                                System.out.println("Street: " + reader.nextString());
                            } else if (addressName.equals("city")) {
                                System.out.println("City: " + reader.nextString());
                            } else if (addressName.equals("province")) {
                                System.out.println("Province: " + reader.nextString());
                            } else {
                                reader.skipValue(); // Skip any unexpected fields
                            }
                        }
                        reader.endObject();
                    }
                    reader.endArray();
                } else {
                    reader.skipValue(); // Skip any unexpected fields
                }
            }
            reader.endObject(); // End of reading the JSON object

        } catch (IOException e) {
            // Handle IO exceptions
            e.printStackTrace();
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
        }
    }
}
