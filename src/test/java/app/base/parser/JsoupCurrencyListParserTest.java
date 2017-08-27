package app.base.parser;

import app.base.domain.Currency;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class JsoupCurrencyListParserTest {

    private Document document;

    @Test
    public void test_kebhana() throws IOException  {
        File docFile = new File(getClass().getResource("/parser/test_sample.html").getPath());
        this.document = Jsoup.parse(docFile, "UTF-8", "http://test.com");

        CurrencyListParser<Document, List<Currency>> currencyListParser = new KEBHanaCurrencyListParser();
        List<Currency> parsedCurrencyList = currencyListParser.parse(document);
        assertNotNull("parsed currency list is not null", parsedCurrencyList);
        assertFalse(parsedCurrencyList.isEmpty());
    }

    @Test
    public void test_wooribank() throws IOException {
        File docFile = new File(getClass().getResource("/parser/test_wooribank_sample.html").getPath());
        this.document = Jsoup.parse(docFile, "UTF-8", "http://test.com");

        CurrencyListParser<Document, List<Currency>> currencyListParser = new WooribankCurrencyListParser();
        List<Currency> parsedCurrencyList = currencyListParser.parse(document);
        assertNotNull("parsed currency list is not null", parsedCurrencyList);
        assertFalse(parsedCurrencyList.isEmpty());
    }
}
