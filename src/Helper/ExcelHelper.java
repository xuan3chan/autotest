package Helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {
    String projectPath = System.getProperty("user.dir");

    public static void main(String[] args) throws IOException {
        ExcelHelper excelHelper = new ExcelHelper(); // Tạo một thể hiện của lớp ExcelHelper
        excelHelper.useDataFromExcel(); // Gọi phương thức useDataFromExcel từ thể hiện đã tạo
    }

    public void useDataFromExcel() throws IOException {
        try {
            FileInputStream fis = new FileInputStream(projectPath + "\\data\\AddProductData.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("testdata");
            int rowCount = sheet.getLastRowNum();
            // lay data theo cot case name trong bảng mỗi case name là 1 đối tương
            // các cột có price và oldPrice co ky tu dat biet vi du (!@#$%^&*()_+)
            // nếu như cell trong thi bo qua
            for (int i = 1; i <= rowCount; i++) {
                System.out.println("Row " + i);
                String caseName = sheet.getRow(i).getCell(0).getStringCellValue();
                String productName = sheet.getRow(i).getCell(1).getCellTypeEnum() == CellType.NUMERIC
                        ? String.valueOf(sheet.getRow(i).getCell(1).getNumericCellValue())
                        : sheet.getRow(i).getCell(1).getStringCellValue();
                String price = sheet.getRow(i).getCell(2).getCellType() == CellType.NUMERIC
                        ? String.valueOf(sheet.getRow(i).getCell(2).getNumericCellValue())
                        : sheet.getRow(i).getCell(2).getStringCellValue();
                String oldPrice = sheet.getRow(i).getCell(3).getCellType() == CellType.NUMERIC
                        ? String.valueOf(sheet.getRow(i).getCell(3).getNumericCellValue())
                        : sheet.getRow(i).getCell(3).getStringCellValue();
                String brand = sheet.getRow(i).getCell(4).getStringCellValue();
                String category = sheet.getRow(i).getCell(5).getStringCellValue();
                String description = sheet.getRow(i).getCell(6).getCellTypeEnum() == CellType.NUMERIC
                        ? String.valueOf(sheet.getRow(i).getCell(6).getNumericCellValue())
                        : sheet.getRow(i).getCell(6).getStringCellValue();
                String image = sheet.getRow(i).getCell(7).getStringCellValue();
                String status = sheet.getRow(i).getCell(8).getStringCellValue();

                // Fill in the form fields with appropriate values
                List < String > productData = new ArrayList < String > ();
                productData.add(caseName);
                productData.add(productName);
                productData.add(price);
                productData.add(oldPrice);
                productData.add(brand);
                productData.add(category);
                productData.add(description);
                productData.add(image);
                productData.add(status);
                System.out.println(productData);
                
           
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
