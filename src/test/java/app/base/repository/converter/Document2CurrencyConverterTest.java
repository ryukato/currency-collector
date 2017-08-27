package app.base.repository.converter;

import app.base.domain.Currency;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class Document2CurrencyConverterTest {

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
        converter.setCustomConversions(new CustomConversions(Collections.singletonList(new DbObject2CurrencyConverter())));
        converter.afterPropertiesSet();
    }

    @Test
    public void read() {
        Map<String, Object> map = new java.util.HashMap<>();
        map.put("baseDateTime", new Date());
        map.put("currency", "USD");
        map.put("currencyInKorean", "달러");
        map.put("buyInCashCurrency", "0");
        map.put("buyInCashSpread", "0");
        map.put("sellInCashCurrency", "0");
        map.put("sellInCashSpread", "0");
        map.put("sellInWireCurrency", "0");
        map.put("travelerCheckCurrency", "0");
        map.put("foreignCheckCurrency", "0");
        map.put("sellingBaseRate", "0");
        map.put("currencyInDollar", "0");

        DBObject dbObject = new BasicDBObject(map);

        Currency currency = converter.read(Currency.class, dbObject);
        assertNotNull(currency);
    }
}
