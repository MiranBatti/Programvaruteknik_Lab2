package domain;

import java.time.LocalDate;
import java.util.Map;

public class FootballTemperatureSource implements DataSource {

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
		// TODO Auto-generated method stub
		return null;
	}

}
