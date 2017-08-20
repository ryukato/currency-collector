package app.base.parser;

import app.base.domain.Currency;
import org.jsoup.nodes.Element;

import java.util.Optional;
import java.util.function.Function;

class WooribankCurrencyListParser extends AbstractHtmlCurrencyListParser {
    private final Function<Element, Currency> mapper = e ->
            Currency
                    .builder()
                    .setCurrency(Optional.ofNullable(e.child(0).child(0).text()).map(String::trim).orElse(""))
                    .setCurrencyInKorean(Optional.ofNullable(e.child(1).text()).map(String::trim).orElse(""))
                    .setBuyInWireCurrency(toBigDecimal(e.child(2).text()))
                    .setSellInWireCurrency(toBigDecimal(e.child(3).text()))
                    .setBuyInCashCurrency(toBigDecimal(e.child(4).text()))
                    .setBuyInCashSpread(toBigDecimal(e.child(5).text()))
                    .setSellInCashCurrency(toBigDecimal(e.child(6).text()))
                    .setSellInCashSpread(toBigDecimal(e.child(7).text()))
                    .setTravelerCheckCurrency(toBigDecimal(e.child(8).text()))
                    .setSellingBaseRate(toBigDecimal(e.child(9).text()))
                    .setCurrencyInDollar(toBigDecimal(e.child(11).text()))
                    .build();


    @Override
    Function<Element, Currency> getMapper() {
        return mapper;
    }
}
