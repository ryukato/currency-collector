package app.base.parser;

import app.base.domain.ParsedCurrency;
import org.jsoup.nodes.Element;

import java.util.Optional;
import java.util.function.Function;

public class WooribankCurrencyListParser extends AbstractHtmlCurrencyListParser {
    private static final String CURRENCY_LIST_CSS_QUERY = "tbody tr";
    private static final Function<Element, ParsedCurrency> mapper = e ->
            ParsedCurrency
                    .builder()
                    .setCurrency(Optional.ofNullable(e.child(0).child(0).text()).map(String::trim).orElse(""))
                    .setCurrencyInKorean(Optional.ofNullable(e.child(1).text()).map(String::trim).orElse(""))
                    .setBuyInWireCurrency(e.child(2).text())
                    .setSellInWireCurrency(e.child(3).text())
                    .setBuyInCashCurrency(e.child(4).text())
                    .setBuyInCashSpread(e.child(5).text())
                    .setSellInCashCurrency(e.child(6).text())
                    .setSellInCashSpread(e.child(7).text())
                    .setTravelerCheckCurrency(e.child(8).text())
                    .setSellingBaseRate(e.child(9).text())
                    .setCurrencyInDollar(e.child(11).text())
                    .build();


    @Override
    String getCurrencyListCssQuery() {
        return CURRENCY_LIST_CSS_QUERY;
    }

    @Override
    Function<Element, ParsedCurrency> getMapper() {
        return mapper;
    }
}
