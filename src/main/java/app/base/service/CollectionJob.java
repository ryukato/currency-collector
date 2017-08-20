package app.base.service;

public interface CollectionJob<R> {
    CollectionJob<R> next();
    R doJob();

    default boolean hasNext() {
        return next() != null;
    }
}
