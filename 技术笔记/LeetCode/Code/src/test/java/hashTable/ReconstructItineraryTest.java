package hashTable;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReconstructItineraryTest {

    @Test
    public void test1() {
        InputStream in = System.in;
        ReconstructItinerary332 reconstructItinerary = new ReconstructItinerary332();
       // {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
        List<List<String>> input1 = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        strings.add("JFK");
        strings.add("SFO");
        input1.add(strings);
        strings.clear();
        strings.add("JFK");
        strings.add("ATL");
        input1.add(strings);
        strings.clear();
        strings.add("SFO");
        strings.add("ATL");
        input1.add(strings);
        strings.clear();
        strings.add("ATL");
        strings.add("JFK");
        input1.add(strings);
        strings.clear();
        strings.add("ATL");
        strings.add("SFO");
        strings.clear();
        List<String> res1 = new ArrayList<>();
        res1.add("JFK");
        res1.add("ATL");
        res1.add("JFK");
        res1.add("SFO");
        res1.add("ATL");
        res1.add("SFO");

        List<String> res = reconstructItinerary.findItinerary(input1);

        assertEquals(res1, res);

    }
}
