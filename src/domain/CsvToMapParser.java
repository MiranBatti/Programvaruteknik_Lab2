package domain;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.util.*;

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
}
