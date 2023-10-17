package elvis.lrucache;

import java.util.HashMap;
import java.util.Map;

public class LRULinkedList<T, Q> {
    Map<T, Node<T, Q>> cache;

    public static class Node<T, Q> {
        Node<T, Q> pre;
        Node<T, Q> next;
        public Q value;
        public T key;

        public Node(T key, Q val) {
            this.key = key;
            this.value = val;
        }
    }

    public LRULinkedList(int cap) {
        this.cap = cap;
        cache = new HashMap<>();
        tail = head;
    }

    int cap;
    int size = 0;
    Node<T, Q> head;
    Node<T, Q> tail;

    public Q get(T key) {
        Node<T, Q> node = cache.get(key);
        if (node != null) {
            moveToHead(node);
            return node.value;
        }
        return null;
    }

    public void put(T key, Q value) {
        Node<T, Q> node = new Node<>(key, value);
        addNodeHead(node);
        cache.put(key, node);
        if (size >= cap) {
            cache.remove(deleteAtTail());
        } else {
            size++;
        }
    }

    private void moveToHead(Node<T, Q> node) {
        deleteNode(node);
        addNodeHead(node);
    }

    private void addNodeHead(Node<T, Q> node) {
        if (size == 0) {
            tail = node;
        } else if (head == tail) {
            node.next = tail;
            tail.pre = node;
        } else {
            node.next = head;
            head.pre = node;
        }
        head = node;
    }

    private void deleteNode(Node<T, Q> node) {
        if (node != tail) {
            node.next.pre = node.pre;
            node.pre.next = node.next;
            node.pre = null;
            node.next = null;
        } else {
            node.pre.next = null;
            node.pre = null;
        }
    }

    private T deleteAtTail() {
        T tailKey = tail.key;
        tail.pre.next = null;
        tail = tail.pre;
        return tailKey;
    }

    public static void main(String[] args) {
        LRULinkedList<String, String> l = new LRULinkedList<>(2);
        l.put("1", "1");
        l.put("2", "2");
        l.put("3", "3");
        System.out.println(l.get("1"));

        System.out.println(l.get("2"));
    }
}
