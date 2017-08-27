package app.base;

import app.base.repository.converter.Currency2DbObjectConverter;
import app.base.repository.converter.DbObject2CurrencyConverter;
import com.mongodb.MongoClient;
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
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.Arrays;

/*
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

	@Override
	public void run(String... args) throws Exception {
//		Document document = Jsoup.connect("https://www.kebhana.com/cms/rate/wpfxd651_01i_01.do?ajax=true&pbldDvCd=3").get();
//		System.out.println(document.toString());
	}

    @SuppressWarnings("unused")
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
	public MappingMongoConverter mappingMongoConverter(MongoDbFactory mongoDbFactory) {
        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory),  new MongoMappingContext());
        converter.setCustomConversions(new CustomConversions(Arrays.asList(new DbObject2CurrencyConverter(), new Currency2DbObjectConverter())));
        converter.afterPropertiesSet();
        return converter;
    }

	@SuppressWarnings("unused")
	@Bean
	public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory, MappingMongoConverter mappingMongoConverter) {
		return new MongoTemplate(mongoDbFactory, mappingMongoConverter);
	}
}
