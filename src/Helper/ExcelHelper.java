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

    // Constructor
    public ExcelHelper() {
        // Default constructor
    }


    public List<List<String>> useDataFromExcel() {
        List<List<String>> excelData = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(projectPath + "\\data\\AddProductData.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("testdata");
            int rowCount = sheet.getLastRowNum();
            int columnCount = sheet.getRow(0).getLastCellNum();
			for (int i = 1; i <= rowCount; i++) {
				List<String> rowData = new ArrayList<>();
				for (int j = 0; j < columnCount; j++) {
					if (sheet.getRow(i).getCell(j).getCellType() == CellType.NUMERIC) {
						rowData.add(String.valueOf((int) sheet.getRow(i).getCell(j).getNumericCellValue()));
					} else {
						rowData.add(sheet.getRow(i).getCell(j).getStringCellValue());
					}
				}
				excelData.add(rowData);
			}
            workbook.close(); // Close workbook to release resources
            fis.close(); // Close FileInputStream to release resources
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return excelData;
    }
}
