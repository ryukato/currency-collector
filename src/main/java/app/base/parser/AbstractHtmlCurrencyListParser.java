package app.base.parser;

import app.base.domain.Currency;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

abstract class AbstractHtmlCurrencyListParser implements CurrencyListParser<Document, List<Currency>> {
    private static final String CURRENCY_LIST_CSS_QUERY = "tbody tr";
    private static final String INVALID_NUMBER_CHARS = "[\\,\\-\\%]|\\s";

    private Elements getElements(Document document, String currencyListElementsQuery) {
        return Optional.ofNullable(document)
                .map(d -> d.select(currencyListElementsQuery))
                .orElseThrow(() -> new IllegalArgumentException("Invalid document - given document is null"));
    }

    @Override
    public List<Currency> parse(Document document) {
        Elements rowElements = getElements(document, getCurrencyListCssQuery());
        if (rowElements == null || rowElements.isEmpty()) {
            return Collections.emptyList();
        }
        return rowElements.parallelStream()
                .map(getMapper()).collect(Collectors.toList());
    }

    BigDecimal toBigDecimal(String input) {

        return Optional.ofNullable(input).map(i ->
                {
                    String removedInvalidChars = i.replaceAll(INVALID_NUMBER_CHARS, "");
                    if (removedInvalidChars.isEmpty()) {
                        return BigDecimal.ZERO;
                    } else {
                        return new BigDecimal(removedInvalidChars);
                    }
                }
        ).orElse(BigDecimal.ZERO);
    }

    abstract Function<Element, Currency> getMapper();
    private String getCurrencyListCssQuery() {
        return CURRENCY_LIST_CSS_QUERY;
    }

}
