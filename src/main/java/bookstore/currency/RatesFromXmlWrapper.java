package bookstore.currency;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RatesFromXmlWrapper extends RatesWrapper<RateFromXml> {

    @Override
    @JsonProperty("Table")
    public void setTable(String table) {
        super.setTable(table);
    }

    @JsonProperty("No")
    @Override
    public void setNo(String no) {
        super.setNo(no);
    }

    @JsonProperty("EffectiveDate")
    @Override
    public void setEffectiveDate(String effectiveDate) {
        super.setEffectiveDate(effectiveDate);
    }

    @JsonProperty("Rates")
    @Override
    public void setRates(List<RateFromXml> rates) {
        super.setRates(rates);
    }
}