package app.base.collector;

import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyListCollectorLoggingTest {

    @Autowired
    private CurrencyListCollector<Document> collector;

    @Test
    public void collectTest_kebhana() {
        String testCollectUrl = "https://www.kebhana.com/cms/rate/wpfxd651_01i_01.do?ajax=true&pbldDvCd=3";
        CurrencyListCollector.Configuration configHasUrlButOtherEmpty = new EmptyHeaderParamConfiguration(testCollectUrl);
        Document result = collector.collect(configHasUrlButOtherEmpty);
        assertNotNull("collect result is not null", result);
    }

}
