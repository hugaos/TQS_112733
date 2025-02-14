package tqs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TqsStackTest {
    private TqsStack<Integer> stack;

    @BeforeEach
    void setUp(){
        stack= new TqsStack<>();
    }

    @Test
    void size_NewStack_ReturnsZero(){
        assertEquals(0, stack.size());
    }

    @Test
    void NewStack_isEmpty(){
        assertTrue(stack.isEmpty());
    }

    @Test 
    void size_AfterNPushes_ReturnsN_NotEmpty(){
        stack.push(1);
        stack.push(2);

        assertFalse(stack.isEmpty());
        assertEquals(2, stack.size());
    }

    @Test
    void PushN_PopsN(){
        int x=8;
        stack.push(x);
        assertEquals(x, stack.pop());
    }

    @Test
    void PushN_PeekN_SizeStaysSame(){
        stack.push(1);

        assertEquals(1, stack.peek());
        assertEquals(1, stack.size());
    }
    
    @Test
    void StackWithNPushes_AfterNPops_SizeIs0(){
        stack.push(1);
        stack.push(2);
        stack.push(3);

        stack.pop();
        stack.pop();
        stack.pop();

        assertEquals(0, stack.size());
    }

    @Test
    void PopFromEmptyStack_ThrowsException(){
        assertThrows(NoSuchElementException.class, () -> stack.pop());
    }

    @Test
    void PeekEmptyStack_ThrowsException(){
        assertThrows(NoSuchElementException.class, () -> stack.peek());

    }

    @Test
    public void pushOnFullStackThrowsException() {
        BoundedTqsStack boundedStack = new BoundedTqsStack(1);
        boundedStack.push(1);

        assertThrows(IllegalStateException.class, () -> boundedStack.push(2));
    }

    @Test
    void Cover_PopTopN(){
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        assertEquals(4, stack.popTopN(4));
    }
}
