package domain;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

public class GoalsSourceTest {
	FootballGoalsSource goalsSource;
	Map<LocalDate, Double> csvMap;
	
	@Before
	public void setUp() throws Exception {
		goalsSource = new FootballGoalsSource();
		csvMap = goalsSource.getData();
	}

	@Test
	public void testGetResultKeysAndValues() {
		LocalDate firstDate = LocalDate.parse("2014-04-06");
		Double firstResult = 3.0;
		LocalDate lastDate = LocalDate.parse("2014-10-19");
		Double lastResult = 3.0;
		LocalDate anyDate = LocalDate.parse("2014-05-26");
		Double anyResult = 2.0;
		
		assertEquals(firstResult, csvMap.get(firstDate));
		assertEquals(lastResult, csvMap.get(lastDate));
		assertEquals(anyResult, csvMap.get(anyDate));
	} 
	
	@Test
	public void testSizeOfCsvData() {
		int csvDataSize = 14;
		assertEquals(csvDataSize, csvMap.size());
	}
	
	@Test
	public void testIncorrectValue() {
		assertEquals(null, csvMap.get(LocalDate.parse("2666-01-01")));
	}
	 
	
	@Test
	public void printCsvMap() {
		for (Entry<LocalDate, Double> entry : csvMap.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
	}

}
