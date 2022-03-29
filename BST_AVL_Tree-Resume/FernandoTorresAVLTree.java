package com.company;
public class FernandoTorresAVLTree<E> extends FernandoTorresBinarySearchTree<E> {
    // create an empty AVL tree using a natural comparator
    public FernandoTorresAVLTree() { // super is implicitly called
    }
    // creat an AVL tree from an array of objects
    public FernandoTorresAVLTree(E[] objects) {
        super(objects);
    }
    // override creatNewNode to create an AVLTreeNode
    @Override
    protected FernandoTorresAVLTreeNode<E> createNewNode(E e) {
        return new FernandoTorresAVLTreeNode<E>(e);
    }
    // insert an element and rebalance if necessary
    @Override
    public boolean insert(E e) {
        boolean successful = super.insert(e);
        if (!successful) {
            // e is already in tree
            return false;
            // balance from e to the root if necessary
        } else {
            balancePath(e);
        }
        return true;
    }

    //update the height of the specified node
    private void updateHeight(FernandoTorresAVLTreeNode<E> node) {
        // node is a leaf
        if (node.left == null && node.right == null) {
            node.height = 0;
            // node has no left subtree
        } else if (node.left == null) {
            node.height = 1 + ((FernandoTorresAVLTreeNode<E>) (node.right)).height;
            // node has no right subtree
        } else if (node.right == null) {
            node.height = 1 + ((FernandoTorresAVLTreeNode<E>) (node.left)).height;
        } else {
            node.height = 1 +
                    Math.max( ((FernandoTorresAVLTreeNode<E>) (node.right)).height,
                              ((FernandoTorresAVLTreeNode<E>) (node.left)).height );
        }
    }

    // balance the nodes in the path form the specified node to the root if necessary
    private void balancePath(E e) {
        java.util.ArrayList<TreeNode<E>> path = path(e);
        for (int i = path.size() - 1; i >= 0; i--) {
            FernandoTorresAVLTreeNode<E> A = (FernandoTorresAVLTreeNode<E>) (path.get(i));
            updateHeight(A);
            FernandoTorresAVLTreeNode<E> parentOfA = (A == root) ? null : (FernandoTorresAVLTreeNode<E>)
                    (path.get(i - 1));

            switch (balanceFactor(A)) {
                case -2:
                    // perform LL rotation
                    if (balanceFactor((FernandoTorresAVLTreeNode<E>) A.left) <= 0) {
                        balanceLL(A, parentOfA);
                        // perform LR rotation
                    } else {
                        balanceLR(A, parentOfA);
                    }
                    break;
                case +2:
                    // perform RR rotation
                    if (balanceFactor((FernandoTorresAVLTreeNode<E>) A.right) >= 0) {
                        balanceRR(A, parentOfA);
                        // perform RL rotation
                    } else {
                        balanceRL(A, parentOfA);
                    }
            }
        }
    }

    // return the balance factor of the node
    private int balanceFactor(FernandoTorresAVLTreeNode<E> node) {
        // node has no right subtree
        if (node.right == null) {
            return -node.height;
            // node has no left subtree
        } else if (node.left == null) {
            return +node.height;
        } else {
            return ((FernandoTorresAVLTreeNode<E>) node.right).height
                    - ((FernandoTorresAVLTreeNode<E>) node.left).height;
        }
    }

    // balance LL
    private void balanceLL(TreeNode<E> A, TreeNode<E> parentOfA) {
        // A is left heavy and B is Left heavy
        TreeNode<E> B = A.left;

        if (A == root) {
            root = B;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = B;
            } else {
                parentOfA.right = B;
            }
        }

        // make T2 the left subtree of A
        A.left = B.right;
        // make A the left child of B
        B.right = A.left;
        updateHeight((FernandoTorresAVLTreeNode<E>) A);
        updateHeight((FernandoTorresAVLTreeNode<E>) B);

    }

    // balance LR
    private void balanceLR(TreeNode<E> A, TreeNode<E> parentOfA) {
        // A is left heavy and B is right heavy
        TreeNode<E> B = A.left;
        TreeNode<E> C = B.right;

        if (A == root) {
            root = C;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = C;
            } else {
                parentOfA.right = C;
            }
        }

        // make T3 the left subtree of A
        A.left = C.right;
        // make T2 the right subtree of B
        B.right = C.left;
        C.left = B;
        C.right = A;

        // adjust heights
        updateHeight((FernandoTorresAVLTreeNode<E>) A);
        updateHeight((FernandoTorresAVLTreeNode<E>) B);
        updateHeight((FernandoTorresAVLTreeNode<E>) C);
    }

    // balance RR
    private void balanceRR(TreeNode<E> A, TreeNode<E> parentOfA) {
        // A is right heavy and B is right heavy
        TreeNode<E> B = A.right;

        if (A == root) {
            root = B;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = B;
            } else {
                parentOfA.right = B;
            }
        }

        // make T2 the right subtree of A
        A.right = B.left;
        B.left = A;
        updateHeight((FernandoTorresAVLTreeNode<E>) A);
        updateHeight((FernandoTorresAVLTreeNode<E>) B);

    }

    // balance RL
    private void balanceRL(TreeNode<E> A, TreeNode<E> parentOfA) {
        // A is right heavy and B is left heavy
        TreeNode<E> B = A.right;
        TreeNode<E> C = B.left;

        if (A == root) {
            root = C;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = C;
            } else {
                parentOfA.right = C;
            }
        }

        // make T2 the right subtree of A
        A.right = C.left;
        // make T3 the left subtree of B
        B.left = C.right;
        C.left = A;
        C.right = B;

        // adjust heights
        updateHeight((FernandoTorresAVLTreeNode<E>) A);
        updateHeight((FernandoTorresAVLTreeNode<E>) B);
        updateHeight((FernandoTorresAVLTreeNode<E>) C);
    }

    // delete an element from the binary tree
    // return true if the element is deleted successfully
    // return false if the element is not in the tree
    @Override
    public boolean delete(E element) {
        if (root == null) {
            // element is not in tree
            return false;

        }
        // locate the node toi be deleted and also locate its parent
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (c.compare(element, current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (c.compare(element, current.element) > 0) {
                parent = current;
                current = current.right;
                // element is in the tree pointed by current
            } else {
                break;
            }
        }
        // element is not in tree
        if (current == null) {
            return false;
        }

        // case 1: current has no left children
        if (current.left == null) {
            // connect parent with the right child of the current
            if (parent == null) {
                root = current.right;
            } else {
                if (((Comparable)element).compareTo(parent.element) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
                //balance tree if necessary
                balancePath(parent.element);
            }
            // case 2: the current node has a left child
            // locate the right most node in the left subtree of the current node and also its parent
        } else {
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;
            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                // keep goign right
                rightMost = rightMost.right;
            }
            // replace the element in current by the element iin rightMost
            current.element = rightMost.element;
            // eliminate rightmost node
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
                //special case: parentofRightMost is current
            } else {
                parentOfRightMost.left = rightMost.left;
            }
            // balance the tree if necessary
            balancePath(parentOfRightMost.element);
        }



        // element is inserted ? does it mean deleted?
        size--;
        return true;
    }

    public static void FernandoTorresPrint(FernandoTorresAVLTree<Integer> tree) {
        System.out.println("Inorder Traversal:");
        tree.inorder();
        System.out.println("\nPre Order Traversal:");
        tree.preorder();
        System.out.println("\nPost Order Traversal:");
        tree.postorder();
        System.out.println("\nNumber of Nodes:" + tree.getSize());
    }

    // AVLTreeNode is TreeNode plus height
    protected static class FernandoTorresAVLTreeNode<E> extends FernandoTorresBinarySearchTree.TreeNode<E> {
        // new data field
        protected int height = 0;
        public FernandoTorresAVLTreeNode(E o) {
            super(o);
        }
    }
}
