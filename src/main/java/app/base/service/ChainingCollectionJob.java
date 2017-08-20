package app.base.service;

import app.base.collector.CurrencyListCollector;
import app.base.domain.Currency;
import app.base.parser.CurrencyListParser;
import org.jsoup.nodes.Document;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChainingCollectionJob implements CollectionJob<List<Currency>> {
    private final CurrencyListCollector<Document> collector;
    private final CurrencyListParser<Document, List<Currency>> parser;
    private final CurrencyListCollector.Config config;
    private CollectionJob<List<Currency>> nextJob;

    private ChainingCollectionJob(
            CurrencyListCollector<Document> collector,
            CurrencyListParser<Document, List<Currency>> parser,
            CurrencyListCollector.Config config,
            CollectionJob<List<Currency>> nextJob) {
        this.collector = collector;
        this.parser = parser;
        this.config = config;
        this.nextJob = nextJob;
    }

    ChainingCollectionJob setNextJob(CollectionJob<List<Currency>> nextJob) {
        this.nextJob = nextJob;
        return this;
    }

    @Override
    public CollectionJob<List<Currency>> next() {
        return nextJob;
    }

    @Override
    public List<Currency> doJob() {
        return parser.parse(collector.collect(config));
    }

    static Builder builder() {
        return new Builder();
    }

    static class Builder {
        private CurrencyListCollector<Document> collector;
        private CurrencyListParser<Document, List<Currency>> parser;
        private CurrencyListCollector.Config config;
        private CollectionJob<List<Currency>> nextJob;

        Builder collector(CurrencyListCollector<Document> collector) {
            this.collector = collector;
            return this;
        }

        Builder parser(CurrencyListParser<Document, List<Currency>> parser) {
            this.parser = parser;
            return this;
        }

        Builder config(CurrencyListCollector.Config config) {
            this.config = config;
            return this;
        }

        Builder nextJob(CollectionJob<List<Currency>> nextJob) {
            this.nextJob = nextJob;
            return this;
        }

        ChainingCollectionJob build() {
            Optional.ofNullable(this.collector).orElseThrow(() -> new IllegalArgumentException("Collector has to be set"));
            Optional.ofNullable(this.parser).orElseThrow(() -> new IllegalArgumentException("Parser has to be set"));
            return Optional.ofNullable(this.nextJob)
                    .map(nextJob -> new ChainingCollectionJob(collector, parser, config, nextJob))
                    .orElse(new ChainingCollectionJob(collector, parser, config, null));
        }
    }
}