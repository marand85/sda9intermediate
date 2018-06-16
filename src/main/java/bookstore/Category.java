package bookstore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// dzieki temu nie musimy pisac kodow
// getterow i setterow w klasie, robi to
// kompilator w trakcie kompilacji, dzieki
// bibliotece Lombok
@Setter
@Getter
@NoArgsConstructor
public class Category {
    private Integer id;
    private String name;
    private Integer parentId;


}
