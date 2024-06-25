package demo_exam.v1_305.n1;


public class MyLinkedList<T> {
    public int size;
    private Node<T> root;
    private Node<T> current;

    public MyLinkedList() {
    }

    public T get(int index) {
        Node<T> current1 = root;
        for (int i = 0; i < index; i++) {
            current1 = current1.getNext();
        }
        return current1.getValue();
    }

    public void add(T elem) {
        if (root == null) {
            root = new Node<>(elem);
            current = root;
            size++;
        } else {
            Node<T> node = new Node<>(elem);
            current.setNext(node);
            current = current.getNext();
            size++;
        }
    }

    public void insert(int index, T value) {
        Node<T> current1 = root;
        if (index == 0) {
            Node<T> newNode = new Node<>(value);
            newNode.setNext(root);
            root.setNext(root.getNext());
            root = newNode;
        } else if (index <= size) {
            for (int i = 0; i < index - 1; i++) {
                current1 = current1.getNext();
            }
            Node<T> newNode = new Node<>(value);
            newNode.setNext(current1.getNext());
            current1.setNext(newNode);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void delete(int index) {
        Node<T> current1 = root;
        if (index == 0) {
            root = root.getNext();
            size--;
        } else if (index <= size) {
            for (int i = 0; i < index - 1; i++) {
                current1 = current1.getNext();
            }
            current1.setNext(current1.getNext().getNext());
            size--;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void printed() {
        Node<T> current1 = root;
        while (current1 != null) {
            System.out.println(current1.getValue());
            current1 = current1.getNext();
        }
    }
}
