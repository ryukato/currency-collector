package app.base.repository.converter;

import app.base.domain.Currency;
import com.mongodb.BasicDBObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.Jsr310Converters;

import java.math.BigDecimal;

public class DbObject2CurrencyConverter implements Converter<BasicDBObject, Currency> {
    @Override
    public Currency convert(BasicDBObject source) {
        return Currency.builder()
                .setId(source.getString("_id"))
                .setBaseDateTime(Jsr310Converters.DateToLocalDateTimeConverter.INSTANCE.convert(source.getDate("baseDateTime")))
                .setCurrency(source.getString("currency"))
                .setCurrencyInKorean(source.getString("currencyInKorean"))
                .setBuyInCashCurrency(new BigDecimal(source.getString("buyInCashCurrency")))
                .setBuyInCashSpread(new BigDecimal(source.getString("buyInCashSpread")))
                .setSellInCashCurrency(new BigDecimal(source.getString("sellInCashCurrency")))
                .setSellInCashSpread(new BigDecimal(source.getString("sellInCashSpread")))
                .setSellInWireCurrency(new BigDecimal(source.getString("sellInWireCurrency")))
                .setTravelerCheckCurrency(new BigDecimal(source.getString("travelerCheckCurrency")))
                .setForeignCheckCurrency(new BigDecimal(source.getString("foreignCheckCurrency")))
                .setSellingBaseRate(new BigDecimal(source.getString("sellingBaseRate")))
                .setCurrencyInDollar(new BigDecimal(source.getString("currencyInDollar")))
                .build();
    }
}
