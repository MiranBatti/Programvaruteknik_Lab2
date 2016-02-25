package domain;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

public class CsvToMapParserTest {
	CsvToMapParser parser;
	Map<String, String> csvMap;
	
	@Before
	public void setup() throws Exception{
		parser = new CsvToMapParser
				("http://opendata-download-metobs.smhi.se/api/version/latest/parameter/2/station/107420/period/corrected-archive/data.csv");
		csvMap = parser.getResult();
	}
	
	@Test
	public void testGetResultKeysAndValues() {
		String firstDate = "1995-08-01";
		String firstResult = "19.1";
		String lastDate = "2015-10-31";
		String lastResult = "8.0";
		String anyDate = "2005-11-17";
		String anyResult = "-4.8";
		
		assertEquals(firstResult, csvMap.get(firstDate));
		assertEquals(lastResult, csvMap.get(lastDate));
		assertEquals(anyResult, csvMap.get(anyDate));
	}
	
	@Test
	public void testIncorrectValues() {
		assertEquals(null, csvMap.get("Fel"));
	}
	
	@Test(expected = RuntimeException.class)
	public void testIncorrectCSVLink() {
		CsvToMapParser newParser = new CsvToMapParser("www.fel.com");
		newParser.getResult();
	}
	
	@Test
	public void printCSVAsString() {
		for (Entry<String, String> entry : csvMap.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
	}

}
