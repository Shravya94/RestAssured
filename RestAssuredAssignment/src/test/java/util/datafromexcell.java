package util;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class datafromexcell {

	public static void main(String[] args) throws IOException {
		testdata();

	}

	public static Object[][] testdata() throws IOException {
		FileInputStream fis = new FileInputStream("C:\\SDET-Shravya\\RestAssured\\DataSheet.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int totalrows = sheet.getLastRowNum();
		Row row = sheet.getRow(1);
		int totalcolumn = row.getLastCellNum();

		System.out.println("Total Rows:" + totalrows);
		Object[][] data = new Object[totalrows + 1][totalcolumn];

		for (int i = 0; i < totalrows + 1; i++) {

			for (int j = 0; j < totalcolumn; j++) {

				try {
					data[i][j] = sheet.getRow(i).getCell(j).getStringCellValue();
					System.out.println(data[i][j]);

				}

				catch (NullPointerException ex)

				{
					System.out.println(ex.getMessage());
				}
			}

		}
		workbook.close();
		return data;

	}

}
