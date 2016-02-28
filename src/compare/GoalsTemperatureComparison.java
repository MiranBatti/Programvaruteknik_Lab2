package compare;

import domain.DataSource;
import domain.FootballGoalsSource;
import domain.TemperatureSource;

public class GoalsTemperatureComparison {

	public static void main(String[] args) {
		DataSource goals = new FootballGoalsSource();
		DataSource temperatures = new TemperatureSource();
		
		DataCollectionBuilder builder = new DataCollectionBuilder(goals, temperatures, Resolution.DAY);
		DataCollection result = builder.getResult();
		result.getData().forEach( (date, match) -> {
			System.out.println(date + " # " + result.getXUnit() + 
					": " + match.getXValue() + " - " + result.getYUnit() + 
					": " + match.getYValue()); 
		});
	}

}
