package app.base.parser;

import app.base.domain.ParsedCurrency;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

abstract class AbstractHtmlCurrencyListParser implements CurrencyListParser<Document, List<ParsedCurrency>> {
    Elements getElements(Document document, String currencyListElementsQuery) {
        return Optional.ofNullable(document)
                .map(d -> d.select(currencyListElementsQuery))
                .orElseThrow(() -> new IllegalArgumentException("Invalid document - given document is null"));
    }

    @Override
    public List<ParsedCurrency> parse(Document document) {
        Elements rowElements = getElements(document, getCurrencyListCssQuery());
        if (rowElements == null || rowElements.isEmpty()) {
            return Collections.emptyList();
        }
        return rowElements.parallelStream()
                .map(getMapper()).collect(Collectors.toList());
    }

    abstract Function<Element, ParsedCurrency> getMapper();
    abstract String getCurrencyListCssQuery();

}
