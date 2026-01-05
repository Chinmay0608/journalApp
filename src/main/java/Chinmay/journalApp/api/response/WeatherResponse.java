package Chinmay.journalApp.api.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class WeatherResponse {
    private Current current;

    @Getter
    @Setter
    public class Current {
        private int temperature;
        private int feelslike;
    }
}
