package baisc.redission;


import java.util.concurrent.ConcurrentHashMap;

//感觉有点投机取巧，用了ConcurrentHashMap
public class MyLru {
    private int capacity;
    private ConcurrentHashMap<Integer, ListNode> hashmap;
    private ListNode head;
    private ListNode tail;

    public MyLru(int capacity) {
        this.capacity = capacity;
        hashmap = new ConcurrentHashMap<>();
        head = new ListNode();
        tail = new ListNode();
        head.next = tail;
        tail.prev = head;
    }



    class ListNode {
        int key;
        int val;
        ListNode prev;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }


    private void removeNode(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addNodeToLast(ListNode node) {
        node.prev = tail.prev;
        node.prev.next = node;
        node.next = tail;
        tail.prev = node;
    }

    private void moveNodeToLast(ListNode node) {
        removeNode(node);
        addNodeToLast(node);
    }

    public int get(int key) {
        if (hashmap.containsKey(key)) {
            ListNode node = hashmap.get(key);
            moveNodeToLast(node);
            return node.val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (hashmap.containsKey(key)) {
            ListNode node = hashmap.get(key);
            node.val = value;
            moveNodeToLast(node);
            return;
        }
        if (hashmap.size() == capacity) {
            hashmap.remove(head.next.key);
            removeNode(head.next);
        }

        ListNode node = new ListNode(key, value);
        hashmap.put(key, node);
        addNodeToLast(node);
    }

    public static void main(String[] args) {
        MyLru myLru = new MyLru(16);
        myLru.put(1,2);
        System.out.println(myLru.get(1));
    }
}
