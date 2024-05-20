package com.example.demo;

import com.example.demo.entities.*;
import com.example.demo.model.enums.MovieType;
import com.example.demo.model.enums.UserRole;
import com.example.demo.repository.*;
import com.example.demo.service.MovieService;
import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
    MovieRepository movieRepository;
	@Autowired
	BlogRepository blogRepository;
	@Autowired
	ActorRepository actorRepository;
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	CountryRepository countryRepository;
	@Autowired
	DirectorRepository directorRepository;
	@Autowired
	EpisodeRepository episodeRepository;
	@Autowired
	FavoriteRepository favoriteRepository;
	@Autowired
	GenreRepository genreRepository;
	@Autowired
	HistoryRepository historyRepository;
	@Autowired
	ReviewRepository reviewRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	@Test
	void save_genres() {
		Faker faker = new Faker();
		Slugify slugify	= Slugify.builder().build();
		for (int i = 0; i < 20; i++) {
			String name = faker.funnyName().name();
			Genre genre = Genre.builder()
					.name(name)
					.slug(slugify.slugify(name))
					.build();
			genreRepository.save(genre);
		}
	}

	@Test
	void save_actors(){
		Faker faker = new Faker();
		for (int i = 0; i < 100; i++) {
			String name = faker.name().fullName();
			Actor actor = Actor.builder()
					.name(name)
					.avatar("https://placehold.co/600x400?text=" +String.valueOf(name.charAt(0)).toUpperCase())
					.bio(faker.lorem().paragraph())
					.build();
			actorRepository.save(actor);
		}
	}

	@Test
	void save_directors(){
		Faker faker = new Faker();
		for (int i = 0; i < 30; i++) {
			String name = faker.name().fullName();
			Director director = Director.builder()
					.name(name)
					.avatar("https://placehold.co/600x400?text=" +String.valueOf(name.charAt(0)).toUpperCase())
					.bio(faker.lorem().paragraph())
					.build();
			directorRepository.save(director);
		}
	}

	@Test
	void save_country() {
		Faker faker = new Faker();
		Slugify slugify = Slugify.builder().build();
		for (int i = 0; i < 20; i++) {
			String name = faker.funnyName().name();
			Country country = Country.builder()
					.name(name)
					.slug(slugify.slugify(name))
					.build();
			countryRepository.save(country);
		}
	}

	@Test
	void save_users(){
		Faker faker = new Faker();
		for (int i = 0; i < 50; i++) {
			String name = faker.name().fullName();
			User user =User.builder()
					.name(name)
					.email(faker.internet().emailAddress())
					.avatar("https://placehold.co/600x400?text=" +String.valueOf(name.charAt(0)).toUpperCase())
					.password("123")
					.role(i==0 || i==1 ? UserRole.ADMIN : UserRole.USER)
					.createdAt(LocalDate.now())
					.updatedAt(LocalDate.now())
					.build();
			userRepository.save(user);
		}

	}

	@Test
	void save_blogs() {
		List<User> users = userRepository.findByRole(UserRole.ADMIN);
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
					.user(users.get(random.nextInt(users.size())))
					.build();
			blogRepository.save(blog);
		}
	}

	@Test
	void save_comments(){
		Faker faker = new Faker();
		Random random = new Random();
		List<User> users = userRepository.findByRole(UserRole.USER);
		List<Blog> blogs =blogRepository.findAll();
		for (Blog blog : blogs) {
			for (int i = 0; i < random.nextInt(6)+5; i++) {
				Comment comment =Comment.builder()
						.content(faker.lorem().paragraph())
						.createdAt(LocalDate.now())
						.updatedAt(LocalDate.now())
						.user(users.get(random.nextInt(users.size())))
						.blog(blog)
						.build();
				commentRepository.save(comment);
			}

		}
	}

	@Test
	void save_movies() {
		Random random = new Random();
		Faker faker = new Faker();
		Slugify slugify= Slugify.builder().build();
		List<Country> countries = countryRepository.findAll();
		List<Actor> actors = actorRepository.findAll();
		List<Genre> genres = genreRepository.findAll();
		List<Director> directors = directorRepository.findAll();


		for (int i = 0; i < 100; i++) {
			Country rdCountry = countries.get(random.nextInt(countries.size()));
			List<Genre> rdGenres = new ArrayList<>();
			for (int j = 0; j < random.nextInt(3)+1; j++) {
				Genre rdGenre = genres.get(random.nextInt(genres.size()));
				if (!rdGenres.contains(rdGenre)) {
					rdGenres.add(rdGenre);
				}
			}
			List<Actor> rdActors = new ArrayList<>();
			for (int j = 0; j < random.nextInt(10)+1; j++) {
				Actor rdActor = actors.get(random.nextInt(actors.size()));
				if (!rdActors.contains(rdActor)) {
					rdActors.add(rdActor);
				}
			}
			List<Director> rdDirectors = new ArrayList<>();
			for (int j = 0; j < random.nextInt(3)+1; j++) {
				Director rdDirector = directors.get(random.nextInt(directors.size()));
				if (!rdDirectors.contains(rdDirector)) {
					rdDirectors.add(rdDirector);
				}
			}

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
					.actors(rdActors)
					.directors(rdDirectors)
					.country(rdCountry)
					.genres(rdGenres)
					.build();
			movieRepository.save(movie);
		}
	}

	@Test
	void save_reviews(){
		Faker faker = new Faker();
		Random random = new Random();
		List<User> users = userRepository.findByRole(UserRole.USER);
		List<Movie> movies =movieRepository.findAll();
		for (Movie movie : movies) {
			for (int i = 0; i < random.nextInt(6) + 5; i++) {
				Review review = Review.builder()
						.content(faker.lorem().paragraph())
						.createdAt(LocalDate.now())
						.updatedAt(LocalDate.now())
						.user(users.get(random.nextInt(users.size())))
						.rating(random.nextInt(10) + 1)
						.movie(movie)
						.build();
				reviewRepository.save(review);
			}
		}
	}

	@Test
	void save_episodes(){
		Random random = new Random();
		List<Movie> movies = movieRepository.findAll();

		for (Movie movie : movies) {
			if (movie.getType() == MovieType.PHIM_BO) {
				for (int i = 0; i < random.nextInt(6) + 5; i++) {
					Episode episode = Episode.builder()
							.name("Episode "+(i+1))
							.duration(45.0)
							.videoURL("https://teacher.techmaster.vn/ded8c44b-aa96-4c22-8611-17ae8c9e7555")
							.orders(i+1)
							.createdAt(LocalDate.now())
							.updatedAt(LocalDate.now())
							.movie(movie)
							.build();
					episodeRepository.save(episode);
				}
			} else {
				Episode episode = Episode.builder()
						.name("Episode Full")
						.duration(100.0)
						.videoURL("https://teacher.techmaster.vn/ded8c44b-aa96-4c22-8611-17ae8c9e7555")
						.orders(1)
						.createdAt(LocalDate.now())
						.updatedAt(LocalDate.now())
						.movie(movie)
						.build();
				episodeRepository.save(episode);
			}
		}
	}

	@Test
	void updatePrivatePassword(){
		List<User> users = userRepository.findAll();
		for (User user : users) {
			user.setPassword(bCryptPasswordEncoder.encode("123"));
			userRepository.save(user);
		}
	}

	@Test
	void save_favorites() {
		List<User> users = userRepository.findByRole(UserRole.USER);
		List<Movie> movies = movieRepository.findByStatus(true);

		for (User user : users) {
			// 1 -> 4 favorite movies. Each favorite has a unique movie
			List<Movie> favoriteMovies = new ArrayList<>();
			for (int i = 0; i < new Random().nextInt(4) + 1; i++) {
				Movie movie = movies.get(new Random().nextInt(movies.size()));
				if (!favoriteMovies.contains(movie)) {
					favoriteMovies.add(movie);
				}
			}

			for (Movie movie : favoriteMovies) {
				Favorite favorite = Favorite.builder()
						.user(user)
						.movie(movie)
						.createdAt(LocalDate.now())
						.build();
				favoriteRepository.save(favorite);
			}
		}
	}

	@Test
	void save_histories() {
		List<User> users = userRepository.findByRole(UserRole.USER);
		List<Movie> movies = movieRepository.findByStatus(true);

		for (User user : users) {
			// 1 -> 3 histories. Each history has a unique movie
			List<Movie> historyMovies = new ArrayList<>();
			for (int i = 0; i < new Random().nextInt(3) + 1; i++) {
				Movie movie = movies.get(new Random().nextInt(movies.size()));
				if (!historyMovies.contains(movie)) {
					historyMovies.add(movie);
				}
			}

			for (Movie movie : historyMovies) {
				if(movie.getType() == MovieType.PHIM_BO) {

				} else {

				}
			}
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
