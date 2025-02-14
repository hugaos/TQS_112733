package tqs;

public class BoundedTqsStack<T> extends TqsStack<T> {
    private final int maxSize;

    BoundedTqsStack(int maxSize) {
        super();
        this.maxSize = maxSize;
    }

    @Override
    public void push(T element) {   
        if (this.size() == maxSize) {
            throw new IllegalStateException();
        }

        super.push(element);
    }
}

