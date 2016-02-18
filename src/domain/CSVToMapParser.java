package domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.csv.*;

public class CSVToMapParser {

	



//	public CSVToMapParser(File file, CSVFormat format) {
//		testThing(file);
//		
//	}
//
//
//
//	public void testThing(File file) {
//		try {
//			parser = CSVParser.parse(file, Charset.defaultCharset(), CSVFormat.EXCEL);
//			for(CSVRecord record : parser.getRecords()) {
//				System.out.println(record);
//			}
//		}
//		catch(Exception e) {
//
//		}
//	}
	public static void main(String[] args) {
		File file = new File("H:/git/Programvaruteknik_Lab2/csv/smhi-opendata_2_107400_20160218_142048.csv");
		CSVParser parser = null;
		UrlFetcher url = new UrlFetcher("http://opendata-download-metobs.smhi.se/api/version/latest/parameter/2/station/107420/period/corrected-archive/data.csv");
		String csv = url.getContent();
		try {
			parser = CSVParser.parse(csv, CSVFormat.EXCEL);
		}
		catch(Exception e) {
			
		}
		
		try {
			for( CSVRecord record : parser.getRecords()) {
				System.out.println(record.get(3));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}


