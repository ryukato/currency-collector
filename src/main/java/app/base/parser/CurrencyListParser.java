package app.base.parser;

public interface CurrencyListParser<T, R> {
    R parse(T t);
}