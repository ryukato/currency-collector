package app.base.event;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Aspect()
//@Order(Ordered.LOWEST_PRECEDENCE)
//@SuppressWarnings("unused")
public class CurrencyCollectLoggingAspect {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Before(value = "execution(* app.base.collector.CurrencyListCollector.collect(..))", argNames = "config")
//    @Before(value = "app.base.collector.CurrencyListCollector.collect", argNames = "config")
    public void logCollectionConfiguration(JoinPoint joinPoint) {

        String argumentOfCollect = Arrays.stream(joinPoint.getArgs())
                .map(c -> String.format("%s: %s", c.getClass().getSimpleName(), c.toString()))
                .collect(Collectors.joining(", "));
        logger.info("Currency list collection start with: {}", argumentOfCollect);
    }


    @After("execution(* app.base.collector.JsoupCurrencyListCollector.collect(..))")
    public void logCollectedDocument(JoinPoint joinPoint) {
        String collectedDocument= Optional.ofNullable(joinPoint.getTarget())
                .map(c -> String.format("%s: %s", c.getClass().getSimpleName(), c.toString())).orElse("EMPTY");
        logger.info("Currency list collection completed with: {}", collectedDocument);
    }
}
