package bookstore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// dzieki temu nie musimy pisac kodow
// getterow i setterow w klasie, robi to
// kompilator w trakcie kompilacji, dzieki
// bibliotece Lombok
@Setter
@Getter
@AllArgsConstructor
public class Category {
    private Integer id;
    private String name;
    private Integer parentId;
    private String poledowywalenia; // FIXME: 01.07.2018 wywal to

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
