package app.base.collector;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class JsoupCurrencyListCollector implements CurrencyListCollector<Document> {
    @Override
    public Document collect(Configuration config) {
        validateConfigNotEmpty(config);

        try {
            return buildConnection(config).get();
        } catch (IOException e) {
            throw new InvalidCollectConfigurationException(e);
        }
    }

    private Connection buildConnection(Configuration config) {
        return Optional.of(config).map(c -> {
            String url = Optional.ofNullable(config.url()).orElseThrow(() -> new InvalidCollectConfigurationException("URL is empty"));
            Connection connection = buildConnection(url);
            Optional.ofNullable(c.headers()).ifPresent(headers -> connection.headers(headers));
            Optional.ofNullable(c.params()).ifPresent(params -> connection.data(params));
            return connection;

        }).orElseThrow(() -> new InvalidCollectConfigurationException("Invalid Config"));
    }

    private Connection buildConnection(String url) {
        try {
            return Jsoup.connect(url);
        }catch (IllegalArgumentException e) {
            throw new InvalidCollectConfigurationException(e);
        }
    }

    private Configuration validateConfigNotEmpty(Configuration config) {
        return Optional.ofNullable(config).orElseThrow(() -> new InvalidCollectConfigurationException("Configuration is empty"));
    }
}