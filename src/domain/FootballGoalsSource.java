package domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author thomas
 */
public class FootballGoalsSource implements DataSource {

    @Override
    public String getName() {
        return "Antal mål per matchdag i fotbollsallsvenskan";
    }

    @Override
    public String getUnit() {
        return "Antal mål";
    }

    @Override
    public Map<LocalDate, Double> getData() {
        UrlFetcher fetcher = new UrlFetcher("http://api.everysport.com/v1/events?apikey=1769e0fdbeabd60f479b1dcaff03bf5c&league=63925&limit=50");
        JsonToMapParser parser = new JsonToMapParser(fetcher.getContent());
        Map<String, Object> data = parser.getResult();
        Map<LocalDate, Double> result = new TreeMap<>();
        for (Map event : (List<Map>) data.get("events")) {
            LocalDate date = LocalDate.parse(event.get("startDate").toString().substring(0, 10));
            int goals = Integer.parseInt(event.get("visitingTeamScore").toString());
            goals += Integer.parseInt(event.get("homeTeamScore").toString());
            addGoalsToDate(result, date, goals);
        }
        return result;
    }

    private void addGoalsToDate(Map<LocalDate, Double> result, LocalDate date, int goals) {
        if (!result.containsKey(date)) {
            result.put(date, new Double(goals));
        } else {
            result.put(date, result.get(date) + goals);
        }
    }

    public static void main(String[] args) {
        System.out.println(new FootballGoalsSource().getData());
    }
}
