package TH7;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class B1 {

  public static void main(String[] args) throws IOException {
    // Replace "F:\\Test.xlsx" with the path to your actual Excel file
    FileInputStream fis = new FileInputStream("D:\\Test.xlsx");
    XSSFWorkbook workbook = new XSSFWorkbook(fis);

    // Get the first sheet (you can modify this to get a specific sheet by name)
    XSSFSheet sheet = workbook.getSheetAt(0);

    // Loop through rows
    for (int a = 0; a <= sheet.getLastRowNum(); a++) {
      XSSFRow row = sheet.getRow(a);

      // Skip rows that are null or empty
      if (row == null) {
        continue;
      }

      // Loop through cells in the current row
      for (int i = 0; i <= row.getLastCellNum(); i++) {
        XSSFCell cell = row.getCell(i);

        // Handle null cells
        if (cell == null) {
          System.out.print("|| ");
          continue;
        }

        // Print cell value based on its data type
        switch (cell.getCellType()) {
          case STRING:
            System.out.print(cell.getStringCellValue() + "|| ");
            break;
          case NUMERIC:
            System.out.print(cell.getNumericCellValue() + "|| ");
            break;
          case BOOLEAN:
            System.out.print(cell.getBooleanCellValue() + "|| ");
            break;
          default:
            System.out.print(" (Unsupported Cell Type) || ");
        }
      }
      System.out.println(); // Print new line after each row
    }

    workbook.close();
    fis.close();
  }
}

