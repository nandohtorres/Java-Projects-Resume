package com.company;

public class FernandoTorresBinarySearchTree<E> implements FernandoTorresTree<E> {
    protected TreeNode<E> root;
    protected int size = 0;
    protected java.util.Comparator<E> c;

    // create a default BST with a natural order comparator
    public FernandoTorresBinarySearchTree() {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
    }

    // create new BST with specified comparator
    public FernandoTorresBinarySearchTree(java.util.Comparator<E> c) {
        this.c = c;
    }

    // create BST from array of objects
    public FernandoTorresBinarySearchTree(E[] objects) {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
        for (int i = 0; i < objects.length; i++) {
            add(objects[i]);
        }
    }

    // return true if the element is in the tree
    @Override
    public boolean search(E e) {
        TreeNode<E> current = root;
        while (current != null) {
            if (c.compare(e, current.element) < 0) {
                current = current.left;
            } else if (c.compare(e, current.element) > 0) {
                current = current.right;
                // if the element matches
            } else {
                return true;
            }
        }
        // element is not in the tree
        return false;
    }

    // insert element e into the binary tree
    // returns true if the element is inserted successfully
    @Override
    public boolean insert(E e) {
        if (root == null) {
            root = createNewNode(e);
        } else {
            // locate parent
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (c.compare(e, current.element) < 0) {
                    parent = current; // keep parent
                    current = current.left; //  go left
                } else if (c.compare(e, current.element) > 0) {
                    parent = current;
                    current = current.right;
                    // if duplicate node inserted
                } else {
                    return false;
                }
            }
            // create the new node and attach it to the parent node
            if (c.compare(e, parent.element) < 0) {
                parent.left = createNewNode(e);
            } else {
                parent.right = createNewNode(e);
            }
        }
        size++;
        // element inserted successfully
        return true;
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }

    // inorder traversal from root
    @Override
    public void inorder() {
        inorder(root);
    }

    // inorder traversal from a subtree
    protected void inorder(TreeNode<E> root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    // postorder traversal from root
    @Override
    public void postorder() {
        postorder(root);
    }

    // postorder traversal from a subtree
    protected void postorder(TreeNode<E> root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    // preorder traversal form a subtree
    @Override
    public void preorder() {
        preorder(root);
    }

    // preorder traversal from a subtree
    protected void preorder(TreeNode<E> root) {
        if (root == null) return;
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    // this inner class is static, because it does not access any instance members defined in its outer class
    public static class TreeNode<E> {
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }

    // get the number of nodes int he tree
    @Override
    public int getSize() {
        return size;
    }

    // returns the root of the tree
    public TreeNode<E> getRoot() {
        return root;
    }

    // returns a path form the root leading to the specified element
    public java.util.ArrayList<TreeNode<E>> path(E e) {
        java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<>();
        // start from root
        TreeNode<E> current = root;

        while (current != null) {
            list.add(current);
            if (c.compare(e, current.element) < 0) {
                current = current.left;
            } else if (c.compare(e, current.element) > 0) {
                current = current.right;
            } else {
                break;
            }

        }
        //returns array list of nodes
        return list;
    }

    // delete an element from binary tree
    // returns true if the element is deleted successfully
    // returns false if the element is not in the tree
    @Override
    public boolean delete(E e) {
        // locate node to be deleted and also locate parent
        TreeNode<E> parent = null;
        TreeNode<E> current = root;

        while (current != null) {
            if (c.compare(e, current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (c.compare(e, current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                // element is in tree pointed at by current
                break;
            }
        }

        // element is not in the tree
        if (current == null) {
            return false;
        }
        // case 1: current has no left child
        if (current.left == null) {
            // connect the parent with the right child of the current
            if (parent == null) {
                root = current.right;
            } else {
                if (c.compare(e, parent.element) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            }
            // case 2: the current node has a left child
            // locate the rightmost node in the left subtree of the current node and its parent also
        } else {
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                // keep goign right
                rightMost = rightMost.right;
            }

            // replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // eliminate rightmost node
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else {
                // special case: parentOfRightMost == current
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        // element successfully deleted
        return true;
    }

    // obtain an iterator, use inorder
    @Override
    public java.util.Iterator<E> iterator() {
        return new InorderIterator();
    }

    // inner class InorderIterator
    private class InorderIterator implements java.util.Iterator<E> {

        // store the elements in a list
        private java.util.ArrayList<E> list = new java.util.ArrayList<>();
        // point to the current element in list
        private int current = 0;

        public InorderIterator() {
            // traverse binary tree and store elements in list
            inorder();
        }

        // inorder traversal from the root
        private void inorder() {
            inorder(root);
        }

        // inorder traversal from a subtree
        private void inorder(TreeNode<E> root) {
            if (root == null) return;
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        // more elements for traversing
        @Override
        public boolean hasNext() {
            if (current < list.size()) {
                return true;
            }
            return false;
        }

        // get the current element and move to the next
        @Override
        public E next() {
            return list.get(current++);
        }

        // remove the element returned by the last next()
        @Override
        public void remove() {
            // next() has not been called yet
            if (current == 0) {
                throw new IllegalStateException();
            }
            delete(list.get(--current));
            //clear list
            list.clear();
            // rebuild list
            inorder();
        }
    }

    // remove all elements from the tree
    @Override
    public void clear() {
        root = null;
        size = 0;
    }
    // print method
    public static void FernandoTorresPrint(FernandoTorresBinarySearchTree<String> tree) {

        System.out.println("Inorder Traversal:");
        tree.inorder();
        System.out.println("\nPre Order Traversal:");
        tree.preorder();
        System.out.println("\nPost Order Traversal:");
        tree.postorder();
        System.out.println("\nNumber of Nodes:" + tree.getSize());

    }

}
