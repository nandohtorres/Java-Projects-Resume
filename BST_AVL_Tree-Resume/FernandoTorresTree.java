package com.company;
import java.util.Collection;
import java.util.Iterator;

public interface FernandoTorresTree<E> extends Collection<E> {
    // return true if the element is in the tree
    public boolean search(E e);

    // insert element e into binary tree
    // return true if the element is inserted successfully
    public boolean insert(E e);

    // delete the specified element form the tree
    // return true if the element is deleted successfully
    public boolean delete(E e);

    // get the number of elements in the tree
    public int getSize();

    // inorder traversal from root
    public void inorder();

    // postorder traversal from root
    public void postorder();

    // preorder traversal from root
    public void preorder();

    // return true if the tree is empty, the default isEmpty
    @Override
    public default boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public default boolean contains(Object e) {
        return search((E) e);
    }

    @Override
    public default boolean add(E e) {
        return insert((E) e);
    }

    @Override
    public default boolean remove(Object e) {
        return delete((E) e);
    }

    @Override
    public default int size() {
        return getSize();
    }

    @Override
    public default boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public default boolean addAll(Collection<? extends E> c) {
        boolean ret = false;
        for (E e : c) {
            if (add(e)) {
                ret = true;
            }
        }
        return ret;
    }
    @Override
    public default boolean removeAll(Collection<?> c) {
        boolean ret = false;
        for (Object o : c) {
            if (remove(o)) {
                ret = true;
            }
        }
        return ret;
    }
    @Override
    public default boolean retainAll(Collection<?> c) {
        boolean changed = false;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            E e = it.next();
            if (!c.contains(e)) {
                it.remove();
                changed = true;
            }
        }
        return changed;

    }
    @Override
    public default Object[] toArray() {
        Object[] array = new Object[size()];
        Iterator<E> it = iterator();
        int index = 0;
        while (it.hasNext()) {
            array[index] = it.next();
            index++;
        }
        return array;
    }
    @Override
    public default <T> T[] toArray(T[] array) {
        T[] arrayT = (T[]) new Object[size()];
        Iterator<E> it = iterator();
        int index = 0;
        while (it.hasNext()) {
            arrayT[index] = (T) it.next();
            index++;
        }
        return arrayT;
    }
}
