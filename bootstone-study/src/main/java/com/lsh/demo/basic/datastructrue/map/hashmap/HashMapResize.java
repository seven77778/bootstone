package com.lsh.demo.basic.datastructrue.map.hashmap;

import org.junit.Test;

public class HashMapResize {

    //模拟hashmap的resize方法，设计真的巧妙
    @Test
    public void test() {
        HashMapResize.Node loHead = null, loTail = null;
        HashMapResize.Node hiHead = null, hiTail = null;
        HashMapResize.Node next;
        HashMapResize.Node[] newTab = new HashMapResize.Node[16];
        Node e = new Node("1",new Node("2",new Node("3",new Node("4",null))));
        do {
            next = e.next;
            if (e.value.equals("1") || e.value.equals("4")) {
                if (loTail == null)
                    loHead = e;
                else
                    loTail.next = e;
                loTail = e;
            } else {
                if (hiTail == null)
                    hiHead = e;
                else
                    hiTail.next = e;
                hiTail = e;
            }
        } while ((e = next) != null);
        if (loTail != null) {
            loTail.next = null;
            newTab[1] = loHead;
        }
        if (hiTail != null) {
            hiTail.next = null;
            newTab[2] = hiHead;
        }

        System.out.println(newTab);

    }

    static class Node {
        int hash;
        String key;
        String value;
        Node next;

        Node(String value, Node next) {
            this.value=value;
            this.next = next;
        }

        Node(int hash, String key, String value, Node next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
