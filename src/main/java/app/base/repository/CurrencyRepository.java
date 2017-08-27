package app.base.repository;

import app.base.domain.Currency;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends MongoRepository<Currency, String> {
}
