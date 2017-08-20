package app.base;

import app.base.event.AbstractEvent;
import app.base.event.CollectionStartEvent;
import app.base.event.Event;
import app.base.repository.CollectionEventRepository;
import app.base.repository.EventRepository;
import com.mongodb.MongoClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.time.LocalDateTime;

/*
 // TODO: integrate collector and parser
 // TODO: create save parse currency list to repository
 // TODO: create schedule job to collect and parsing
*/

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
@EnableAspectJAutoProxy
public class MinCurrencyCollectorApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(MinCurrencyCollectorApplication.class, args);
	}

	@Autowired
	private EventRepository eventRepository;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(eventRepository.hashCode());
		eventRepository.save(new CollectionStartEvent(null));

		Document document = Jsoup.connect("https://www.kebhana.com/cms/rate/wpfxd651_01i_01.do?ajax=true&pbldDvCd=3").get();
		System.out.println(document.toString());
	}

	@Bean
	public MongoDbFactory mongoDbFactory(
			MongoClient mongoClient,
			@Value("${spring.data.mongodb.database}") String databaseName) {

		return new SimpleMongoDbFactory(mongoClient, databaseName);
	}

	@SuppressWarnings("unused")
	@Bean
	public MongoClient mongoClient() {
		return new MongoClient();
	}

	@SuppressWarnings("unused")
	@Bean
	public MongoTemplate mongoTemplate(
			MongoClient mongoClient,
			@Value("${spring.data.mongodb.database}")  String databaseName) {
		return new MongoTemplate(mongoDbFactory(mongoClient, databaseName));
	}
}
