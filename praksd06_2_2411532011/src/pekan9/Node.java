package pekan9;

public class Node {
    int data;
    Node left;
    Node right;
    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
    public void setLeft(Node node) {
        if (left == null) {
            left = node;
        }
    }
    public void setRight(Node node) {
        if (right == null) {
            right = node;
        }
    }
    public Node getLeft() {
        return left;
    }
    public Node getRight() {
        return right;
    }
    public int getData() {
        return data;
    }
    public void setData(int data) {
        this.data = data;
    }

    void printPreOrder( Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getData() + " ");
        printPreOrder(node.getLeft());
        printPreOrder(node.getRight());
    }
    void printInOrder( Node node) {
        if (node == null) {
            return;
        }
        printInOrder(node.getLeft());
        System.out.print(node.getData() + " ");
        printInOrder(node.getRight());
    }
    void printPostOrder( Node node) {
        if (node == null) {
            return;
        }
        printPostOrder(node.getLeft());
        printPostOrder(node.getRight());
        System.out.print(node.getData() + " ");
    }

    public String print() {
        return this.print("", true, "");
    }
    private String print(String prefix, boolean isTail, String sb) {
        if (right != null) {
            right.print(prefix + (isTail ? "│   " : "    "), false, sb);
        }
        System.out.print(prefix + (isTail ? "\\-- " : "//--") + data );
        if (left != null) {
            left.print(prefix + (isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }
}
