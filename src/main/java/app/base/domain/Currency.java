package app.base.domain;

import app.base.error.InvalidCurrencyException;
import app.base.util.LocalDateTimeUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("unused")
@Document
public class Currency {
    @Id
    private final String id;
    private final LocalDateTime baseDateTime;
    private final String currency;
    private final String currencyInKorean;
    private final BigDecimal buyInCashCurrency;
    private final BigDecimal buyInCashSpread;
    private final BigDecimal sellInCashCurrency;
    private final BigDecimal sellInCashSpread;
    private final BigDecimal buyInWireCurrency;
    private final BigDecimal sellInWireCurrency;
    private final BigDecimal travelerCheckCurrency;
    private final BigDecimal foreignCheckCurrency;
    private final BigDecimal sellingBaseRate;
    private final BigDecimal currencyInDollar;

    private Currency(
                    String id,
                    LocalDateTime baseDateTime,
                    String currency,
                    String currencyInKorean,
                    BigDecimal buyInCashCurrency,
                    BigDecimal buyInCashSpread,
                    BigDecimal sellInCashCurrency,
                    BigDecimal sellInCashSpread,
                    BigDecimal buyInWireCurrency,
                    BigDecimal sellInWireCurrency,
                    BigDecimal travelerCheckCurrency,
                    BigDecimal foreignCheckCurrency,
                    BigDecimal sellingBaseRate,
                    BigDecimal currencyInDollar) {
        this.id = id;
        this.baseDateTime = baseDateTime;
        this.currency = currency;
        this.currencyInKorean = currencyInKorean;
        this.buyInCashCurrency = buyInCashCurrency;
        this.buyInCashSpread = buyInCashSpread;
        this.sellInCashCurrency = sellInCashCurrency;
        this.sellInCashSpread = sellInCashSpread;
        this.buyInWireCurrency = buyInWireCurrency;
        this.sellInWireCurrency = sellInWireCurrency;
        this.travelerCheckCurrency = travelerCheckCurrency;
        this.foreignCheckCurrency = foreignCheckCurrency;
        this.sellingBaseRate = sellingBaseRate;
        this.currencyInDollar = currencyInDollar;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getBaseDateTime() {
        return baseDateTime;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCurrencyInKorean() {
        return currencyInKorean;
    }

    public BigDecimal getBuyInCashCurrency() {
        return buyInCashCurrency;
    }

    public BigDecimal getBuyInCashSpread() {
        return buyInCashSpread;
    }

    public BigDecimal getSellInCashCurrency() {
        return sellInCashCurrency;
    }

    public BigDecimal getSellInCashSpread() {
        return sellInCashSpread;
    }

    public BigDecimal getBuyInWireCurrency() {
        return buyInWireCurrency;
    }

    public BigDecimal getSellInWireCurrency() {
        return sellInWireCurrency;
    }

    public BigDecimal getTravelerCheckCurrency() {
        return travelerCheckCurrency;
    }

    public BigDecimal getForeignCheckCurrency() {
        return foreignCheckCurrency;
    }

    public BigDecimal getSellingBaseRate() {
        return sellingBaseRate;
    }

    public BigDecimal getCurrencyInDollar() {
        return currencyInDollar;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "baseDateTime='" + baseDateTime + '\'' +
                "currency='" + currency + '\'' +
                ", currencyInKorean='" + currencyInKorean + '\'' +
                ", buyInCashCurrency=" + buyInCashCurrency +
                ", buyInCashSpread=" + buyInCashSpread +
                ", sellInCashCurrency=" + sellInCashCurrency +
                ", sellInCashSpread=" + sellInCashSpread +
                ", buyInWireCurrency=" + buyInWireCurrency +
                ", sellInWireCurrency=" + sellInWireCurrency +
                ", travelerCheckCurrency=" + travelerCheckCurrency +
                ", foreignCheckCurrency=" + foreignCheckCurrency +
                ", sellingBaseRate=" + sellingBaseRate +
                ", currencyInDollar=" + currencyInDollar +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private LocalDateTime baseDateTime;
        private String currency;
        private String currencyInKorean;
        private BigDecimal buyInCashCurrency;
        private BigDecimal buyInCashSpread;
        private BigDecimal sellInCashCurrency;
        private BigDecimal sellInCashSpread;
        private BigDecimal buyInWireCurrency;
        private BigDecimal sellInWireCurrency;
        private BigDecimal travelerCheckCurrency;
        private BigDecimal foreignCheckCurrency;
        private BigDecimal sellingBaseRate;
        private BigDecimal currencyInDollar;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setBaseDateTime(String baseDateTime) {
            this.baseDateTime = LocalDateTimeUtil.from(baseDateTime, "yyyMMdd HHmmss");
            return this;
        }

        public Builder setBaseDateTime(LocalDateTime baseDateTime) {
            this.baseDateTime = baseDateTime;
            return this;
        }

        public Builder setCurrency(String currency) {
            if (StringUtils.isEmpty(currency)) {
                throw new InvalidCurrencyException("Empty currency");
            }
            this.currency = currency;
            return this;
        }

        public Builder setCurrencyInKorean(String currencyInKorean) {
            if (StringUtils.isEmpty(currencyInKorean)) {
                throw new InvalidCurrencyException("Empty currency");
            }
            this.currencyInKorean = currencyInKorean;
            return this;
        }

        private BigDecimal defaultCurrencyValueIfEmpty(BigDecimal input) {
            return Optional.ofNullable(input).orElse(new BigDecimal("0"));
        }

        public Builder setBuyInCashCurrency(BigDecimal buyInCashCurrency) {
            this.buyInCashCurrency = defaultCurrencyValueIfEmpty(buyInCashCurrency);
            return this;
        }

        public Builder setBuyInCashSpread(BigDecimal buyInCashSpread) {
            this.buyInCashSpread = defaultCurrencyValueIfEmpty(buyInCashSpread);
            return this;
        }

        public Builder setSellInCashCurrency(BigDecimal sellInCashCurrency) {
            this.sellInCashCurrency = defaultCurrencyValueIfEmpty(sellInCashCurrency);
            return this;
        }

        public Builder setSellInCashSpread(BigDecimal sellInCashSpread) {
            this.sellInCashSpread = defaultCurrencyValueIfEmpty(sellInCashSpread);
            return this;
        }

        public Builder setBuyInWireCurrency(BigDecimal buyInWireCurrency) {
            this.buyInWireCurrency = defaultCurrencyValueIfEmpty(buyInWireCurrency);
            return this;
        }

        public Builder setSellInWireCurrency(BigDecimal sellInWireCurrency) {
            this.sellInWireCurrency = defaultCurrencyValueIfEmpty(sellInWireCurrency);
            return this;
        }

        public Builder setTravelerCheckCurrency(BigDecimal travelerCheckCurrency) {
            this.travelerCheckCurrency = defaultCurrencyValueIfEmpty(travelerCheckCurrency);
            return this;
        }

        public Builder setForeignCheckCurrency(BigDecimal foreignCheckCurrency) {
            this.foreignCheckCurrency = defaultCurrencyValueIfEmpty(foreignCheckCurrency);
            return this;
        }

        public Builder setSellingBaseRate(BigDecimal sellingBaseRate) {
            this.sellingBaseRate = defaultCurrencyValueIfEmpty(sellingBaseRate);
            return this;
        }

        public Builder setCurrencyInDollar(BigDecimal currencyInDollar) {
            this.currencyInDollar = defaultCurrencyValueIfEmpty(currencyInDollar);
            return this;
        }

        public Currency build() {
            if (this.id == null || this.id.isEmpty()) {
                this.id = UUID.randomUUID().toString();
            }
            return new Currency(
                    id,
                    baseDateTime,
                    currency,
                    currencyInKorean,
                    buyInCashCurrency,
                    buyInCashSpread,
                    sellInCashCurrency,
                    sellInCashSpread,
                    buyInWireCurrency,
                    sellInWireCurrency,
                    travelerCheckCurrency,
                    foreignCheckCurrency,
                    sellingBaseRate,
                    currencyInDollar);
        }
    }
}