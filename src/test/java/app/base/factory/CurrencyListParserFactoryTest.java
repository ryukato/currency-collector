package app.base.factory;

import app.base.parser.CurrencyListParser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class CurrencyListParserFactoryTest {
    private CurrencyListParserFactory currencyListParserFactory;

    @Before
    public void setUp() {
        this.currencyListParserFactory = new CurrencyListParserFactory();
    }

    @Test
    public void getParser() {
        CurrencyListParser currencyListParser = this.currencyListParserFactory.create("KEB");
        assertNotNull(currencyListParser);

        currencyListParser = this.currencyListParserFactory.create("WOORI");
        assertNotNull(currencyListParser);
    }

    @Test
    public void getParser_emptyParserKey() {
        try {
            this.currencyListParserFactory.create("");
            fail();
        } catch (IllegalArgumentException e) {
            // do nothing
        }
    }

    @Test
    public void getParser_invalidParserKey() {
        try {
            this.currencyListParserFactory.create("INVALID");
            fail();
        } catch (IllegalArgumentException e) {
            // do nothing
        }
    }
}
