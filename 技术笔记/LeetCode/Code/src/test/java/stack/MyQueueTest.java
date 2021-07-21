package stack;

import org.junit.jupiter.api.Test;

public class MyQueueTest {

    @Test
    public void test() {
        MyQueue232 myQueue = new MyQueue232();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        assert myQueue.peek() == 1; // return 1
        assert myQueue.pop() == 1; // return 1, queue is [2]
        assert myQueue.empty() == false; // return false

    }
}
