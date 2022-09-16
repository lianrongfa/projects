package cn.lianrf.algorithm;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * LRU 算法
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/9/6 10:51 上午
 */
public class LRUCache {

    public static void main(String[] args) {
        myLRUTest();
        linkMapLRUTest();
    }

    private static void linkMapLRUTest(){
        LinkMapLRU mapLRU = new LinkMapLRU(3);
        mapLRU.put(1, 1);

        mapLRU.put(2, 2);
        mapLRU.put(3, 3);
        mapLRU.put(4, 4);

        mapLRU.get(2);
    }

    private static void myLRUTest() {
        MyLRUCache myLRUCache = new MyLRUCache(3);

        myLRUCache.put(1, 1);

        myLRUCache.put(2, 2);
        myLRUCache.put(3, 3);
        myLRUCache.put(4, 4);


        int i = myLRUCache.get(2);
    }

    static class LinkMapLRU {
        Map<Integer, Integer> map;
        int limit;

        public LinkMapLRU(int limit) {
            this.limit = limit;
            this.map = new LinkedHashMap<>();
        }

        public Integer get(Integer key) {
            Integer val = this.map.get(key);
            if (val == null) {
                return null;
            }
            makeR(key, val);
            printKey();
            return val;
        }

        private void makeR(Integer key, Integer val) {
            map.remove(key);
            map.put(key, val);
        }

        public void put(Integer key, Integer val) {
            Integer valI = this.map.get(key);
            if (valI == null) {
                map.put(key, val);
                int size = map.size();
                if (size > limit) {
                    Integer rmKey = map.keySet().iterator().next();
                    map.remove(rmKey);
                }
            } else {
                makeR(key, val);
            }

            printKey();
        }

        private void printKey() {
            Set<Integer> integers = map.keySet();
            List<String> collect = integers.stream().map(Objects::toString).collect(Collectors.toList());
            System.out.println(String.join(",", collect));
        }
    }


    static class Node {
        int key;
        int val;
        Node pre, next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    static class DoubleLink {
        Node head;
        Node tail;
        int size;

        void addLast(Node node) {
            if (node == null) {
                return;
            }
            Node t = this.tail;
            this.tail = node;

            if (t == null) {
                head = node;
            } else {
                t.next = node;
                node.pre = t;
            }
            size++;
        }

        void remove(Node node) {
            if (node == null) {
                return;
            }

            Node preN = node.pre;
            Node nextN = node.next;

            if (preN == null) {
                this.head = nextN;
            } else {
                preN.next = nextN;
                node.pre = null;
            }

            if (nextN == null) {
                tail = preN;
            } else {
                nextN.pre = preN;
                node.next = null;
            }
            size--;
        }

        Node removeFirst() {

            Node headN = this.head;

            remove(headN);

            return headN;
        }
    }

    static class MyLRUCache {
        DoubleLink doubleLink;
        Map<Integer, Node> map;
        int limit;

        public MyLRUCache(int limit) {
            this.doubleLink = new DoubleLink();
            this.map = new HashMap<>();
            this.limit = limit;
        }


        void put(int key, int val) {
            Node node = map.get(key);
            if (node == null) {
                node = new Node(key, val);
                map.put(key, node);
                doubleLink.addLast(node);
                int size = map.size();
                if (size > limit) {
                    Node tempNode = doubleLink.removeFirst();
                    map.remove(tempNode.key);
                }
            } else {
                node.val = val;
                map.put(key, node);
                makeR(node);
            }

            linkPrint();
        }

        private void linkPrint() {
            Node p = doubleLink.head;
            Node tail = doubleLink.head;

            do {
                System.out.print(p.val + ",");
                p = p.next;
            } while (p != null);
            System.out.println();
        }

        int get(int key) {
            Node node = map.get(key);
            if (node == null) {
                return -1;
            }
            makeR(node);
            linkPrint();
            return node.val;
        }

        private void makeR(Node node) {
            doubleLink.remove(node);
            doubleLink.addLast(node);
        }
    }
}
