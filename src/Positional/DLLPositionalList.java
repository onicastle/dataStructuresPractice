package Positional;

import Interfaces.Position;
import Interfaces.PositionalList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DLLPositionalList<E> implements PositionalList<E> {


    private static class Node<E> implements Position<E>{

        private E element;
        private Node<E> next;
        private Node<E> prev;



        public Node(E element, Node<E> next, Node<E> prev) {
            super();
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        public Node() {
            super();
            this.element = null;
            this.next = null;
            this.prev = null;
        }


        public Node<E> getNext() {
            return next;
        }



        public void setNext(Node<E> next) {
            this.next = next;
        }



        public Node<E> getPrev() {
            return prev;
        }



        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }



        public void setElement(E element) {
            this.element = element;
        }



        @Override
        public E getElement() {
            // EODO Auto-generated method stub
            return this.element;
        }

    }

    private class PositionalListIterator<E> implements Iterator<E>{
        private Node<E> currentPosition;

        private PositionalListIterator() {
            this.currentPosition = (Node<E>) header.getNext();
        }
        @Override
        public boolean hasNext() {
            return this.currentPosition != tail;
        }

        @Override
        public E next() {
            if (hasNext()) {
                E result = this.currentPosition.getElement();
                this.currentPosition = this.currentPosition.getNext();
                return result;
            }
            else {
                throw new NoSuchElementException();
            }
        }

    }
    private int size;
    private Node<E> header;
    private Node<E> tail;

    public DLLPositionalList() {
        this.size= 0;
        this.header = new Node<E>();
        this.tail= new Node<E>();
        this.header.setNext(this.tail);
        this.tail.setPrev(this.header);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public Position<E> first() {
        if (this.isEmpty()) {
            return null;
        }
        else {
            return this.header.getNext();
        }
    }

    @Override
    public Position<E> last() {
        if (this.isEmpty()) {
            return null;
        }
        else {
            return this.tail.getPrev();
        }
    }

    @Override
    public Position<E> before(Position<E> p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        Node<E> temp = (Node<E>) p;
        if (temp.getPrev() == this.header) {
            return null;
        }
        else {
            return temp.getPrev();
        }
    }

    @Override
    public Position<E> after(Position<E> p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        Node<E> temp = (Node<E>) p;
        if (temp.getNext() == this.tail) {
            return null;
        }
        else {
            return temp.getNext();
        }
    }

    @Override
    public void addFirst(E e) {
        this.addBetween(this.header, this.header.getNext(), e);
    }

    @Override
    public void addLast(E e) {
        this.addBetween(this.tail.getPrev(), this.tail, e);
    }

    @Override
    public void addBefore(Position<E> p, E e) {
        Node<E> temp = (Node<E>) p;
        if (temp == this.header) {
            throw new IllegalArgumentException();
        }
        else {
            this.addBetween(temp.getPrev(), temp, e);
        }

    }

    @Override
    public void addAfter(Position<E> p, E e) {
        Node<E> temp = (Node<E>) p;
        if (temp == this.tail) {
            throw new IllegalArgumentException();
        }
        else {
            this.addBetween(temp, temp.getNext(), e);
        }

    }

    @Override
    public E set(Position<E> p, E e) {
        Node<E> temp = (Node<E>) p;
        if ((temp == this.header) ||
                (temp == this.tail)) {
            throw new IllegalArgumentException();
        }
        else {
            E result = temp.getElement();
            temp.setElement(e);
            return result;
        }
    }

    @Override
    public E remove(Position<E> p) {
        Node<E> temp = (Node<E>) p;
        if ((temp == this.header) ||
                (temp == this.tail)) {
            throw new IllegalArgumentException();
        }
        else {
            temp.getPrev().setNext(temp.getNext());
            temp.getNext().setPrev(temp.getPrev());
            E result = temp.getElement();
            temp.setElement(null);
            temp.setNext(null);
            temp.setPrev(null);
            this.size--;
            return result;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new PositionalListIterator<E>();
    }

    private void addBetween(Node<E> prev, Node<E> next, E e) {
        Node<E> temp = new Node();
        temp.setElement(e);
        temp.setNext(next);
        temp.setPrev(prev);
        prev.setNext(temp);
        next.setPrev(temp);
        this.size++;
    }
}
