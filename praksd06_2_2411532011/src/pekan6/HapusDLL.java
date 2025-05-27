package pekan6;

public class HapusDLL {
    public static NodeDLL delHead (NodeDLL head) {
        if (head == null) {
            return null;
        }
        NodeDLL temp = head;
        head = head.next;
        if (head != null) {
            head.prev = null;
        }
        return head;
    }

    public static NodeDLL delLast (NodeDLL head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return null;
        }
        NodeDLL curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }

        if (curr.prev != null) {
            curr.prev.next = null;
        }
        return head;
    }
}
