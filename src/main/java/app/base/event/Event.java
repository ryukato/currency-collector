package app.base.event;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "events")
public interface Event<T> {
    @Id
    String id();


    Class<? extends Event> type();

    @Field("body")
    T body();

    @CreatedDate
    LocalDateTime createdAt();

    @CreatedBy
    String createdBy();


}
