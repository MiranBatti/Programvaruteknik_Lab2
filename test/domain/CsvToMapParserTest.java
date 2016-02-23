package domain;

import static org.junit.Assert.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.junit.Before;
import org.junit.Test;

public class CsvToMapParserTest {

	private URL urlComma;
	private URL urlSemicolon;
	private Map<String, String> correctData;

	@Before
	public void setUp() throws Exception {
		urlComma = new File("csv-test/testComma.csv").toURI().toURL();
		urlSemicolon = new File("csv-test/testSemicolon.csv").toURI().toURL();
	}

	@Test(expected = RuntimeException.class)
	public void testCreateObjectWithIllegalURL() {
		new CsvToMapParser("");
	}

	@Test
	public void testGetMapFromFileWithSemicolonDelimiter() {
		correctData = makeMapForSemicolonDelimiter();
		assertEquals(correctData, new CsvToMapParser(urlSemicolon).getMap("test-value", "day-of-year"));
	}

	@Test
	public void testGetMapFromFileWithCommaDelimiter() {
		CSVFormat format = CSVFormat.DEFAULT.withHeader().withDelimiter(',');
		correctData = makeMapForCommaDelimiter();
		assertEquals(correctData, new CsvToMapParser(urlComma, format).getMap("day-of-year", "comment"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetMapFromInvalidFile() throws MalformedURLException {
		URL invalidCSVUrl = new File("csv-test/testInvalid.csv").toURI().toURL();
		new CsvToMapParser(invalidCSVUrl).getMap("no-good", "values");
	}

	private Map<String, String> makeMapForSemicolonDelimiter() {
		Map<String, String> semicolonMap = new HashMap<>();
		semicolonMap.put("9.29", "2016-02-22");
		semicolonMap.put("-1315.915", "1330-09-12");
		semicolonMap.put("9000.99999", "5199-12-30");
		return semicolonMap;
	}

	private Map<String, String> makeMapForCommaDelimiter() {
		Map<String, String> commaMap = new HashMap<>();
		commaMap.put("2016-02-22", "den första");
		commaMap.put("1330-09-12", "den andra");
		commaMap.put("5199-12-30", " är detta den tredje tro?");
		return commaMap;
	}

}
