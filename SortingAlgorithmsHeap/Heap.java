package com.company;
import java.util.Comparator;
// heap algorithm from textbook
// I had to go to CSMC at UTD to understand exactly how to make this work and thats where they explained to me what
// generics are and how the heap class was coded for generics and my two options were to make/design my own heap class
// or to learn how to make it work while using generics
// I understand how Binary trees work but the way its coded makes it very difficult to follow along
// looking back I should have tried to design and implement my own Heap class for better understanding
public class Heap<E> {
    private java.util.ArrayList<E> list = new java.util.ArrayList<>();

    private java.util.Comparator<? super E> c;
    public Heap() {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
    }
    public Heap(java.util.Comparator<E> c) {
        this.c = c;
    }
    public Heap(E[] objects) {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
        for (int i = 0; i < objects.length; i++) {
            add(objects[i]);
        }
    }
    public void add(E newObject) {
        list.add(newObject);
        int currentIndex = list.size() - 1;
        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;
            if (c.compare(list.get(currentIndex), list.get(parentIndex)) > 0) {
                E temp = list.get(currentIndex);
                list.set(currentIndex, list.get(parentIndex));
                list.set(parentIndex, temp);
            } else {
                break;
            }
            currentIndex = parentIndex;
        }
    }
    public E remove() {
        if (list.size() == 0) {
            return null;
        }
        E removedObject = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);

        int currentIndex = 0;
        while (currentIndex < list.size()) {
            int leftChildIndex = 2 * currentIndex + 1;
            int rightChildIndex = 2 * currentIndex + 2;

            if (leftChildIndex >= list.size()) {
                break;
            }
            int maxIndex = leftChildIndex;

            if (rightChildIndex < list.size()) {
                if (c.compare(list.get(maxIndex), list.get(rightChildIndex)) < 0) {
                    maxIndex = rightChildIndex;
                }
            }

            if (c.compare(list.get(currentIndex), list.get(maxIndex)) < 0) {
                E temp = list.get(maxIndex);
                list.set(maxIndex, list.get(currentIndex));
                list.set(currentIndex, temp);
                currentIndex = maxIndex;
            } else {
                break;
            }
        }

        return removedObject;
    }
    public int getSize() {
        return list.size();
    }
    public boolean isEmpty() {
        return list.size() == 0;
    }
    public static <E> void FernandoTorresHeapSort(E[] list) {
        FernandoTorresHeapSort(list, (e1, e2) -> ((Comparable<E>) e1).compareTo(e2));
    }
    public static <E> void FernandoTorresHeapSort(E[] list, Comparator<E> c) {
        Heap<E> heap = new Heap<>(c);
        for (int i = 0; i < list.length; i++) {
            heap.add(list[i]);
        }
        for (int i = list.length - 1; i >= 0; i--) {
            list[i] = heap.remove();
        }
    }

}
