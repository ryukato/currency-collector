package app.base.event;

import java.time.LocalDateTime;

public interface Event<T> {
    T body();
    LocalDateTime createdAt();

}
