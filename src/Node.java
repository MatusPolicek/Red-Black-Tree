package RedBlackTree;

public class Node {
    private int data;
    private String key;
    private Node left, right, parent;
    private Color color;
    public Node(int data) {
        this.data = data;
        this.left = this.right = this.parent = null;
        this.color = Color.RED;
        this.key = String.valueOf(data);
    }
    public int getData() {
        return this.data;
    }
    public void setData(int data) {
        this.data = data;
    }
    public Node getLeftChild() {
        return this.left;
    }
    public void setLeftChild(Node left) {
        this.left = left;
    }
    public Node getRightChild() {
        return this.right;
    }
    public void setRightChild(Node right) {
        this.right = right;
    }
    public Node getParent() {
        return this.parent;
    }
    public void setParent(Node parent) {
        this.parent = parent;
    }
    public Color getColor() {
        return this.color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public boolean isBlack() {
        return this.color == Color.BLACK;
    }
    public boolean isRed() {
        return this.color == Color.RED;
    }
}