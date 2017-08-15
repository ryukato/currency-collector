package app.base.parser;

import app.base.domain.ParsedCurrency;
import org.jsoup.nodes.Element;

import java.util.Optional;
import java.util.function.Function;

public class KEBHanaCurrencyListParser extends AbstractHtmlCurrencyListParser {
    private static final String CURRENCY_LIST_CSS_QUERY = "tbody tr";
    private static final Function<Element, ParsedCurrency> mapper = e -> {
        String[] currencies = e.child(0).text().split(" ");
        return ParsedCurrency
                .builder()
                .setCurrencyInKorean(Optional.ofNullable(currencies[0]).map(String::trim).orElse(""))
                .setCurrency(Optional.ofNullable(currencies[1]).map(String::trim).orElse(""))
                .setBuyInCashCurrency(e.child(1).text())
                .setBuyInCashSpread(e.child(2).text())
                .setSellInCashCurrency(e.child(3).text())
                .setSellInCashSpread(e.child(4).text())
                .setBuyInWireCurrency(e.child(5).text())
                .setSellInWireCurrency(e.child(6).text())
                .setTravelerCheckCurrency(e.child(7).text())
                .setForeignCheckCurrency(e.child(8).text())
                .setSellingBaseRate(e.child(9).text())
                .setCurrencyInDollar(e.child(11).text())
                .build();
    };

    @Override
    Function<Element, ParsedCurrency> getMapper() {
        return mapper;
    }

    @Override
    String getCurrencyListCssQuery() {
        return CURRENCY_LIST_CSS_QUERY;
    }

}
