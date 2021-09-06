package hashTable;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReconstructItineraryTest {

    @Test
    public void test1() {

        ReconstructItinerary332 reconstructItinerary = new ReconstructItinerary332();
        //测试数据1
        //输入
        List<List<String>> input1 = new ArrayList<>();
        String[] strings = {"JFK","SFO","JFK","ATL","SFO","ATL","ATL","JFK","ATL","SFO"};
        for(int i = 0; i < strings.length; i += 2) {
            List<String> strings1 = new ArrayList<>();
            strings1.add(strings[i]);
            strings1.add(strings[i + 1]);
            input1.add(strings1);
        }
        //期望结果
        List<String> expect1 = new ArrayList<>();
        String[] strings1 = {"JFK","ATL","JFK","SFO","ATL","SFO"};
        expect1.addAll(Arrays.asList(strings1));

        //测试数据2
        //输入
        List<List<String>> input2 = new ArrayList<>();
        String[] strings2 = { "JFK","KUL" , "JFK","NRT" , "NRT","JFK" };
        for(int i = 0; i < strings2.length; i += 2) {
            List<String> temp2 = new ArrayList<>();
            temp2.add(strings2[i]);
            temp2.add(strings2[i + 1]);
            input2.add(temp2);
        }
        //期望结果
        List<String> expect2 = new ArrayList<>();
        String[] strings3 = {"JFK", "NRT", "JFK", "KUL"};
        expect2.addAll(Arrays.asList(strings3));


        List<String> res1 = reconstructItinerary.findItinerary(input1);
        assertEquals(expect1, res1);

        List<String> res2 = reconstructItinerary.findItinerary(input2);
        assertEquals(expect2, res2);

    }
}
