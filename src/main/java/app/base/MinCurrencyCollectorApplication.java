package app.base;

import app.base.collector.CurrencyListCollector;
import app.base.collector.JsoupCurrencyListCollector;
import app.base.domain.Currency;
import app.base.factory.CurrencyListParserFactory;
import app.base.repository.CurrencyRepository;
import app.base.repository.converter.Currency2DbObjectConverter;
import app.base.repository.converter.DbObject2CurrencyConverter;
import app.base.service.ChainingCollectionJob;
import app.base.service.CollectionJobService;
import app.base.service.SimpleAddMergeCollectionJobService;
import com.mongodb.MongoClient;
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
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 // TODO: create save parse currency list to repository
 // TODO: create schedule job to collect and parsing
*/

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
@EnableAspectJAutoProxy
public class MinCurrencyCollectorApplication implements CommandLineRunner{
    @Autowired
    private CurrencyRepository currencyRepository;

	public static void main(String[] args) {
		SpringApplication.run(MinCurrencyCollectorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        List<ChainingCollectionJob> collectionJobs = buildCollectionJobs();
        collectionJobs.stream().forEach(chainingCollectionJob -> new SimpleAddMergeCollectionJobService(currencyRepository).execute(chainingCollectionJob));
	}

    private List<ChainingCollectionJob> buildCollectionJobs() {

        ChainingCollectionJob chainingCollectionJob2 = ChainingCollectionJob.builder()
                .collector(documentCurrencyListCollector())
                .parser(currencyListParserFactory().create("KEB"))
                .config(CurrencyListCollector.
                        Config.
                        emptyHeaderParamsConfig("https://www.kebhana.com/cms/rate/wpfxd651_01i_01.do?ajax=true&pbldDvCd=3"))
                .build();

        ChainingCollectionJob chainingCollectionJob1 = ChainingCollectionJob.builder()
                .collector(documentCurrencyListCollector())
                .parser(currencyListParserFactory().create("WOORI"))
                .config(CurrencyListCollector.
                        Config.
                        emptyHeaderParamsConfig("https://spot.wooribank.com/pot/jcc?withyou=FXXRT0011&__ID=c008329&NTC_DIS=B"))
                .nextJob(chainingCollectionJob2)
                .build();
        chainingCollectionJob1.setNextJob(chainingCollectionJob2);
        return Collections.singletonList(chainingCollectionJob1);
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

	@Bean
    @Scope("prototype")
    public CurrencyListCollector<Document> documentCurrencyListCollector() {
	    return new JsoupCurrencyListCollector();
    }

    @Bean
    public CurrencyListParserFactory currencyListParserFactory() {
	    return new CurrencyListParserFactory();
    }
}
