package bookstore.currency;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RatesWrapper {

    private String table;
    private String no;
    private String effectiveDate;
    private List<Rate> rates;
}
