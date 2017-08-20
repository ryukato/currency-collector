package app.base.repository;

import app.base.event.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("eventRepository")
public interface EventRepository<E extends Event> extends MongoRepository<E, String> {
}
