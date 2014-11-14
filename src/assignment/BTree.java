package assignment;

import javax.sound.midi.Soundbank;

/**
 * Created by Koo Lee on 11/14/2014.
 */
public class BTree {

    private static final int order = 4;

    private Node root;
    private int height;
    private int size;

    public BTree() {
        this.root = new Node(0);
    }

    public void put(int key) {
        Node newNode = insert(root, key, height);
        size++;
        if (newNode == null) return;

        Node splitNode = new Node(2);
        splitNode.items[0] = new Entry(root.items[0].key, root);
        splitNode.items[1] = new Entry(newNode.items[0].key, splitNode);
        root = splitNode;
        height++;
    }

    public String status() {
        return status(root, height, "") + "\n";
    }

    public String status(Node node, int height, String indent) {
        if (node == null)
            return indent;

        String s = "";
        Entry[] items = node.items;

        if (height == 0) {
            for (int i = 0; i < node.size; i++) {
                s += indent + items[i].key + " " + "\n";
            }
        } else {
            for (int i = 0; i < node.size; i++) {
                if (i > 0) {
                    s += indent + "(" + items[i].key + ")\n";
                }
                s += status(items[i].next, height - 1, indent + "     ");
            }
        }
        return s;
    }

    private Node insert(Node node, int key, int ht) {
        int j;
        Entry newEntry = new Entry(key, null);

        if (ht == 0) {
            // find the position of new key.
            for (j = 0; j < node.size; j++) {
                if (key < node.items[j].key) break;
            }
        } else {
            for (j = 0; j < node.size; j++) {
                if ((j + 1 == node.size) || key < node.items[j + 1].key) {
                    Node inserted = insert(node.items[j++].next, key, ht - 1);
                    if (inserted == null) return null;
                    newEntry.key = inserted.items[0].key;
                    newEntry.next = inserted;
                    break;
                }
            }
        }

        for (int i = node.size; i > j; i--) node.items[i] = node.items[i - 1];
        node.items[j] = newEntry;
        node.size++;
        if (node.size < order) return null;
        else {
            Node splitNode = new Node(order / 2);
            splitNode.size = order / 2;
            for (int k = 0; k < order / 2; k++) {
                splitNode.items[k] = node.items[order / 2 + k];
            }
            return splitNode;
        }
    }

    public class Node {
        private int size;
        private Entry[] items = new Entry[order];

        public Node(int size) {
            this.size = size;
        }
    }

    public class Entry {
        private int key;
        private Node next;

        public Entry(int key, Node next) {
            this.key = key;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        BTree bTree = new BTree();
        bTree.put(1);
        bTree.put(12);
        bTree.put(8);
        bTree.put(2);
        bTree.put(25);
//        bTree.put(6);
//        bTree.put(14);
//        bTree.put(28);
        System.out.println(bTree.status());
    }
}
