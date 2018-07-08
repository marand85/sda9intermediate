package bookstore.currency;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class RatesWrapper<T extends Rate> {

    private String table;
    private String no;
    private String effectiveDate;
    private List<T> rates;
}
