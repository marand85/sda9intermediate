package bookstore.categories.dtos;

import bookstore.categories.CategoryInfoHolder;
import bookstore.categories.entities.CategoryState;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@Builder
public class AdminCategoryDTO
        implements CategoryInfoHolder {
    private String id;
    private String text;
    private CategoryState state;
    private AdminCategoryDTO parentCat; //to musiałem zmienić z uwagi na frontendowców
    private String parentCategoryId;

    //frontendowcy się uparli i oczekują od nas wartości parent id
    // wystawionej w takiej metodzie, albo # jeśli nie ma parenta
    public String getParent() {
        return StringUtils.isBlank(parentCategoryId) ? "#" : parentCategoryId;
    }
}
