package helper;

import java.util.List;

public class test {

	public static void main(String[] args) {
		ExcelHelper excelHelper = new ExcelHelper();
		List<List<String>> excelData = excelHelper.useDataFromExcel();
		for (List<String> rowData : excelData) {
			for (String cellData : rowData) {
				System.out.print(cellData + "\t");
				//in ra vi tri row va column format sao cho de doc
				System.out.print(excelData.indexOf(rowData) + "\t" + rowData.indexOf(cellData) + "\t");
				
			}
			System.out.println();
		}
	}
}
