package ru.ssau.tk.shnurok.lab2.functions.realizations;

import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.AbstractTabulatedFunction;
import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.Insertable;
import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.MathFunction;
import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.Removable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable, Serializable {

    @Serial
    private static final long serialVersionUID = -6963720079632410406L;

    protected static class Node {
        public Node next=null;
        public Node prev=null;
        public double x;
        public double y;

        public Node(double x, double y) {
            this.x = x;
            this.y = y;
        }

    }
    private Node head = null;
    protected int count;

    private void addNode(double x, double y) {
        Node newNode = new Node(x, y);
        if (head == null) {
            head = newNode;
            head.next = head;
            head.prev = head;
        } else {
            Node last = head.prev;
            last.next = newNode;
            newNode.prev = last;
            newNode.next = head;
            head.prev = newNode;
        }
        count++;
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2 || yValues.length < 2) {
            throw new IllegalArgumentException("Длина таблицы должна быть не менее 2");
        }
        for (int i = 0; i < xValues.length; ++i) {
            addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom > xTo) {
            double t = xFrom;
            xFrom = xTo;
            xTo = t;
        }
        if (xFrom == xTo) {
            for (int i = 0; i < count; i++) {
                addNode(xFrom, source.apply(xFrom));
            }
        }
        else {
            double step = (xTo - xFrom) / (count - 1);
            for (int i = 0; i < count; ++i) {
                double x = xFrom + step * i;
                double y = source.apply(x);
                addNode(x, y);
            }
        }

    }
    private Node getNode(int index) {
        if (index > count / 2) {
            Node current = head.prev;
            for (int i = 0; i < count - index - 1; i++) {
                current = current.prev;
            }
            return current;
        } else {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        }
    }


    @Override
    protected int floorIndexOfX(double x) {
        if (x < head.x) {
            throw new IllegalArgumentException("x меньше левой границы: " + x);
        }
        Node cur = head;
        int i = 0;
        do {
            if (cur.x >= x)
                return i == 0 ? 0 : i - 1;
            ++i;
            cur = cur.next;
        } while (cur != head);
        return count;

    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x,head.x,head.next.x,head.y,head.next.y);

    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);

    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        Node floorNode = getNode(floorIndex);
        return interpolate(x,floorNode.x,floorNode.next.x,floorNode.y,floorNode.next.y);

    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Индекс вне диапазона: " + index);
        }
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        getNode(index).y = value;
    }

    @Override
    public int indexOfX(double x) {
        Node current = head;
        for (int i = 0; i < count; i++) {
            if (current.x == x) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node current = head;
        for (int i = 0; i < count; i++) {
            if (current.y == y) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    @Override
    public void insert(double x, double y) {
        if (head == null) addNode(x,y);
        Node tmp = head;

        do{

            if(tmp.x == x) tmp.y = y;
            if (tmp.next.x > x && tmp.x <x) {
                Node node = new Node(x,y);
                Node next = tmp.next;

                tmp.next = node;
                node.prev = tmp;
                node.next = next;
                next.prev = node;


                count++;
                return;
            }
            tmp = tmp.next;
        } while (tmp.next!=head);

        if (x<head.x){
            Node node = new Node(x,y);

            Node tail = head.prev;

            tail.next = node;
            node.next = head;
            node.prev = tail;
            head.prev = node;
            head = node;

            count++;
            return;

        }
        if(tmp.next.x<x&&tmp.x<x){
            Node node = new Node(x,y);
            Node tail = head.prev;

            tail.next = node;
            node.next = head;
            node.prev = tail;
            head.prev = node;

            count++;
            return;
        }
    }

    @Override
    public void remove(int index) {
        if (count==0) return;

        Node del = getNode(index);

        if (count==1) head = null;
        else {
            if(del == head) head = head.next;
            Node prev = del.prev;
            Node next = del.next;
            next.prev = prev;
            prev.next = next;
        }
        count--;
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private Node node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Point next() {
                if (!hasNext())
                    throw new NoSuchElementException("");

                Point point = new Point(node.x, node.y);
                node = (node.next == head) ? null : node.next;
                return point;
            }
        };
    }
}