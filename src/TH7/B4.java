package TH7;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.testng.annotations.Test;

import com.google.gson.stream.JsonReader;

public class B4 {
    @Test

    public void ReadJSON_API() throws IOException {

        URL url = new URL("https://jsonplaceholder.typicode.com/posts");

        InputStreamReader reader_stream = new InputStreamReader(url.openStream());

        try (JsonReader reader = new JsonReader(reader_stream)) {

            reader.beginArray();

            while (reader.hasNext()) {

                reader.beginObject();

                while (reader.hasNext()) {
                    String name = reader.nextName();

                    if (name.equals("userId")) {
                        System.out.println(reader.nextInt());

                    } else if (name.equals("id")) {
                        System.out.println(reader.nextInt());
                    } else if (name.equals("title")) {

                        System.out.println(reader.nextString());
                    } else if (name.equals("body")) {

                        System.out.println(reader.nextString());
                    }
                }
                reader.endObject();

            }
            System.out.println("");

            reader.endArray();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }
}

