package pekan6.TugasPekan6;

public class Node {
    itemBelanja item;
    Node next;
    Node prev;

    public Node(itemBelanja item) {
        this.item = item;
        this.next = null;
        this.prev = null;
    }
}
