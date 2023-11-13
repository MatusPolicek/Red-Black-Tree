# Red-Black Tree Implementation

This Java code provides a concise implementation of a Red-Black tree, a self-balancing binary search tree known for its efficiency in search, insertion, and deletion operations.

## Overview

The `RedBlack` class represents the Red-Black tree, a type of binary search tree that maintains balance through color-coding and rotation operations. This implementation includes methods for insertion, deletion, and searching.

## Usage

### 1. Create a Red-Black Tree Object:
```java
RedBlack redBlackTree = new RedBlack();
```
### 2. Insertion:
```java
redBlackTree.insert(data);
```
This method inserts a new node with the specified data into the Red-Black tree while maintaining its properties.
### 3. Deletion:

```java
redBlackTree.delete(data);
```

Delete a node with the specified data from the Red-Black tree while preserving its properties.
### 4. Search:
```java
boolean found = redBlackTree.search(data);
```
Search for a node with the specified data in the Red-Black tree. Returns true if found, false otherwise.
Red-Black Tree Methods

    insert(int data): Inserts a new node with the given data into the Red-Black tree.
    delete(int data): Deletes a node with the specified data from the Red-Black tree.
    search(int data): Searches for a node with the specified data in the Red-Black tree.
    getRoot(): Returns the root node of the Red-Black tree.

### Internal Methods

    transplant(Node x, Node y): Transplants node y in place of node x in the Red-Black tree.
    minimum(Node right): Finds the minimum node in the subtree rooted at the specified node right.
    leftRotation(Node x): Performs a left rotation on the specified node x in the Red-Black tree.
    rightRotation(Node x): Performs a right rotation on the specified node x in the Red-Black tree.
    insert(Node x): Fixes the Red-Black tree properties after insertion of a new node.
    delete(Node node, int data): Searches for and deletes a node with the specified data from the Red-Black tree.
    delete(Node node, Node parent): Fixes the Red-Black tree properties after deletion of a node.
    search(Node node, int data): Searches for a node with the specified data in the Red-Black tree.
    findNode(Node node, int data): Finds and returns the node with the specified data in the Red-Black tree.