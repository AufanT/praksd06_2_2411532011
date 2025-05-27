package pekan6;

public class PenelusuranDLL {
    static void ForwardTraversal(NodeDLL head) {
        NodeDLL curr = head;

        while (curr != null) {
            System.out.print(curr.data + " <-> ");
            curr = curr.next;
        }
        System.out.println();
    }
    static void backwardTraversal (NodeDLL tail) {
        NodeDLL curr = tail;
        while (curr != null) {
            System.out.print(curr.data + " <-> ");
            curr = curr.prev;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        NodeDLL head = new NodeDLL(1);
        NodeDLL second = new NodeDLL(2);
        NodeDLL third = new NodeDLL(3);

        head.next = second;
        second.prev = head;
        second.next = third;
        third.prev = second;

        System.out.println("Penelusuran maju:");
        ForwardTraversal(head);

        System.out.println("Penelusuran mundur:");
        backwardTraversal(third);
    }
}
