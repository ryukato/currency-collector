package app.base.event;

import java.time.LocalDateTime;

@SuppressWarnings("unused")
public abstract class AbstractEvent<T> implements Event<T> {
    private final LocalDateTime createdAt;

    private final String createdBy;

    AbstractEvent() {
        this(LocalDateTime.now(), "SYSTEM");
    }

    AbstractEvent(String createdBy) {
        this(LocalDateTime.now(), createdBy);
    }

    AbstractEvent(LocalDateTime createdAt, String createdBy) {
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

}
