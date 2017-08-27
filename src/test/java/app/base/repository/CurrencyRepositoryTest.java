package app.base.repository;

import app.base.domain.Currency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyRepositoryTest {

    @Autowired
    CurrencyRepository currencyRepository;


    @Test
    public void save() {
        BigDecimal sampleCurrency = new BigDecimal("1.111");
        Currency currency = Currency.builder()
                .setBaseDateTime(LocalDateTime.now())
                .setCurrency("USD")
                .setCurrencyInKorean("달러")
                .setBuyInCashCurrency(sampleCurrency)
                .setBuyInCashSpread(sampleCurrency)
                .setSellInCashCurrency(sampleCurrency)
                .setSellInCashSpread(sampleCurrency)
                .setSellInWireCurrency(sampleCurrency)
                .setTravelerCheckCurrency(sampleCurrency)
                .setForeignCheckCurrency(sampleCurrency)
                .setSellingBaseRate(sampleCurrency)
                .setCurrencyInDollar(sampleCurrency)
                .build();

        Currency savedCurrency = currencyRepository.save(currency);
        assertThat(savedCurrency.getId(), is(currency.getId()));
        assertThat(savedCurrency.getBaseDateTime(), is(currency.getBaseDateTime()));
        assertThat(savedCurrency.getBuyInCashCurrency(), is(sampleCurrency));

        Currency foundCurrency = currencyRepository.findOne(currency.getId());
        assertThat(foundCurrency.getId(), is(currency.getId()));
        assertThat(foundCurrency.getBaseDateTime(), is(currency.getBaseDateTime()));
        assertThat(foundCurrency.getBuyInCashCurrency(), is(sampleCurrency));
    }

}
