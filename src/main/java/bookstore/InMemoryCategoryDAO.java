package bookstore;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryCategoryDAO {
    // DAO Data Access Object
    // DTO Data Transfer Object

    public List<Category> initializeCategories() {
        List<String> linesFromFile = null;
        try {
            linesFromFile = Files.readAllLines(Paths
                    .get("C:\\projects\\sda9intermediate\\src\\main\\resources\\kategorie.txt"), Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        populateCategories(linesFromFile);
        return Lists.newArrayList();
    }

    private void populateCategories(List<String> linesFromFile) {
        Integer idCounter = 1;
        List<Category> categoryList = linesFromFile.stream()
                .map(e -> new Category(idCounter, e))
                .collect(Collectors.toList());
        Map<Integer, List<Category>> categoryMap = categoryList.stream()
                .collect(Collectors.groupingBy(e -> countSpaces(e)));
        populateRecursive(0, categoryMap);
    }

    private void populateRecursive(int level, Map<Integer, List<Category>> categoryMap) {
        List<Category> categories = categoryMap.get(level);
        for (Category category : categories) {
        }
    }

    private int countSpaces(Category category) {
        return category.getName().startsWith(" ") ?
                category.getName().split("\\S")[0].length() : 0;
    }
}
