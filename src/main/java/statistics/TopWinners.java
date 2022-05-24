package statistics;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class TopWinners {

    /**
     * Returns the names and the number of wins of the top 12 winners
     * @return A {@code Map<String, Long> } repository, which contains the names and the number of wins of players
     * @throws IOException An exception if there is no such available file
     */

    public Map<String, Long> getTopWinnersFromFile() throws IOException {
        var repository = new WinnerRepository();
        repository.loadFromFile(new File("winners.json"));
        return repository.getTopWinners();
    }
}
