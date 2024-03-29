package TH7;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class B3 {

  public static void ReadFromJson() throws Exception {
    FileReader reader = new FileReader("D:\\employee.json"); // Replace with your JSON file path

    JSONParser parser = new JSONParser();
    JSONObject jsonObject = (JSONObject) parser.parse(reader);

    String firstName = (String) jsonObject.get("firstName");
    String lastName = (String) jsonObject.get("lastName");

    System.out.println("First Name: " + firstName);
    System.out.println("Last Name: " + lastName);

    JSONArray addressArray = (JSONArray) jsonObject.get("address");

    for (Object obj : addressArray) {
      JSONObject addressObject = (JSONObject) obj;

      String street = (String) addressObject.get("street");
      String city = (String) addressObject.get("city");
      String province = (String) addressObject.get("province");

      System.out.println("Address:");
      System.out.println("  Street: " + street);
      System.out.println("  City: " + city);
      System.out.println("  Province: " + province);
    }

    reader.close();
  }

  public static void main(String[] args) {
    try {
      ReadFromJson();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

