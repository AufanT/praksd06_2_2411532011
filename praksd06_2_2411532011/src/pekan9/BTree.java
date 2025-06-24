package pekan9;

public class BTree {
    private Node root;
    private Node currentNode;
    public BTree() {
        root = null;
    }

    public boolean search(int data) {
        return search(root, data);
    }

    private boolean search(Node node, int data) {
        if (node.getData() == data)
            return true;
        if (node.getLeft() != null)
            if (search(node.getLeft(), data))
                return true;
        if (node.getRight() != null)
            if (search(node.getRight(), data))
                return true;
        return false;
    }

    public void printInOrder() {
        root.printInOrder(root);
    }

    public void printPreOrder() {
        root.printPreOrder(root);
    }

    public void printPostOrder() {
        root.printPostOrder(root);
    }

    public Node getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(Node node) {
        int count = 1;
        if (node == null) {
            return 0;
        } else {
            count += countNodes(node.getLeft());
            count += countNodes(node.getRight());
            return count;
        }
    }

    public void print() {
        root.print();
    }

    public Node getCurrent() {
        return currentNode;
    }

    public void setCurrent(Node node) {
        this.currentNode = node;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}