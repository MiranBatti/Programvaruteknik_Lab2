package domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class FootballTemperatureSource implements DataSource {
	
	private final String csvLink;
	
	public FootballTemperatureSource() {
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
		Map<String, Object> csvData;
		return null;
	}

}
