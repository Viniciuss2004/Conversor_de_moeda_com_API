package Service;

import java.util.Date;
import java.util.Map;

public record FormataJSONparaGSON (String base_code, String target_code, double conversion_rate, Date time_last_update_utc, Date time_next_update_utc, Map<String, Double> conversion_rates) {
}
