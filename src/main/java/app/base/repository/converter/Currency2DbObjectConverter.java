package app.base.repository.converter;

import app.base.domain.Currency;
import com.mongodb.BasicDBObject;
import org.springframework.core.convert.converter.Converter;

import java.time.ZoneOffset;
import java.util.Date;
import java.util.Map;

public class Currency2DbObjectConverter implements Converter<Currency, BasicDBObject> {
    @Override
    public BasicDBObject convert(Currency source) {
        Map<String, Object> map = new java.util.HashMap<>();
        map.put("baseDateTime", Date.from(source.getBaseDateTime().toInstant(ZoneOffset.UTC)));
        map.put("currency", source.getCurrency());
        map.put("currencyInKorean", source.getCurrencyInKorean());
        map.put("buyInCashCurrency", source.getBuyInCashCurrency().toPlainString());
        map.put("buyInCashSpread", source.getBuyInCashSpread().toPlainString());
        map.put("sellInCashCurrency", source.getSellInCashCurrency().toPlainString());
        map.put("sellInCashSpread", source.getSellInCashSpread().toPlainString());
        map.put("sellInWireCurrency", source.getSellInWireCurrency().toPlainString());
        map.put("travelerCheckCurrency", source.getTravelerCheckCurrency().toPlainString());
        map.put("foreignCheckCurrency", source.getForeignCheckCurrency().toPlainString());
        map.put("sellingBaseRate", source.getSellingBaseRate().toPlainString());
        map.put("currencyInDollar", source.getCurrencyInDollar().toPlainString());
        return new BasicDBObject(map);
    }
}
