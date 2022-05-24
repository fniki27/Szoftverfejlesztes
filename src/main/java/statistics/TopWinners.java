package statistics;

import statistics.repository.WinnerRepository;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class TopWinners {

    public Map<String, Long> getTopWinnersFromFile() throws IOException {
        var repository = new WinnerRepository();
        repository.loadFromFile(new File("winners.json"));
        return repository.getTopWinners();
    }
}
