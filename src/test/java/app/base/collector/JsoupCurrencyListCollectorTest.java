package app.base.collector;

import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.fail;

public class JsoupCurrencyListCollectorTest {
    private CurrencyListCollector<Document> collector;

    @Before
    public void setUp() {
        this.collector = new JsoupCurrencyListCollector();
    }

    @Test
    public void collectTest_kebhana() {
        String testCollectUrl = "https://www.kebhana.com/cms/rate/wpfxd651_01i_01.do?ajax=true&pbldDvCd=3";
        CurrencyListCollector.Configuration configHasUrlButOtherEmpty = new EmptyHeaderParamConfiguration(testCollectUrl);
        Document result = collector.collect(configHasUrlButOtherEmpty);
        assertNotNull("collect result is not null", result);
        System.out.println("result: "+ result);
    }

    @Test
    public void collectTest_wooribank() {
        String testCollectUrl = "https://spot.wooribank.com/pot/jcc?withyou=FXXRT0011&__ID=c008329&NTC_DIS=B";
        CurrencyListCollector.Configuration configHasUrlButOtherEmpty = new EmptyHeaderParamConfiguration(testCollectUrl);
        Document result = collector.collect(configHasUrlButOtherEmpty);
        assertNotNull("collect result is not null", result);
        System.out.println("result: "+ result);
    }



    @Test
    public void collectTest_with_invalid_url() {
        CurrencyListCollector.Configuration invalidUrlConfig = new EmptyHeaderParamConfiguration("invalid url");
        try {
            collector.collect(invalidUrlConfig);
            fail();
        }catch (InvalidCollectConfigurationException e) {
            // do nothing
        }
    }

    @Test
    public void collectTest_with_not_reachable_url() {
        CurrencyListCollector.Configuration invalidUrlConfig = new EmptyHeaderParamConfiguration("http://not-reachable.com");
        try {
            collector.collect(invalidUrlConfig);
            fail();
        }catch (InvalidCollectConfigurationException e) {
            // do nothing
        }
    }

    @Test
    public void collectTest_with_empty_url() {
        CurrencyListCollector.Configuration urlEmptyConfig = new EmptyHeaderParamConfiguration(null);
        try {
            collector.collect(urlEmptyConfig);
            fail();
        } catch (InvalidCollectConfigurationException e) {
            // do nothing
        }
    }

    @Test
    public void collectTest_with_null_config() {
        try {
            collector.collect(null);
            fail();
        } catch (InvalidCollectConfigurationException e) {
            // do nothing
        }
    }
}
