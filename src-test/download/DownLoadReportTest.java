package download;


import org.junit.Test;

public class DownLoadReportTest {

	@Test
	public void testGetReport() throws Exception {
		DownLoadReport dlr = new DownLoadReport();
		dlr.getReport("600031");
	}

	@Test
	public void testGetAllReport() throws Exception {
		DownLoadReport dlr = new DownLoadReport();
		dlr.getAllReport();
	}
}
