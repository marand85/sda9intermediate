package bookstore;

import java.util.List;

public interface CategorySource {
    void updateCategory(Category category);
    List<Category> findCategoriesByName(String name);
    List<Category> getCategories();
    Category findCategoryById(Integer id);
}
