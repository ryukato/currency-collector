package app.base.parser;

import app.base.domain.Currency;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Optional;
import java.util.function.Function;

public class KEBHanaCurrencyListParser extends AbstractHtmlCurrencyListParser {
    private final Function<Element, Currency> mapper = e -> {
        String[] currencies = e.child(0).text().split(" ");
        return Currency
                .builder()
                .setCurrencyInKorean(Optional.ofNullable(currencies[0]).map(String::trim).orElse(""))
                .setCurrency(Optional.ofNullable(currencies[1]).map(String::trim).orElse(""))
                .setBuyInCashCurrency(toBigDecimal(e.child(1).text()))
                .setBuyInCashSpread(toBigDecimal(e.child(2).text()))
                .setSellInCashCurrency(toBigDecimal(e.child(3).text()))
                .setSellInCashSpread(toBigDecimal(e.child(4).text()))
                .setBuyInWireCurrency(toBigDecimal(e.child(5).text()))
                .setSellInWireCurrency(toBigDecimal(e.child(6).text()))
                .setTravelerCheckCurrency(toBigDecimal(e.child(7).text()))
                .setForeignCheckCurrency(toBigDecimal(e.child(8).text()))
                .setSellingBaseRate(toBigDecimal(e.child(9).text()))
                .setCurrencyInDollar(toBigDecimal(e.child(11).text()))
                .setBaseDateTime(e.child(12).child(0).text().replaceAll("[년월일]","").replaceAll("[시분초]",""))
                .build();
    };

    @Override
    Function<Element, Currency> getMapper() {
        return mapper;
    }

    String getBaseDateTime(Document document) {
        Elements currencyInfoHeader = document.select("p.txtRateBox > span");
        return currencyInfoHeader.get(0).child(1).text() + " " + currencyInfoHeader.get(0).child(5).text();
    }
}
