package tqs.hw1.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import tqs.hw1.service.WeatherService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WeatherServiceTest {

    @Mock
    private RestTemplate restTemplate; 

    private WeatherService weatherService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        weatherService = new WeatherService(restTemplate);

        ReflectionTestUtils.setField(weatherService, "apiKey", "test-api-key");
    }

    @Test
    void testGetWeatherForecast() {
        String mockResponse = "{ \"days\": [{ \"datetime\": \"2025-04-07\", \"tempmax\": 20, \"tempmin\": 10, \"conditions\": \"Clear\" }] }";
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(mockResponse);

        String result = weatherService.getWeatherForecast();

        assertNotNull(result);
        assertTrue(result.contains("2025-04-07"));
        assertTrue(result.contains("Clear"));

        verify(restTemplate, times(1)).getForObject(anyString(), eq(String.class));
    }

    
}
