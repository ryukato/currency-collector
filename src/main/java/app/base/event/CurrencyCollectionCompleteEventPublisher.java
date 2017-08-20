package app.base.event;

import app.base.repository.CollectionEventRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

//@Component
//@Aspect
public class CurrencyCollectionCompleteEventPublisher {

    private final CollectionEventRepository collectionEventRepository;

    public CurrencyCollectionCompleteEventPublisher(CollectionEventRepository collectionEventRepository) {
        this.collectionEventRepository = collectionEventRepository;
    }

    Logger logger = LoggerFactory.getLogger(getClass());
    @Before(value = "execution(* app.base.collector.CurrencyListCollector.collect(..))", argNames = "config")
    public void publishCollectionStartEvent(JoinPoint joinPoint) {
        String argumentOfCollect = Arrays.stream(joinPoint.getArgs())
                .map(c -> String.format("%s: %s", c.getClass().getSimpleName(), c.toString()))
                .collect(Collectors.joining(", "));
        logger.info("Currency list collection start with: {}", argumentOfCollect);
    }
}
