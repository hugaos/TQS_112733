package tqs.hw1.service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@Service
public class WeatherService {

    @Value("${weather.api.key}") // Sua chave da API
    private String apiKey;

    private final String weatherUrl = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
    private final String city = "Aveiro"; // Cidade fixa
    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    
    @Cacheable(value = "weatherCache", key = "#city + '-07-04-2025-11-04-2025'", unless = "#result == null")
    public String getWeatherForecast() {
        // Datas fixas: 07-04-2025 a 11-04-2025
        String startDate = "2025-04-07";
        String endDate = "2025-04-11";

        // Construção da URL com as datas fixas
        String url = String.format("%s%s/%s/%s?unitGroup=us&include=days&key=%s&contentType=json",
                weatherUrl, city, startDate, endDate, apiKey);

        // Fazendo a requisição HTTP para a Visual Crossing API
        String response = restTemplate.getForObject(url, String.class);

        return response;
    }
}
