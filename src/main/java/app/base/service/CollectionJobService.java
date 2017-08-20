package app.base.service;

import app.base.domain.Currency;

import java.util.List;

public interface CollectionJobService<R> {
    R execute(CollectionJob<List<Currency>> collectionJob);
}
