package domain;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class CsvToMapParser {

	private final String link;

	public CsvToMapParser(String link) {
		this.link = link;
	}
	
	public Map<String, Object> getResult() {
		UrlFetcher url = new UrlFetcher(link);
		String csv = url.getContent();
		Map<String, Object> map = new HashMap<>();
	    String[] line =  csv.split(";");

	    for(int i = 0; i < line.length; i++){
	    	if(line[i].startsWith("Y"))
	    		map.put(line[i - 2], line[i - 1]);
	    }
//	    System.out.println(map);
	    return map;
	}
	
	public static void main(String[] args) {
		CsvToMapParser c = new CsvToMapParser
				("http://opendata-download-metobs.smhi.se/api/version/latest/parameter/2/station/107420/period/corrected-archive/data.csv");
		Map<String, Object> m = c.getResult();
		System.out.println(m.get("1995-08-13"));
//		for (Entry string : m.entrySet()) {
//			System.out.println(string.getValue());
//		}
	}
}
