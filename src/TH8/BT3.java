package TH8;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class BT3 {

    @Test
    public void ReadCSVFile() throws IOException, CsvException {
        String path = "C:\\Users\\xuann\\Desktop\\videoposet\\data.csv"; // Replace with your actual CSV file path
        FileReader reader = new FileReader(path);
        try (CSVReader csvreader = new CSVReader(reader)) {
            List<String[]> list = csvreader.readAll();
            Iterator<String[]> ite = list.iterator();

            while (ite.hasNext()) {
                String[] data = ite.next();
                if (data.length >= 3) {
                    System.out.println("TestID: " + data[0]);
                    System.out.println("Địa chỉ: " + data[1]);
                    System.out.println("Kịch bản: " + data[2]);
                    System.out.println("Mô tả: " + data[3]);
                }
            }
        }
    }
}
