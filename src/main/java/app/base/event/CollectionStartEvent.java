package app.base.event;

import app.base.collector.CurrencyListCollector;

public class CollectionStartEvent extends AbstractEvent<CurrencyListCollector.Configuration> {
    public CollectionStartEvent(CurrencyListCollector.Configuration configuration) {
        super(configuration);
    }
}
