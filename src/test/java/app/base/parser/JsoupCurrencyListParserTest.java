package app.base.parser;

import app.base.domain.ParsedCurrency;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class JsoupCurrencyListParserTest {

    private Document document;

    @Test
    public void test_kebhana() throws IOException  {
        File docFile = new File(getClass().getResource("/parser/test_sample.html").getPath());
        this.document = Jsoup.parse(docFile, "UTF-8", "http://test.com");

        CurrencyListParser<Document, List<ParsedCurrency>> currencyListParser = new KEBHanaCurrencyListParser();
        List<ParsedCurrency> parsedCurrencyList = currencyListParser.parse(document);
        assertNotNull("parsed currency list is not null", parsedCurrencyList);
        assertFalse(parsedCurrencyList.isEmpty());
        System.out.println(parsedCurrencyList.stream().map(ParsedCurrency::toString).collect(Collectors.joining("\n")));
    }

    @Test
    public void test_wooribank() throws IOException {
        File docFile = new File(getClass().getResource("/parser/test_wooribank_sample.html").getPath());
        this.document = Jsoup.parse(docFile, "UTF-8", "http://test.com");

        CurrencyListParser<Document, List<ParsedCurrency>> currencyListParser = new WooribankCurrencyListParser();
        List<ParsedCurrency> parsedCurrencyList = currencyListParser.parse(document);
        assertNotNull("parsed currency list is not null", parsedCurrencyList);
        assertFalse(parsedCurrencyList.isEmpty());
        System.out.println(parsedCurrencyList.stream().map(ParsedCurrency::toString).collect(Collectors.joining("\n")));
    }
}
