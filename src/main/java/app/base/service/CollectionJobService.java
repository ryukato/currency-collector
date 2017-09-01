package app.base.service;

public interface CollectionJobService<R> {
    R execute(CollectionJob<R> collectionJob);
}
