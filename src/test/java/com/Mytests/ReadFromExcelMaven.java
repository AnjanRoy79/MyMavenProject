package com.Mytests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterTest;

public class ReadFromExcelMaven {
	String fPath = "D:\\Anjan-SeleniumDemos\\Practise_WebDriver\\ExcelFiles\\LoginData.xlsx";
	File file;
	FileInputStream fis;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	
	@Test
	public void readAllData() {
		int r = sheet.getPhysicalNumberOfRows();
		int c = sheet.getRow(0).getPhysicalNumberOfCells();
		
		for(int i = 0; i < r; i++)
		{
			for(int j = 0; j < c; j++)
			{
				System.out.println(sheet.getRow(i).getCell(j).getStringCellValue());
			}
		}
	}

	@BeforeTest
	public void beforeTest() throws IOException {
		file = new File(fPath);
		fis = new FileInputStream(file);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheetAt(0);
	}

	@AfterTest
	public void afterTest() {
	}

}
