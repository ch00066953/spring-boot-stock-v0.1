package wlgtext.jxl;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import jxl.read.biff.BiffException;

import org.junit.Test;

public class ReadExcelTest {

	@Test
	public void testGetData() throws FileNotFoundException, BiffException, IOException {
		ReadExcel re = new ReadExcel();

		File file = new File("F:\\000001.xls");

		String[][] result = re.getData(file, 0);

		int rowLength = result.length;

		for (int i = 0; i < rowLength; i++) {

			for (int j = 0; j < result[i].length; j++) {

				System.out.print(result[i][j] + "\t\t");

			}

			System.out.println();

		}
	}

}
