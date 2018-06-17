package bookstore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminCategoryDTO
        implements CategoryInfoHolder {
    private String id;
    private String text;
}
