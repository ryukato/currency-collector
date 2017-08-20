package app.base.collector;

import java.util.Collections;
import java.util.Map;

public class EmptyHeaderParamConfiguration implements CurrencyListCollector.Config {
    private final String url;

    EmptyHeaderParamConfiguration(String url) {
        this.url = url;
    }

    @Override
    public String url() {
        return this.url;
    }

    @Override
    public Map<String, String> params() {
        return Collections.emptyMap();
    }

    @Override
    public Map<String, String> headers() {
        return Collections.emptyMap();
    }

    @Override
    public String toString() {
        return "EmptyHeaderParamConfiguration{" +
                "url='" + url + '\'' +
                "headers= empty"+ '\'' +
                "params= empty"+ '\'' +
                '}';
    }
}