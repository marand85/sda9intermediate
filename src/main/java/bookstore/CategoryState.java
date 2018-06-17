package bookstore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryState {
    private Boolean open;
    private Boolean selected;
    private Boolean disabled;
}
