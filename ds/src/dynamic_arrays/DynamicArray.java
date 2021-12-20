package dynamic_arrays;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author : Odinga David
 * @since : 12/20/21, Mon
 */
@SuppressWarnings("unchecked")
public class DynamicArray<T> implements Iterable<T>{

    private T[] array;


    public DynamicArray() {
        this(0);
    }

    public DynamicArray(int size) {
        assert size >= 0 : "Array size has to be greater than 0";
        array = (T[]) new Object[size];
    }


    public int size() {
        return array.length;
    }

    public boolean isEmpty() {
        return array.length == 0;
    }

    public T get(int index) {
        if (index >= array.length )
            throw new IndexOutOfBoundsException("Invalid index");
        return array[index];
    }

    public void set(int index, T element){
        if (index >= array.length )
            throw new IndexOutOfBoundsException("Invalid index");
        array[index] = element;
    }

    public void clear() {
        for (T t:array)
            t=null;

        array = (T[]) new Object[0];
    }

    public void add(T elem){
        //Increase size
        T[] newArray= (T[]) new Object[array.length+1];

        //Copy all elements in first array to second
        System.arraycopy(array, 0, newArray, 0, array.length);

        //add element to new array
        newArray[array.length]=elem;

        // Assign new array to our array element
        array=newArray;
    }

    public void addAll(T ...t){
        for (T elem:t)
            add(elem);

    }

    public T removeAt(int index){
        if (index >= array.length)
            throw new IndexOutOfBoundsException("Invalid index");

        T elem= array[index];

        T[] newArray = (T[]) new Object[array.length - 1];

        for (int i = 0, j=0; i < array.length; i++, j++) {
            if (i==index) j--;
            else newArray[j]=array[i];
        }

        array=newArray;

        return  elem;
    }

    public boolean remove(Object obj){
        int index = indexOf(obj);

        if(index == -1) return false;
        removeAt(index);

        return true;
    }

    public int indexOf(Object obj) {
        for (int i = 0; i < array.length; i++) {
            if (obj==null ){
                if (array[i] == null) return i;
            }
            else if (array[i].equals(obj)) return i;
        }
        return -1;
    }

    public boolean contains(Object obj ){
        return indexOf (obj) != -1;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>(){

            int index=0;

            @Override
            public boolean hasNext() {
                return index<array.length;
            }

            @Override
            public T next() {
                if (!hasNext())
                 return null;

                return array[index];
            }

            @Override
            public void remove() {
                Iterator.super.remove();
            }

            @Override
            public void forEachRemaining(Consumer<? super T> action) {
                Iterator.super.forEachRemaining(action);
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }
}
