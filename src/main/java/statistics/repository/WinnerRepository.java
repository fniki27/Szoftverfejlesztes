package statistics.repository;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import statistics.Winners;
import statistics.repository.GsonRepository;

public class WinnerRepository extends GsonRepository<Winners> {

    public WinnerRepository() {
        super(Winners.class);
    }

    public Map<String, Long> getTopWinners() {
        Map<String, Long> listOfWinners = elements.stream().collect(Collectors.groupingBy(Winners::getWinner, Collectors.counting()));
        Map<String, Long> sortedWinners = listOfWinners.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(12)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (w1, w2) -> w1, LinkedHashMap::new));
        return sortedWinners;

    }

}