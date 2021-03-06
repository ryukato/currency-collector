package app.base.service;

import app.base.collector.CurrencyListCollector;
import app.base.domain.Currency;
import app.base.parser.CurrencyListParser;
import app.base.repository.CurrencyRepository;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.mockito.Mockito.*;

public class SimpleAddMergeCollectionJobServiceTest {
    private CollectionJobService<List<Currency>> collectionJobService;

    @Mock
    private CurrencyListCollector<Document> collector;

    @Mock
    private CurrencyListParser<Document, List<Currency>> parser;

    @Mock
    private CurrencyRepository currencyRepository;

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        // mocking
        collector = Mockito.mock(CurrencyListCollector.class);
        parser = Mockito.mock(CurrencyListParser.class);
        currencyRepository = Mockito.mock(CurrencyRepository.class);

        collectionJobService = new SimpleAddMergeCollectionJobService(currencyRepository);
    }

    @Test
    public void buildCurrencies() {
        String testCollectUrl = "http://test.com/currencies";
        Document document = new Document(testCollectUrl);
        Currency currency = getTestParsedCurrency();
        final CurrencyListCollector.Config configHasUrlButOtherEmpty = CurrencyListCollector.Config.emptyHeaderParamsConfig(testCollectUrl);
        when(collector.collect(configHasUrlButOtherEmpty)).thenReturn(document);
        when(parser.parse(document)).thenReturn(Collections.singletonList(currency));

        ChainingCollectionJob chainingCollectionJob2 = ChainingCollectionJob.builder()
                .collector(collector)
                .parser(parser)
                .config(configHasUrlButOtherEmpty)
                .build();

        ChainingCollectionJob chainingCollectionJob1 = ChainingCollectionJob.builder()
                .collector(collector)
                .parser(parser)
                .config(configHasUrlButOtherEmpty)
                .nextJob(chainingCollectionJob2)
                .build();

        ChainingCollectionJob chainingCollectionJob3 = ChainingCollectionJob.builder()
                .collector(collector)
                .parser(parser)
                .config(configHasUrlButOtherEmpty)
                .build();

        chainingCollectionJob1.setNextJob(chainingCollectionJob2);
        chainingCollectionJob2.setNextJob(chainingCollectionJob3);


        List<Currency> currencies = collectionJobService.execute(chainingCollectionJob1);

        verify(collector, times(3)).collect(configHasUrlButOtherEmpty);
        verify(parser, times(3)).parse(document);
        currencies.forEach(currency1 -> {
            verify(currencyRepository, times(1)).save(currency1);
        });
        //assertThat("currencies is not null", currencies, isNotNull());
        assertFalse(currencies == null);

    }

    @Test
    public void buildCurrencies_WithOnlySingleJob() {
        String testCollectUrl = "http://test.com/currencies";
        Document document = new Document(testCollectUrl);
        Currency currency = getTestParsedCurrency();
        final CurrencyListCollector.Config configHasUrlButOtherEmpty = CurrencyListCollector.Config.emptyHeaderParamsConfig(testCollectUrl);
        when(collector.collect(configHasUrlButOtherEmpty)).thenReturn(document);
        when(parser.parse(document)).thenReturn(Collections.singletonList(currency));

        ChainingCollectionJob chainingCollectionJob1 = ChainingCollectionJob.builder()
                .collector(collector)
                .parser(parser)
                .config(configHasUrlButOtherEmpty)
                .build();

        List<Currency> currencies = collectionJobService.execute(chainingCollectionJob1);

        verify(collector, times(1)).collect(configHasUrlButOtherEmpty);
        verify(parser, times(1)).parse(document);
        //assertThat("currencies is not null", currencies, isNotNull());
        assertFalse(currencies == null);

    }

    private Currency getTestParsedCurrency() {
        return Currency
                .builder()
                .setCurrencyInKorean("미국")
                .setCurrency("USD")
                .setBuyInCashCurrency(BigDecimal.ONE)
                .setBuyInCashSpread(BigDecimal.ONE)
                .setSellInCashCurrency(BigDecimal.ONE)
                .setSellInCashSpread(BigDecimal.ONE)
                .setBuyInWireCurrency(BigDecimal.ONE)
                .setSellInWireCurrency(BigDecimal.ONE)
                .setTravelerCheckCurrency(BigDecimal.ONE)
                .setForeignCheckCurrency(BigDecimal.ONE)
                .setSellingBaseRate(BigDecimal.ONE)
                .setCurrencyInDollar(BigDecimal.ONE)
                .build();
    }
}
