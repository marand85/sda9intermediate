package bookstore;

import lombok.Getter;
import lombok.Setter;

// dzieki temu nie musimy pisac kodow
// getterow i setterow w klasie, robi to
// kompilator w trakcie kompilacji, dzieki
// bibliotece Lombok
@Setter
@Getter
public class Category {
    private Integer id;
    private String name;
    private Integer parentId;


}
