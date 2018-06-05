package pl.jakubgajewski.GieldaRowerowa;

import lombok.Builder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.jakubgajewski.GieldaRowerowa.models.BikeModel;
import pl.jakubgajewski.GieldaRowerowa.models.forms.BikeForm;

@SpringBootApplication
public class GieldaRowerowaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GieldaRowerowaApplication.class, args);

	}
}
