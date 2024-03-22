package be.kdg.kollections.lists;

import be.kdg.kollections.Kollections;

import java.util.Arrays;

public class ArrayList<E> implements List<E> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    private void expand() {
//        Object[] elementsNew = new Object[2*2*elements.length];
//        System.arraycopy(elements, 0 , elementsNew, 0, size);
//        elements  = elementsNew;

        //alternative
        // Double the size of the array
        elements = Arrays.copyOf(elements, 2*elements.length);
    }

    @Override
    public void add(int index, E element) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
//        int lengthTempObjects = size-index;
//        Object[] tempObjects = new Object[lengthTempObjects];
//
//        for (int i = 0 ; i < tempObjects.length; i++) {
//            tempObjects[i] = elements[index+i];
////            System.out.println(tempObjects[i]);
//
//        }
//        elements[index] = element;
//        for (int i = 0 ; i < tempObjects.length; i++) {
//            elements[index+i+1] = tempObjects[i];
//        }
//
//      //a quicker way to copy over elements of an array
        //copy everything from the array elements to elements, starting from index position to index potion index +1 , having length size - index
        if (size == elements.length) expand();
        if (size - index >= 0) System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public void add(E element) {
        add(size, element);
    }

    @Override
    public boolean remove(E element) {
        int index = Kollections.lineairSearch(this, element);
        if (index != -1){
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != 1;
    }

    @Override
    public void set(int index, E element) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        elements[index] = element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        E oldValue = (E) elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return oldValue;
    }

    @Override
    public int indexOf(E element) {
        return Kollections.lineairSearch(this, element);
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        return (E) elements[index];
    }
}
