package wlgtext.jxl;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import jxl.read.biff.BiffException;

import org.junit.Test;

public class ReadCVSTest {

	@Test
	public void testGetData()  {
		ReadExcel re = new ReadExcel();

		File file = new File("E:\\stock\\data\\table.csv");
//		File file = new File("E:\\stock\\data\\000001.xls");

		String[][] result = null;
		try {
			result = re.getData(file, 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		int rowLength = result.length;

		for (int i = 0; i < rowLength; i++) {

			for (int j = 0; j < result[i].length; j++) {

				System.out.print(result[i][j] + "\t\t");

			}

			System.out.println();

		}
	}

}
