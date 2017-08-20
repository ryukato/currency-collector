package app.base.event;

import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class AbstractEvent<T> implements Event<T> {
    private final LocalDateTime createdAt;
    private final String id;
    private final String createdBy;
    private final T body;

    public AbstractEvent(T body) {
        this(body, LocalDateTime.now(), "SYSTEM");
    }

    public AbstractEvent(T body, LocalDateTime createdAt, String createdBy) {
        this.body = body;
        this.id = UUID.randomUUID().toString();
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    @Override
    public String id() {
        return this.id;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public String createdBy() {
        return createdBy;
    }

    @Override
    @Field("type")
    public Class<? extends Event> type() {
        return getClass();
    }

    @Override
    @Field("body")
    public T body() {
        return body;
    }
}
