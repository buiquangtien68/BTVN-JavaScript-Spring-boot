package com.example.demo;

import com.example.demo.entities.Blog;
import com.example.demo.entities.Movie;
import com.example.demo.model.enums.MovieType;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.MovieRepository;
import com.example.demo.service.MovieService;
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
    MovieRepository movieRepository;
	@Autowired
	BlogRepository blogRepository;


	@Test
	void save_blogs() {
		Random random = new Random();
		Faker faker = new Faker();
		Slugify slugify= Slugify.builder().build();
		for (int i = 0; i < 30; i++) {
			String title = faker.book().title();
			Blog blog = Blog.builder()
					.title(title)
					.slug(slugify.slugify(title))
					.description(faker.lorem().paragraph())
					.content(faker.lorem().paragraph(50))
					.thumbnail("https://placehold.co/600x400?text=" +String.valueOf(title.charAt(0)).toUpperCase())
					.status(faker.bool().bool())
					.createdAt(LocalDate.now())
					.updatedAt(LocalDate.now())
					.build();
			blogRepository.save(blog);
		}

	}
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
					.poster("https://placehold.co/600x400?text=" +String.valueOf(name.charAt(0)).toUpperCase())
					.releaseYear(faker.number().numberBetween(2020,2024))
					.rating(faker.number().randomDouble(1,1,10))
					.type(MovieType.values()[random.nextInt(MovieType.values().length)])
					.status(faker.bool().bool())
					.trailer("https://www.youtube.com/embed/inIVdZSFfc0?si=ycuM6oztFfwXUYDh")
					.createdAt(LocalDate.now())
					.updatedAt(LocalDate.now())
					.build();
			movieRepository.save(movie);
		}
	}


	@Test
	void find_movies() {
		List<Movie> movies = movieRepository.findAll();
		System.out.println("Movies size: " + movies.size());

		//select * from movies where id in (1,2,3)
		List<Movie> movieById = movieRepository.findAllById(List.of(1,2,3));
		System.out.println("Movies size by id: " + movieById.size());

		//select * from movies where id =1
		Movie movie = movieRepository.findById(1).orElse(null);
		System.out.println("Movie: " + movie);

		//Update
		movie.setName("Harry Potter");
		movieRepository.save(movie);

		//delete
		movieRepository.deleteById(1);
		movieRepository.delete(movie);
		movieRepository.deleteAll();
		movieRepository.deleteAllById(List.of(1,2,3));
	}

	@Test
	void find_type_sort() {
		movieRepository.findByType(MovieType.PHIM_BO, Sort.by("name","rating").descending());
	}

	@Test
	void test_pagination() {
		PageRequest pageRequest =PageRequest.of(0, 10);
		Page<Movie> movies = movieRepository.findByStatus(true,pageRequest);
		System.out.println("Total pages: " + movies.getTotalPages());
		System.out.println("Number of elements: " + movies.getTotalElements());
		System.out.println("Content: " + movies.getContent());
	}

}
