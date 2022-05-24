package statistics;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class Winners {

    private String name1;
    private String name2;
    private String winner;

    public Winners(String name1, String name2, String winner) {

        this.name1 = name1;
        this.name2 = name2;
        this.winner = winner;
    }

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }

    public String getWinner() {
        return winner;
    }

}
