package com.disrizka.gui;


import java.util.Iterator;
//interface
public class LinkedList<Item> implements Iterable<Item> {

    private Node first = new Node(); // first node in list

    @Override
    public Iterator<Item> iterator() {
        {
            return new ListIterator();
        }
    }
    //relasi aggregation
    class GUI {
        private LinkedList<Item> list;
        public GUI(LinkedList<Item> list) {
            this.list = list;
        }
    }


    //AbstractLinkedList
    public abstract class AbstractLinkedList<Item> implements Iterable<Item> {

        protected abstract void insertFirst(Item item);

        protected abstract void insertLast(Item item);

        @Override
        public abstract Iterator<Item> iterator();

        protected class Node {
            Item item;
            Node next;
        }
    }

    //turunan dari AbstractLinkedList
    public class SinglyLinkedList<Item> extends AbstractLinkedList<Item> {

        private Node first = new Node();

        @Override
        public Iterator<Item> iterator() {
            return new ListIterator();
        }

        @Override
        protected void insertFirst(Item item) {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
        }

        @Override
        protected void insertLast(Item item) {
            Node newNode = new Node();
            newNode.item = item;
            newNode.next = new Node();

            if (first == null) {
                first = newNode;
                return;
            }

            Node next = first.next;
            do {
                if (next.item == null) {
                    next.item = item;
                    next.next = new Node();
                    break;
                }
                next = next.next;
            } while (next != null);
        }

        private class ListIterator implements Iterator<Item> {
            private Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }

    }

    private class Node {
        Item item;
        Node next;
    }

    public void insertFirst(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public void insertLast(Item item) {
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = new Node();

        if (first == null) {
            first = newNode;
            return;
        }

        Node next = first.next;
        do {
            if (next.item == null) {
                next.item = item;
                next.next = new Node();
                break;
            }
            next = next.next;
        } while (next != null);
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
