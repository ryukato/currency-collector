package app.base.collector;

import java.util.Map;

public interface CurrencyListCollector<T> {
    T collect(Configuration config);

    interface Configuration {
        String url();
        Map<String, String> params();
        Map<String, String> headers();
    }
}
