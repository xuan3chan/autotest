package helper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonHelper {
    private static final String API_URL = "https://nextsp-server.id.vn/api/products/getall";

    public static void main(String[] args) {
        List<String> keysToExtract = new ArrayList<>();
        keysToExtract.add("nameProduct");
        keysToExtract.add("description");
        keysToExtract.add("price");
        keysToExtract.add("oldprice");
        keysToExtract.add("numReviews");
        keysToExtract.add("averageRating");
        keysToExtract.add("brand");
        keysToExtract.add("category");
        keysToExtract.add("status");

        List<Map<String, Object>> dataList = fetchDataFromAPI(API_URL, keysToExtract);

        for (Map<String, Object> data : dataList) {
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println();
        }
    }

    public static List<Map<String, Object>> fetchDataFromAPI(String apiUrl, List<String> keysToExtract) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(response.toString());

            if (jsonTree.isJsonObject()) {
                JsonObject jsonObject = jsonTree.getAsJsonObject();
                JsonArray productsArray = jsonObject.getAsJsonArray("products");
                Gson gson = new Gson();
                for (JsonElement element : productsArray) {
                    JsonObject productObject = element.getAsJsonObject();
                    Map<String, Object> productMap = new HashMap<>();
                    for (String key : keysToExtract) {
                        JsonElement value = productObject.get(key);
                        if (value != null) {
                            if (value.isJsonObject() || value.isJsonArray()) {
                                productMap.put(key, gson.fromJson(value, Map.class));
                            } else {
                                productMap.put(key, value.getAsString());
                            }
                        }
                    }
                    dataList.add(productMap);
                }
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }
}
