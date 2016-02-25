package domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;

public class TemperatureSource implements DataSource {

	private final String csvLink;

	public TemperatureSource() {
		csvLink = "http://opendata-download-metobs.smhi.se/api/version/latest/parameter/2/station/107420/period/corrected-archive/data.csv";
	}

	@Override
	public String getName() {
		return "Temperature";
	}

	@Override
	public String getUnit() {
		return "Celcius";
	}
	
	@Override
	public Map<LocalDate, Double> getData() {
		CsvToMapParser parser = new CsvToMapParser(csvLink);
		Map<String, String> csvData = parser.getResult(); //Tidigare Map<String, Object>
		return csvData.keySet().stream().collect(Collectors.toMap
				(key -> LocalDate.parse(key), key -> Double.parseDouble(csvData.get(key))));
	}
}
