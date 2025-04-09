package tqs.hw1.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tqs.hw1.entities.Meal;
import tqs.hw1.entities.Restaurant;
import tqs.hw1.repository.MealRepository;
import tqs.hw1.repository.RestaurantRepository;
import tqs.hw1.service.RestaurantService;

import java.time.LocalDate;

@Configuration
public class DataLoaderConfig {

    @Bean
    CommandLineRunner initDatabase(RestaurantRepository restaurantRepository, MealRepository mealRepository) {
        return args -> {
            // Verifica se já existem restaurantes no banco de dados
            if (restaurantRepository.count() == 0) {
                // Cria e salva os restaurantes
                Restaurant cantinaSantiago = restaurantRepository.save(new Restaurant("Cantina de Santiago"));
                Restaurant cantinaCrasto = restaurantRepository.save(new Restaurant("Cantina do Crasto"));

                // Cria e salva as refeições para "Cantina de Santiago"
                mealRepository.save(new Meal("Prato Normal - Solha frita e arroz de tomate caldoso",
                        LocalDate.of(2025, 4, 7), cantinaSantiago.getId()));
                mealRepository.save(
                        new Meal("Prato Normal - Frango assado com mostarda e massa esparguete com molho de tomate",
                                LocalDate.of(2025, 4, 8), cantinaSantiago.getId()));
                mealRepository.save(new Meal("Prato Normal - Meia desfeita de bacalhau e batata cozida e grão",
                        LocalDate.of(2025, 4, 9), cantinaSantiago.getId()));

                // Cria e salva as refeições para "Cantina do Crasto"
                mealRepository.save(new Meal("Prato Normal - Grelhada mista com arroz branco e brócolos",
                        LocalDate.of(2025, 4, 7), cantinaCrasto.getId()));
                mealRepository.save(
                        new Meal("Prato Normal - Bacalhau com natas", LocalDate.of(2025, 4, 8), cantinaCrasto.getId()));
                mealRepository.save(new Meal("Prato Normal - Peru à salsicheiro e esparguete", LocalDate.of(2025, 4, 9),
                        cantinaCrasto.getId()));

            } else {
                System.out.println("ℹ️ Restaurants already exist. Skipping initialization.");
            }
        };
    }
}
