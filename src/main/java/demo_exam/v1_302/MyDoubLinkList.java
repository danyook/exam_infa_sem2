package demo_exam.v1_302;

public class MyDoubLinkList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void add(T value) {

        Node<T> last = tail;
        Node<T> newNode = new Node<T>(last, value, null);
        tail = newNode;
        if (last == null)
            head = newNode;
        else
            last.setNext(newNode);
        size++;
    }

    public T get(int index) {
        Node<T> cur = head;
        for (int i = 1; i < index; i++) {
            cur = cur.getNext();
        }
        return cur.getValue();
    }

    public int getSize() {
        return size;
    }

    public void set(int index, T value) {
        delete(index);
        add(value);
    }

    public T delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size + " - за пределами");
        }

        Node<T> nodeToRemove;
        if (index == 0) {
            nodeToRemove = head;
            head = head.getNext();
            if (head != null) {
                head.setPrev(null);
            } else {
                tail = null;
            }
        } else if (index == size - 1) {
            nodeToRemove = tail;
            tail = tail.getPrev();
            tail.setNext(null);
        } else {
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            nodeToRemove = current;
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
        }

        size--;
        return nodeToRemove.getValue();
    }

    public String toString() {
        Node<T> current = head;
        String s = "";
        while (current != null) {
            s += current.getValue() + " || ";
            current = current.getNext();
        }
        return s;
    }
}