package app.base.event;

import app.base.collector.CurrencyListCollector;

public class CollectionStartEvent extends AbstractEvent<CurrencyListCollector.Config> {
    public CollectionStartEvent(CurrencyListCollector.Config configuration) {
        super(configuration);
    }
}
