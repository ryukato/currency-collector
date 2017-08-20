package app.base.collector;

import java.util.Collections;
import java.util.Map;

public interface CurrencyListCollector<T> {
    T collect(Config config);

    interface Config {
        String url();
        Map<String, String> params();
        Map<String, String> headers();

        static Config emptyHeaderParamsConfig(final String url) {
            return new Config() {
                @Override
                public String url() {
                    return url;
                }

                @Override
                public Map<String, String> params() {
                    return Collections.emptyMap();
                }

                @Override
                public Map<String, String> headers() {
                    return Collections.emptyMap();
                }
            };
        }
    }
}
