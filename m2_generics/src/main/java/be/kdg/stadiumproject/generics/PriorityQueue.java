package be.kdg.stadiumproject.generics;

import java.util.*;

public class PriorityQueue<T> implements FIFOQueue<T>{
    private TreeMap<Integer, LinkedList<T>> linkedListTreeMap = new TreeMap<>(Comparator.reverseOrder());


    @Override
    public boolean enqueue(T element, int priority) {
        for (LinkedList<T> value : linkedListTreeMap.values()) {
            for (T t : value) {
                if (t == element) return false;
            }
        }

        if (linkedListTreeMap.containsKey(priority)){
            linkedListTreeMap.get(priority).addLast(element);
        }else {
            LinkedList<T> newLinkedlist = new LinkedList<T>();
            newLinkedlist.addLast(element);
            linkedListTreeMap.put(priority, newLinkedlist);
        }
        return true;

    }

    @Override
    public T dequeue() {
        T removeLast = null;
            if (linkedListTreeMap.firstEntry().getValue().isEmpty()){
                linkedListTreeMap.remove(linkedListTreeMap.firstKey());
                removeLast= linkedListTreeMap.get(linkedListTreeMap.firstKey()).removeLast();
            } else removeLast= linkedListTreeMap.get(linkedListTreeMap.firstKey()).removeLast();


        return removeLast;
    }

    @Override
    public int search(T element) {
        int position = 0;
        for (Map.Entry<Integer, LinkedList<T>> integerLinkedListEntry : linkedListTreeMap.entrySet()) {
            for (T t : integerLinkedListEntry.getValue()) {
                position++;
                if (t.equals(element)){
                    return position;
                }
            }
        };
        return -1;
    }

    @Override
    public int getSize() {
        int sum = 0;
        for (LinkedList<T> tLinkedList : linkedListTreeMap.values()) {
            sum += tLinkedList.size();
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Integer, LinkedList<T>> integerLinkedListEntry : linkedListTreeMap.entrySet()) {
            for (T t : integerLinkedListEntry.getValue()) {
                stringBuilder.append(integerLinkedListEntry.getKey()).append(": ");
                stringBuilder.append(t);
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
