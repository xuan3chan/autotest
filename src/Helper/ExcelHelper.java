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
            for (int i = 1; i <= rowCount; i++) {
                List<String> rowData = new ArrayList<>();
                rowData.add("Row " + i);
                rowData.add(sheet.getRow(i).getCell(0).getStringCellValue());
                rowData.add(sheet.getRow(i).getCell(1).getCellTypeEnum() == CellType.NUMERIC
                        ? String.valueOf(sheet.getRow(i).getCell(1).getNumericCellValue())
                        : sheet.getRow(i).getCell(1).getStringCellValue());
                rowData.add(sheet.getRow(i).getCell(2).getCellType() == CellType.NUMERIC
                        ? String.valueOf(sheet.getRow(i).getCell(2).getNumericCellValue())
                        : sheet.getRow(i).getCell(2).getStringCellValue());
                rowData.add(sheet.getRow(i).getCell(3).getCellType() == CellType.NUMERIC
                        ? String.valueOf(sheet.getRow(i).getCell(3).getNumericCellValue())
                        : sheet.getRow(i).getCell(3).getStringCellValue());
                rowData.add(sheet.getRow(i).getCell(4).getStringCellValue());
                rowData.add(sheet.getRow(i).getCell(5).getStringCellValue());
                rowData.add(sheet.getRow(i).getCell(6).getCellTypeEnum() == CellType.NUMERIC
                        ? String.valueOf(sheet.getRow(i).getCell(6).getNumericCellValue())
                        : sheet.getRow(i).getCell(6).getStringCellValue());
                rowData.add(sheet.getRow(i).getCell(7).getStringCellValue());
                rowData.add(sheet.getRow(i).getCell(8).getStringCellValue());
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
