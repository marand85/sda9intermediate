package bookstore.categories.services;

import bookstore.categories.daos.CategorySource;
import bookstore.categories.daos.InMemoryCategoryDAO;
import bookstore.categories.dtos.AdminCategoryDTO;
import bookstore.categories.entities.Category;
import bookstore.categories.entities.CategoryState;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SearchCategoriesService {
    CategorySource source = InMemoryCategoryDAO.getInstance();

    public List<AdminCategoryDTO> filterCategories(String searchCategoryName) {
        return source.getCategories()
                .stream()
                .map(category -> buildAdminCategoryDTO(category))
                .peek(e ->
                        e.setParentCat(findParent(e)
                                .map(m -> buildAdminCategoryDTO(m))
                                .orElse(null)))
                .peek(e -> {
                    if (e.getText().equals(searchCategoryName)) {
                        e.getState().setSelected(true);
                        e.getState().setOpen(true);
                        openParents(e);
                    }
                })
                .collect(Collectors.toList());
    }

    private Optional<Category> findParent(AdminCategoryDTO child) {
        if (child.getParentCategoryId() == null) {
            return Optional.empty();
        }
        return source
                .findCategoryById(Integer
                        .valueOf(child.getParentCategoryId()));
    }

    private AdminCategoryDTO buildAdminCategoryDTO(Category category) {
        return AdminCategoryDTO.builder()
                .id(category.getId().toString())
                .parentCategoryId(category.getParentId() == null
                        ? null : category.getParentId().toString())
                .text(category.getName())
                .state(new CategoryState())
                .build();
    }

    private void openParents(AdminCategoryDTO child) {
        AdminCategoryDTO parent = child.getParentCat();
        if (parent == null) {
            return;
        }
        parent.getState().setOpen(true);
        openParents(parent);
    }
}
