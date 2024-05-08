package com.example.demo;

import com.example.demo.entities.Movie;
import com.example.demo.model.enums.MovieType;
import com.example.demo.reponsitory.MovieReponsitory;
import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	MovieReponsitory movieReponsitory;
	@Test
	void save_movies() {
		Random random = new Random();
		Faker faker = new Faker();
		Slugify slugify= Slugify.builder().build();

		for (int i = 0; i < 100; i++) {
			String name = faker.book().title();
			Movie movie = Movie.builder()
					.name(name)
					.slug(slugify.slugify(name))
					.description(faker.lorem().paragraph())
					.poster("https://placehold.co/600x400?text=" +String.valueOf(name.charAt(0)).toLowerCase())
					.releaseYear(faker.number().numberBetween(2020,2024))
					.rating(faker.number().randomDouble(1,1,10))
					.type(MovieType.values()[random.nextInt(MovieType.values().length)])
					.status(faker.bool().bool())
					.trailer("https://www.youtube.com/embed/inIVdZSFfc0?si=ycuM6oztFfwXUYDh")
					.createdAt(LocalDate.now())
					.updatedAt(LocalDate.now())
					.build();
			movieReponsitory.save(movie);
		}
	}

	@Test
	void find_movies() {
		List<Movie> movies = movieReponsitory.findAll();
		System.out.println("Movies size: " + movies.size());

		//select * from movies where id in (1,2,3)
		List<Movie> movieById = movieReponsitory.findAllById(List.of(1,2,3));
		System.out.println("Movies size by id: " + movieById.size());

		//select * from movies where id =1
		Movie movie = movieReponsitory.findById(1).orElse(null);
		System.out.println("Movie: " + movie);

		//Update
		movie.setName("Harry Potter");
		movieReponsitory.save(movie);

		//delete
		movieReponsitory.deleteById(1);
		movieReponsitory.delete(movie);
		movieReponsitory.deleteAll();
		movieReponsitory.deleteAllById(List.of(1,2,3));
	}

	@Test
	void find_type_sort() {
		movieReponsitory.findByType(MovieType.PHIM_BO, Sort.by("name","rating").descending());
	}

	@Test
	void test_pagination() {
		PageRequest pageRequest =PageRequest.of(0, 10);
		Page<Movie> movies = movieReponsitory.findByStatus(true,pageRequest);
		System.out.println("Total pages: " + movies.getTotalPages());
		System.out.println("Number of elements: " + movies.getTotalElements());
		System.out.println("Content: " + movies.getContent());
	}

}
