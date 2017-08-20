package app.base.service;

import app.base.domain.Currency;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SimpleAddMergeCollectionJobService implements CollectionJobService<List<Currency>> {
    @Override
    public List<Currency> execute(CollectionJob<List<Currency>> collectionJob) {
        CollectionJob<List<Currency>> currentJob = collectionJob;
        List<Currency> currencyList = currentJob.doJob();
        while (currentJob != null) {
            if (currentJob.hasNext()) {
                List<Currency> currencyList1 = currentJob.next().doJob();
                if (!currencyList1.isEmpty()) {
                    Map<String, Currency> currencyMap = Collections.unmodifiableMap(currencyList.stream().collect(Collectors.toMap(Currency::getCurrency, c -> c)));
                    currencyList.addAll(currencyList1.stream().filter(c -> !currencyMap.containsKey(c.getCurrency())).collect(Collectors.toList()));
                }
            }

            if (!currentJob.hasNext()) {
                break;
            } else {
                currentJob = currentJob.next();
            }
        }
        return currencyList;
    }
}