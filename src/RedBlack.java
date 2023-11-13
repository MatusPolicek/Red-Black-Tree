package RedBlackTree;

public class RedBlack {
    private Node root;
    public RedBlack() {
        this.root = null;
    }
    public Node getRoot() {
        return this.root;
    }
    private void transplant(Node x, Node y) { //Method for transplant 2 nodes
        Node parent = x.getParent(); //Getting x's parent
        if (parent == null) //If parent is null that's mean x was root
            this.root = y; //We assign y as new root
        else if (x == parent.getLeftChild()) //Else if it's not root and x is parent's left child
            parent.setLeftChild(y); //We assign y as x's parent left child
        else //Else x is parent's right child
            parent.setRightChild(y); //We assign y as x's parent right child
        if (y != null) //If y is not null than we set y's parent to s'x parent
            y.setParent(parent);
        //With transplanting, we changed x to y and x is not connected to the tree anymore
    }
    private Node minimum(Node right) {
        //Getting minimal node from right child (minimum is right's left children)
        //If right's left child is null we return right else we recursively return minim(his left child)
        return (right.getLeftChild() == null) ? right : minimum(right.getLeftChild());
    }
    private void leftRotation(Node x) {
        //Left rotation
        Node y = x.getRightChild(); //Assigning y as x's right child
        x.setRightChild(y.getLeftChild()); //Assigning x's right child as y's left child
        if (x.getRightChild() != null) //If x's new right child is not null
            x.getRightChild().setParent(x); //Set x's new right child's parent as x
        y.setParent(x.getParent()); //Set y's parent as x's parent
        if (x.getParent() == null) //If x's parent is null
            this.root = y; //x was root and y became as root
        else if (x == x.getParent().getLeftChild()) { //Else if x is x's parent's left child
            x.getParent().setLeftChild(y); //Set x's parent's left child to y
        } else {
            x.getParent().setRightChild(y); //Else set x's parent's right child to y
        }
        y.setLeftChild(x); //Set y's left child to x
        x.setParent(y); //Set x's parent to y
    }
    private void rightRotation(Node x) {
        //Right rotation
        Node y = x.getLeftChild(); //Assigning y as x's left child
        x.setLeftChild(y.getRightChild()); //Assigning x's left child as y's right child
        if (x.getLeftChild() != null) //If x's new left child is not null
            x.getLeftChild().setParent(x); //Set x's new left child's parent as x
        y.setParent(x.getParent()); //Set y's parent as x's parent
        if (x.getParent() == null) //If x's parent is null
            this.root = y; //x was root and y became as root
        else if (x == x.getParent().getLeftChild()) { //Else if x is x's parent's left child
            x.getParent().setLeftChild(y); //Set x's parent's left child to y
        } else {
            x.getParent().setRightChild(y); //Else set x's parent's right child to y
        }
        y.setRightChild(x); //Set y's right child to x
        x.setParent(y); //Set x's parent to y
    }
    //Insertion methods for RedBlack Tree
    public void insert(int data) {
        Node x = new Node(data);
        this.root = insert(this.root, x);
        insert(x);
    }
    private Node insert(Node node, Node x) {
        //Inserting new node (x)
        if (node == null) //If node is null we assign node as x
            node = x;
        else if (x.getData() > node.getData()) { //Else if new data are bigger than node's data
            node.setRightChild(insert(node.getRightChild(), x)); //Recursively we call insert at node's right child
            node.getRightChild().setParent(node); //Assigning right child's parent as node
        } else if (x.getData() < node.getData()) { //Else if new data are smaller than node's data
            node.setLeftChild(insert(node.getLeftChild(), x)); //Recursively we call insert at node's left child
            node.getLeftChild().setParent(node); //Assigning left child's parent as node
        }
        //If data already exists in tree we return untouched node
        return node;
    }
    private void insert(Node x) {
        //Insert fix method
        Node parent, grandParent, uncle; //References for x's parent, grandParent and uncle
        int subtree; //Reference for subtree
        while (x.getParent() != null && x.isRed() && x.getParent().isRed()) { //While x's parent is not null and x is red and x's parent is red (double red ! not allowed)
            parent = x.getParent(); //parent is x's parent
            grandParent = parent.getParent(); //grandParent is x's parent's parent
            if (parent == grandParent.getLeftChild()) //If x's parent is left child of grandParent
                subtree = 1; //we are in left subtree (1 as left)
            else
                subtree = 0; //else we are in right subtree (0 as right)
            uncle = (subtree == 1) ? grandParent.getRightChild() : grandParent.getLeftChild(); //x's uncle is grandparent's left or right child, depends on subtree
            if (uncle != null && uncle.isRed()) { //if uncle is not null and uncle is red
                parent.setColor(Color.BLACK); //Color parent black
                uncle.setColor(Color.BLACK); //Color uncle black
                grandParent.setColor(Color.RED); //Color grandParent red
                x = grandParent; //x now is grandParent of x's
            } else { //Else if uncle is nil (black) or black node
                if (subtree == 1) { //If we are in left subtree
                    if (x == parent.getRightChild()) { //If x is parent's right child (left-right rotation)
                        leftRotation(parent); //Perform left rotation parent
                        x = parent; //x is now his parent
                        parent = x.getParent(); //parent is x's parent after rotation
                    }
                    //Right rotation
                    rightRotation(grandParent); //right rotation of grandParent
                    parent = x.getParent();
                    grandParent = x.getParent().getParent();
                    parent.setColor((parent.isBlack() ? Color.RED : Color.BLACK)); //Swap parent's color
                    parent.getRightChild().setColor((parent.getRightChild().isBlack()) ? Color.RED : Color.BLACK); //Swap parent's right child color
                } else { //Else if we are right subtree
                    if (x == parent.getLeftChild()) { //If x is parent's left child (right-left rotation)
                        rightRotation(parent); //Perform right rotation parent
                        x = parent; //x is now his parent
                        parent = x.getParent(); //parent is x's parent after rotation
                    }
                    leftRotation(grandParent); //Perform left rotation of grandParent
                    parent = x.getParent();
                    grandParent = x.getParent().getParent();
                    parent.setColor((parent.isBlack() ? Color.RED : Color.BLACK)); //Swap parent's color
                    parent.getLeftChild().setColor((parent.getLeftChild().isBlack()) ? Color.RED : Color.BLACK); //Swap parent's left child color
                }
                x = parent; //x became his parent
            }
        }
        this.root.setColor(Color.BLACK); //Root is always black
    }
    //Methods for deletion for RedBlack Tree
    public void delete(int data) {
        delete(this.root, data);
    }
    private void delete(Node root, int data) { //Searching for node to be deletion
        Node node = findNode(root, data);
        if (node == null)
            return;

        //If we found node to be deleted we mark his color to reference originalColor
        Color originalColor = node.getColor();
        Node x = node, parent = null, y = null, min; //References for parent, minimal of right subtree and y
        //Set node to be deleted to x reference
        if (x.getLeftChild() == null) { //If x's left child is null
            parent = x.getParent(); //Parent is x's parent
            y = x.getRightChild(); //y is x's right child
            transplant(x, x.getRightChild()); //Transplant x and his right child
        } else if (x.getRightChild() == null) { //Else if x's right child is null
            parent = x.getParent(); //Parent is x's parent
            y = x.getLeftChild(); //y is x's left child
            transplant(x, x.getLeftChild()); //Transplant x and his left child
        } else { //Else if x has 2 children
            min = minimum(x.getRightChild()); //Finding x's minimum of right subtree (I implemented minimum)
            originalColor = min.getColor(); //Marking minimum's color to reference
            parent = min.getParent(); //Parent is minimum's parent
            y = min.getRightChild(); //y is minimum's right child
            if (min.getParent() != x) { //If minimum isn't child of node to be deleted
                transplant(min, min.getRightChild()); //Transplant min and his right child
                min.setRightChild(x.getRightChild()); //Set minimum's right child as x's right child
                if (min.getRightChild() != null) //If new right child isn't null
                    min.getRightChild().setParent(min); //We set minimum's right child's parent as minimum
            } else { //Else if minimum is node's to be deleted child
                parent = min; //parent is minimum
                y = min.getRightChild(); //y is minimum's right child
            }
            transplant(x, min); //Transplanting node to be deleted and his minimum
            min.setLeftChild(x.getLeftChild()); //Set minimum's left child as x's left child
            min.getLeftChild().setParent(min); //Set minimum's left child's parent as minimum
            min.setColor(x.getColor()); //Set minimum's color as node's to be deleted color
            //We changed node to deleted to his minimum of right subtree
        }
        if (originalColor == Color.BLACK) //If node's color is black we call fix up after deletion
            delete(y, parent); //Passing y and his parent
    }
    //Deletion fix up method after deletion
    private void delete(Node node, Node parent) {
        Node x = node, sibling; //Reference of node and his sibling
        int subtree; //Reference of subtree
        while (x != this.root && (x == null || x.isBlack())) { //While x isn't root and x's color is black
            if (x == parent.getLeftChild()) { //If x is parent's left child
                sibling = parent.getRightChild(); //sibling is parent's right child
                subtree = 1; //Set reference to 1
            } else {
                sibling = parent.getLeftChild(); //sibling is parent's left child
                subtree = 0; //Set reference to 0
            }
            if (sibling != null && sibling.isRed()) { //If sibling isn't null and his color is red
                sibling.setColor(Color.BLACK); //Set sibling's color to black
                parent.setColor(Color.RED); //Set parent's color to red
                if (subtree == 1) //If sibling is right child
                    leftRotation(parent); //Left rotation of parent
                else //Else if sibling is left child
                    rightRotation(parent); //Right rotation of parent
                sibling = (subtree == 1) ? parent.getRightChild() : parent.getLeftChild(); //Assigning new sibling after rotation
            }
            if (sibling == null) { //If sibling is null than sibling is black
                x = parent; //Moving up, x is his parent
                parent = x.getParent(); //parent is parent's parent
                if (parent != null) //if parent isn't null
                    sibling = (x == parent.getLeftChild()) ? parent.getRightChild() : parent.getLeftChild(); //Assigning new sibling after move up
            } else if ((sibling.getLeftChild() == null || sibling.getLeftChild().isBlack()) && (sibling.getRightChild() == null || sibling.getRightChild().isBlack())) {
                //Else if siblings children are black
                sibling.setColor(Color.RED); //Set sibling's color to Red
                x = parent; //Moving up, x is his parent
                parent = x.getParent(); //parent is parent's parent
            } else {
                //Else
                if (subtree == 1 && (sibling.getRightChild() == null || sibling.getRightChild().isBlack())) { //If sibling is right child and sibling's right child is black or nil
                    sibling.setColor(Color.RED); //Set sibling's color to red
                    if (sibling.getLeftChild() != null) //If sibling's left child isn't null
                        sibling.getLeftChild().setColor(Color.BLACK); //Set sibling's left child's color to black
                    rightRotation(sibling); //Right rotation of sibling
                    sibling = parent.getRightChild(); //Assign new sibling after rotation
                } else if (subtree == 0 && (sibling.getLeftChild() == null || sibling.getLeftChild().isBlack())) { //Else if sibling is left child and sibling's left child is black or nil
                    sibling.setColor(Color.RED); //Set sibling's color to red
                    if (sibling.getRightChild() != null) //If sibling's right child isn't null
                        sibling.getRightChild().setColor(Color.BLACK); //Set sibling's right child's color to black
                    leftRotation(sibling); //Left rotation of sibling
                    sibling = parent.getLeftChild(); //Assign new sibling after rotation
                }
                sibling.setColor(parent.getColor()); //Set sibling's color to parent's color
                parent.setColor(Color.BLACK); //Set parent's color to black
                if (subtree == 1) { //If sibling is right child
                    if (sibling.getRightChild() != null) //if sibling's right child isn't null
                        sibling.getRightChild().setColor(Color.BLACK); //Set sibling's right child's color to black
                    leftRotation(parent); //Left rotation of parent
                } else { //Else if sibling is left child
                    if (sibling.getLeftChild() != null) //If sibling's left child isn't null
                        sibling.getLeftChild().setColor(Color.BLACK); //Set sibling's left child's color to black
                    rightRotation(parent); //Right rotation of parent
                }
                x = this.root; //x is root and we end while loop
            }
        }
        if (x != null)
            x.setColor(Color.BLACK); //Set x's (root's) color to black
    }
    //Methods for searching Red Black Tree
    public boolean search(int data) {
        return search(this.root, data);
    }
    private boolean search(Node node, int data) {
        if (node == null)
            return false;
        if (data < node.getData())
            return search(node.getLeftChild(), data);
        else if (data > node.getData())
            return search(node.getRightChild(), data);
        else
            return data == node.getData();
    }
    private Node findNode(Node node, int data) {
        if (node == null)
            return null;
        if (data < node.getData())
            return findNode(node.getLeftChild(), data);
        else if (data > node.getData())
            return findNode(node.getRightChild(), data);
        else
        if (data == node.getData())
            return node;
        else
            return null;
    }
}