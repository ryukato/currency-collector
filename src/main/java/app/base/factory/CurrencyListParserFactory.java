package app.base.factory;

import app.base.parser.CurrencyListParser;
import app.base.parser.KEBHanaCurrencyListParser;
import app.base.parser.WooribankCurrencyListParser;

public class CurrencyListParserFactory {

    public CurrencyListParser create(String parserKey) {
        if (parserKey == null || parserKey.isEmpty()) {
            throw new IllegalArgumentException("Invalid Parser key: empty parser key");
        }
        if (parserKey.equals("KEB")) {
            return new KEBHanaCurrencyListParser();
        } else if (parserKey.equals("WOORI")) {
            return new WooribankCurrencyListParser();
        } else  {
            throw new IllegalArgumentException(String.format("Invalid Parser key: no matched parser with key - %s", parserKey));
        }
    }
}
