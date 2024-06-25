package demo_exam.v1_302;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Node<T> {
    private Node<T> prev;
    private T value;
    private Node<T> next;


}


