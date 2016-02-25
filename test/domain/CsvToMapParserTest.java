package domain;

import static org.junit.Assert.*;

import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Test;

public class CsvToMapParserTest {
	CsvToMapParser parser;
	
	@Before
	public void setup() throws Exception{
		parser = new CsvToMapParser
				("http://opendata-download-metobs.smhi.se/api/version/latest/parameter/2/station/107420/period/corrected-archive/data.csv");
		
	}
	
	@Test
	public void testGetResultKeysAndValues() {
		String firstDate = "1995-08-01";
		String firstResult = "19.1";
		String lastDate = "2015-10-31";
		String lastResult = "8.0";
		String anyDate = "2005-11-17";
		String anyResult = "-4.8";
		
		assertEquals(firstResult, parser.getResult().get(firstDate));
		assertEquals(lastResult, parser.getResult().get(lastDate));
		assertEquals(anyResult, parser.getResult().get(anyDate));
	}
	
	@Test
	public void testIncorrectValues() {
		assertEquals(null, parser.getResult().get("Fel"));
	}
	
	@Test(expected = RuntimeException.class)
	public void testIncorrectCSVLink() {
		CsvToMapParser newParser = new CsvToMapParser("www.fel.com");
		newParser.getResult();
	}

}
