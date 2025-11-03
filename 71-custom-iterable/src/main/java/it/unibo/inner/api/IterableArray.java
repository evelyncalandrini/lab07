package it.unibo.inner.api;

import java.util.Iterator;

public class IterableArray<T> implements IterableWithPolicy<T>{
    private final T[] element;
    
    public IterableArray(T[] element) {
        this.element = element;
    }

    public void setIterationPolicy(Predicate<T> filter) {

    }

    private class ArrayIterator implements Iterator<T> {
        private int index = 0;

        public boolean hasNext() {
            return index < element.length;
        }

        public T next() {
            return element[index++];
        }
    }

    public Iterator<T> iterator(){
        return new ArrayIterator();
    }


    
}
