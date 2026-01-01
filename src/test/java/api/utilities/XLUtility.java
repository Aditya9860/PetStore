package api.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;

public class XLUtility {

    // ===== Public variables =====
    public String path;

    public FileInputStream fis;
    public FileOutputStream fos;

    public XSSFWorkbook xssfWorkbook;
    public XSSFSheet xssfSheet;
    public XSSFRow xssfRow;
    public XSSFCell xssfCell;

    public CellStyle style;

    // ===== Constructor =====
    public XLUtility(String path) {
        this.path = path;
    }

    // ===== Get Row Count =====
    public int getRowCount(String sheetName) throws IOException {
        fis = new FileInputStream(path);
        xssfWorkbook = new XSSFWorkbook(fis);
        xssfSheet = xssfWorkbook.getSheet(sheetName);
        int rowCount = xssfSheet.getLastRowNum();
        xssfWorkbook.close();
        fis.close();
        return rowCount;
    }

    // ===== Get Cell Count =====
    public int getCellCount(String sheetName, int rowNum) throws IOException {
        fis = new FileInputStream(path);
        xssfWorkbook = new XSSFWorkbook(fis);
        xssfSheet = xssfWorkbook.getSheet(sheetName);
        xssfRow = xssfSheet.getRow(rowNum);
        int cellCount = xssfRow.getLastCellNum();
        xssfWorkbook.close();
        fis.close();
        return cellCount;
    }

    // ===== Read Cell Data =====
    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
        fis = new FileInputStream(path);
        xssfWorkbook = new XSSFWorkbook(fis);
        xssfSheet = xssfWorkbook.getSheet(sheetName);
        xssfRow = xssfSheet.getRow(rowNum);
        xssfCell = xssfRow.getCell(colNum);

        DataFormatter formatter = new DataFormatter();
        String data = formatter.formatCellValue(xssfCell);

        xssfWorkbook.close();
        fis.close();
        return data;
    }


    // ===== Write Cell Data =====
    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
        fis = new FileInputStream(path);
        xssfWorkbook = new XSSFWorkbook(fis);
        xssfSheet = xssfWorkbook.getSheet(sheetName);

        xssfRow = xssfSheet.getRow(rowNum);
        if (xssfRow == null)
            xssfRow = xssfSheet.createRow(rowNum);

        xssfCell = xssfRow.getCell(colNum);
        if (xssfCell == null)
            xssfCell = xssfRow.createCell(colNum);

        xssfCell.setCellValue(data);

        fos = new FileOutputStream(path);
        xssfWorkbook.write(fos);

        xssfWorkbook.close();
        fis.close();
        fos.close();
    }
}
