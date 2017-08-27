package app.base.repository.converter;

import app.base.domain.Currency;
import com.mongodb.BasicDBObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class Currency2DbObjectConverterTest {
    MappingMongoConverter converter;
    MongoMappingContext mappingContext;

    @Mock
    ApplicationContext context;

    @Mock
    DbRefResolver resolver;

    @Before
    public void setUp() {
        mappingContext = new MongoMappingContext();
        mappingContext.setApplicationContext(context);
        mappingContext.afterPropertiesSet();

        mappingContext.getPersistentEntity(Currency.class);

        converter = new MappingMongoConverter(resolver, mappingContext);
        converter.setCustomConversions(new CustomConversions(Collections.singletonList(new Currency2DbObjectConverter())));
        converter.afterPropertiesSet();
    }

    @Test
    public void write() {
        BigDecimal ZERO = new BigDecimal("0");
        Currency currency = Currency.builder()
                .setBaseDateTime(LocalDateTime.now())
                .setCurrency("USD")
                .setCurrencyInKorean("달러")
                .setBuyInCashCurrency(ZERO)
                .setBuyInCashSpread(ZERO)
                .setSellInCashCurrency(ZERO)
                .setSellInCashSpread(ZERO)
                .setSellInWireCurrency(ZERO)
                .setTravelerCheckCurrency(ZERO)
                .setForeignCheckCurrency(ZERO)
                .setSellingBaseRate(ZERO)
                .setCurrencyInDollar(ZERO)
                .build();
        BasicDBObject dbObject = new BasicDBObject();

        converter.write(currency, dbObject);
        assertThat(dbObject.getString("currency"), is("USD"));
    }
}
