package app.base.parser;

import app.base.domain.Currency;
import app.base.domain.ParsedCurrency;
import org.jsoup.nodes.Element;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Function;

public class KEBHanaCurrencyListParser extends AbstractHtmlCurrencyListParser {
    private static final String CURRENCY_LIST_CSS_QUERY = "tbody tr";
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
                .build();
    };

    @Override
    Function<Element, Currency> getMapper() {
        return mapper;
    }

    @Override
    String getCurrencyListCssQuery() {
        return CURRENCY_LIST_CSS_QUERY;
    }

}
