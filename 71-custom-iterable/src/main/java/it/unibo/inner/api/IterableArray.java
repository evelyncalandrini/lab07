package it.unibo.inner.api;

import java.util.Arrays;
import java.util.Iterator;

public class IterableArray<T> implements IterableWithPolicy<T>{
    private final T[] backingArray;
    private Predicate<T> filter;
    
    public IterableArray(final T[] element, final Predicate<T> filter) {
        this.backingArray = Arrays.copyOf(element, element.length);
        this.filter = filter; 
    }
     
    public IterableArray(final T[] element) {
        this.backingArray = element;
        this.filter = filter -> true; 
    }

    public void setIterationPolicy(final Predicate<T> filter) {
        this.filter = filter; 
    }

    public Iterator<T> iterator(){
        return new ArrayIterator();
    }

    @Override
    public String toString() {
        return Arrays.toString(backingArray);
    }

    private class ArrayIterator implements Iterator<T> {
        private int index = 0;

        public boolean hasNext() {
            while (index < backingArray.length && !filter.test(backingArray[index])) {
                index++;
            }
            return index < backingArray.length;
        }

        public T next() {
            return backingArray[index++];
        }
    }    
}
