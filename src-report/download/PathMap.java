package download;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PathMap {

	public static Map<String, Path> pMap = new HashMap<String, Path>();
	public static Map<String, String> paraMap = new HashMap<String, String>();

	public static synchronized Path getPath(String id) throws IOException {
		Path Path = pMap.get(id);
		if (Path != null) {
			return Path;
		} else {
			File in = new File(System.getProperty("user.dir")
					+ "\\src\\main\\resources\\dlconfig.xml");
			Document doc = Jsoup.parse(in, "UTF-8");
			Path = new PathImpl(doc.select("#" + id));
			pMap.put(id, Path);
		}
		return Path;
	}
	
	public static synchronized Path getPara(String id) throws IOException {
		Path Path = pMap.get(id);
		if (Path != null) {
			return Path;
		} else {
			File in = new File(System.getProperty("user.dir")
			                   + "\\WebContent\\WEB-INF\\etc\\dlconfig.xml");
			Document doc = Jsoup.parse(in, "UTF-8");
			Path = new PathImpl(doc.select("#" + id));
			pMap.put(id, Path);
		}
		return Path;
	}
}
