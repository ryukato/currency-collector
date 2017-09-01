package app.base.collector;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Optional;

public class JsoupCurrencyListCollector implements CurrencyListCollector<Document> {
    @Override
    public Document collect(Config config) {
        validateConfigNotEmpty(config);

        try {
            return buildConnection(config).get();
        } catch (IOException e) {
            throw new InvalidCollectConfigurationException(e);
        }
    }

    private Connection buildConnection(Config config) {
        return Optional.of(config).map((Config c) -> {
            String url = Optional.ofNullable(config.url()).orElseThrow(() -> new InvalidCollectConfigurationException("URL is empty"));
            Connection connection = buildConnection(url);
            Optional.ofNullable(c.headers()).ifPresent(connection::headers);
            Optional.ofNullable(c.params()).ifPresent(connection::data);
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

    private Config validateConfigNotEmpty(Config config) {
        return Optional.ofNullable(config).orElseThrow(() -> new InvalidCollectConfigurationException("Configuration is empty"));
    }
}