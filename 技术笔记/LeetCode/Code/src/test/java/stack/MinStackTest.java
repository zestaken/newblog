package stack;

import org.junit.jupiter.api.Test;

public class MinStackTest {

    @Test
    public void test() {
        MinStack155 minStack = new MinStack155();
        minStack.push(512);
        minStack.push(-1024);
        minStack.push(-1024);
        minStack.push(512);
        minStack.pop();
        assert minStack.getMin() == -1024;
        minStack.pop();
        assert minStack.getMin() == -1024;
        minStack.pop();
        assert minStack.getMin() == 512;

    }
}
