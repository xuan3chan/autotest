package TH7;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class B2 {

    public static XSSFRow row;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        FileInputStream fis = new FileInputStream("D:\\Test.xlsx");
        XSSFWorkbook book = new XSSFWorkbook(fis);

        XSSFSheet sheet1 = book.getSheet("Sheet2");

        String[][] dulieu = new String[][]{{"Facebook", "Google", "Zalo", "Yahoo", "TDC", "PLT", "Trần Tuấn"},
                {"Nhựt Lâm", "Hồng Tư", "Xuân Hiếu", "Thị Hiền", "Phan Phước", "Thầy Thọ", "Thầy Thái"},

                {"Công ty A", "Công ty B", "Công ty C", "Công ty D", "Công ty E", "Công ty F", "Công ty G"},
                {"Quách Tĩnh", "Hồng Thất Công", "Hoàng Dung", "Hoàng Dược Sư", "Dương Khang", "Châu Bá Thông",
                        "Chu Du"},
                {"Không Minh", "Lưu Bị", "Tào Tháo", "Triệu Vân", "Trương Phi", "Tôn Quyền", "Hoàng Trung"},

                {"Vân Tương", "Vân Tịch", "Tiêu Hà", "Hàn Tín", "Phàn Khoái", "Lưu Bá Ôn", "Tiêu Tường"}};

        for (int dong = 0; dong < dulieu.length; dong++) {
            row = sheet1.createRow(dong);

            for (int i = 0; i < dulieu[dong].length; i++) {
                XSSFCell cell = row.createCell(i);
                cell.setCellType(CellType.STRING);
                cell.setCellValue(dulieu[dong][i].toString());
            }
        }

        FileOutputStream fos = new FileOutputStream("D:\\Test.xlsx");
        book.write(fos);
        book.close();
        fos.close();
    }
}

