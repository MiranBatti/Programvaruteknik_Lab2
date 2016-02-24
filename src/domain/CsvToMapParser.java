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

public class CsvToMapParser {

	private final URL url;
	private CSVFormat format;

	public CsvToMapParser(String link) {
		try {
			this.url = new URL(link);
		} catch (MalformedURLException e) {
			throw new RuntimeException();
		}
		format = null;
	}

	public CsvToMapParser(URL url) {
		this.url = url;
	}

	public CsvToMapParser(String link, CSVFormat format) {
		this(link);
		this.format = format;
	}

	public CsvToMapParser(URL url, CSVFormat format) {
		this(url);
		this.format = format;
	}

	public Map<String, String> getMap(String key, String value) {
		Map<String, String> map = new HashMap<>();
		for(CSVRecord record : getList()) 
			map.put(record.get(key), record.get(value));
		return map;
	}

	private List<CSVRecord> getList() {
		try (CSVParser parser = CSVParser.parse(url, StandardCharsets.UTF_8, getFormat())) {
			return parser.getRecords();
		} catch (IOException e) {
			throw new IllegalArgumentException();
		}
	}

	public CSVFormat getFormat() {
		return format != null ? format : createFormat();
	}
	
	private CSVFormat createFormat() {
		format = CSVFormat.DEFAULT.withHeader().withDelimiter(';');
		return format;
	}
	
	public Map<String, Object> getResult() {
		UrlFetcher url = new UrlFetcher(this.url.toString());
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
		System.out.println(m.get("1995-08-14"));
//		for (Entry string : m.entrySet()) {
//			System.out.println(string.getValue());
//		}
	}
}
