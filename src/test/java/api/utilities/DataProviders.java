package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "Data")
    public String[][] getAllData() throws IOException {

        String path = System.getProperty("user.dir") + "//testData//UserData.xlsx";
        XLUtility xl = new XLUtility(path);

        String sheetName = "Sheet1";
        int rowCount = xl.getRowCount(sheetName);
        int colCount = xl.getCellCount(sheetName, 1);

        String[][] apiData = new String[rowCount][colCount];

        for (int i = 1; i <= rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                apiData[i - 1][j] = xl.getCellData(sheetName, i, j);
            }
        }
        return apiData;
    }

    @DataProvider(name = "UserNames")
    public String[] getUsernames() throws IOException {

        String path = System.getProperty("user.dir") + "//testData//UserData.xlsx";
        XLUtility xl = new XLUtility(path);

        String sheetName = "Sheet1";
        int rowCount = xl.getRowCount(sheetName);

        String[] usernames = new String[rowCount];

        for (int i = 1; i <= rowCount; i++) {
            usernames[i - 1] = xl.getCellData(sheetName, i, 1); // assuming username is in column index 1
        }

        return usernames;
    }
}
