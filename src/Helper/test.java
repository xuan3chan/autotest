package Helper;

import java.util.List;

public class test {

	public static void main(String[] args) {
		ExcelHelper excelHelper = new ExcelHelper();
		List<List<String>> excelData = excelHelper.useDataFromExcel();
		for (List<String> rowData : excelData) {
			for (String cellData : rowData) {
				System.out.print(cellData + "\t");
			}
			System.out.println();
		}
	}
}
