package app.base.domain;


import app.base.error.InvalidCurrencyException;
import org.springframework.util.StringUtils;

public class ParsedCurrency {
    private final String currency;
    private final String currencyInKorean;
    private final String buyInCashCurrency;
    private final String buyInCashSpread;
    private final String sellInCashCurrency;
    private final String sellInCashSpread;
    private final String buyInWireCurrency;
    private final String sellInWireCurrency;
    private final String travelerCheckCurrency;
    private final String foreignCheckCurrency;
    private final String sellingBaseRate;
    private final String currencyInDollar;

    private ParsedCurrency(String currency,
                          String currencyInKorean,
                          String buyInCashCurrency,
                          String buyInCashSpread,
                          String sellInCashCurrency,
                          String sellInCashSpread,
                          String buyInWireCurrency,
                          String sellInWireCurrency,
                          String travelerCheckCurrency,
                          String foreignCheckCurrency,
                          String sellingBaseRate,
                          String currencyInDollar) {
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

    public String getCurrency() {
        return currency;
    }

    public String getBuyInCashCurrency() {
        return buyInCashCurrency;
    }

    public String getBuyInCashSpread() {
        return buyInCashSpread;
    }

    public String getSellInCashCurrency() {
        return sellInCashCurrency;
    }

    public String getSellInCashSpread() {
        return sellInCashSpread;
    }

    public String getBuyInWireCurrency() {
        return buyInWireCurrency;
    }

    public String getSellInWireCurrency() {
        return sellInWireCurrency;
    }

    public String getTravelerCheckCurrency() {
        return travelerCheckCurrency;
    }

    public String getForeignCheckCurrency() {
        return foreignCheckCurrency;
    }

    public String getSellingBaseRate() {
        return sellingBaseRate;
    }

    public String getCurrencyInDollar() {
        return currencyInDollar;
    }

    @Override
    public String toString() {
        return "ParsedCurrency{" +
                "currency='" + currency + '\'' +
                ", currencyInKorean='" + currencyInKorean + '\'' +
                ", buyInCashCurrency='" + buyInCashCurrency + '\'' +
                ", buyInCashSpread='" + buyInCashSpread + '\'' +
                ", sellInCashCurrency='" + sellInCashCurrency + '\'' +
                ", sellInCashSpread='" + sellInCashSpread + '\'' +
                ", buyInWireCurrency='" + buyInWireCurrency + '\'' +
                ", sellInWireCurrency='" + sellInWireCurrency + '\'' +
                ", travelerCheckCurrency='" + travelerCheckCurrency + '\'' +
                ", foreignCheckCurrency='" + foreignCheckCurrency + '\'' +
                ", sellingBaseRate='" + sellingBaseRate + '\'' +
                ", currencyInDollar='" + currencyInDollar + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String currency;
        private String currencyInKorean;
        private String buyInCashCurrency;
        private String buyInCashSpread;
        private String sellInCashCurrency;
        private String sellInCashSpread;
        private String buyInWireCurrency;
        private String sellInWireCurrency;
        private String travelerCheckCurrency;
        private String foreignCheckCurrency;
        private String sellingBaseRate;
        private String currencyInDollar;

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

        private String defaultCurrencyValueIfEmpty(String input) {
            if (StringUtils.isEmpty(input)) {
                return "0.00";
            } else {
                return input;
            }
        }

        public Builder setBuyInCashCurrency(String buyInCashCurrency) {
            this.buyInCashCurrency = defaultCurrencyValueIfEmpty(buyInCashCurrency);
            return this;
        }

        public Builder setBuyInCashSpread(String buyInCashSpread) {
            this.buyInCashSpread = defaultCurrencyValueIfEmpty(buyInCashSpread);
            return this;
        }

        public Builder setSellInCashCurrency(String sellInCashCurrency) {
            this.sellInCashCurrency = defaultCurrencyValueIfEmpty(sellInCashCurrency);
            return this;
        }

        public Builder setSellInCashSpread(String sellInCashSpread) {
            this.sellInCashSpread = defaultCurrencyValueIfEmpty(sellInCashSpread);
            return this;
        }

        public Builder setBuyInWireCurrency(String buyInWireCurrency) {
            this.buyInWireCurrency = defaultCurrencyValueIfEmpty(buyInWireCurrency);
            return this;
        }

        public Builder setSellInWireCurrency(String sellInWireCurrency) {
            this.sellInWireCurrency = defaultCurrencyValueIfEmpty(sellInWireCurrency);
            return this;
        }

        public Builder setTravelerCheckCurrency(String travelerCheckCurrency) {
            this.travelerCheckCurrency = defaultCurrencyValueIfEmpty(travelerCheckCurrency);
            return this;
        }

        public Builder setForeignCheckCurrency(String foreignCheckCurrency) {
            this.foreignCheckCurrency = defaultCurrencyValueIfEmpty(foreignCheckCurrency);
            return this;
        }

        public Builder setSellingBaseRate(String sellingBaseRate) {
            this.sellingBaseRate = defaultCurrencyValueIfEmpty(sellingBaseRate);
            return this;
        }

        public Builder setCurrencyInDollar(String currencyInDollar) {
            this.currencyInDollar = defaultCurrencyValueIfEmpty(currencyInDollar);
            return this;
        }

        public ParsedCurrency build() {
            return new ParsedCurrency(currency,
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
