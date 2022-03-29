package com.company;
public class Main {

    public static void main(String[] args) {
        testBST();
        testAVLTree();
    }

    public static void testBST() {
        FernandoTorresBinarySearchTree<String> tree = new FernandoTorresBinarySearchTree<>();
        tree.insert("George");
        tree.insert("Michael");
        tree.insert("Tom");
        tree.insert("Adam");
        tree.insert("Jones");
        tree.insert("Peter");
        tree.insert("Daniel");
        // this will call/print the Inorder, PostOrder and PreOrder Traversal, as well as print out number of Nodes
        System.out.println("I am calling the printTree method which will call the Inorder, Preorder, Postorder " +
                "and print them out along with the number of nodes.");
        tree.FernandoTorresPrint(tree);
        System.out.println();

        // search for an element
        System.out.println("\nSearch Test");
        System.out.println("Is Peter in the tree?");
        if (!tree.search("Peter")) {
            System.out.println("Peter is not in the tree. Here is the tree:");
        } else {
            System.out.println("Peter is in the tree. Here is the tree:");
            tree.inorder();
        }
        System.out.println();

        // delete peter
        System.out.println("\nDelete Test");
        tree.delete("Peter");
        System.out.println("Is peter deleted now?");
        if (!tree.delete("Peter")) {
            System.out.println("Peter is deleted. Here is the tree:");
        }
        tree.FernandoTorresPrint(tree);
        System.out.println();

        // insert peter back in
        System.out.println("\nWe will now insert Peter back into the tree.");
        tree.insert("Peter");

        // search for an element
        System.out.println("Insert Test");
        System.out.println("Was Peter successfully inserted into the tree?");
        if (!tree.search("Peter")) {
            System.out.println("Peter was NOT inserted into the tree.");
        } else {
            System.out.println("Peter was inserted into the tree.");
        }
        tree.FernandoTorresPrint(tree);
        System.out.println();

        // get a path from the root to Peter
        System.out.println("\nA path from the root to peter is: ");
        java.util.ArrayList<FernandoTorresBinarySearchTree.TreeNode<String>> path = tree.path("Peter");
        for (int i = 0; path != null && i < path.size(); i++) {
            System.out.print(path.get(i).element + " ");
        }
        System.out.println();
        System.out.print("\nHere is the Inorder, PostOrder and PreOrder sortings for a tree of type int with " +
                "{2, 4, 3, 1, 8, 5, 6, 7} being inserted in that order. ");
        Integer[] numbers = {2, 4, 3, 1, 8, 5, 6, 7};
        FernandoTorresBinarySearchTree<Integer> intTree = new FernandoTorresBinarySearchTree<>(numbers);
        System.out.println("\nInorder: ");
        intTree.inorder();
        System.out.println("\nPost Order: ");
        intTree.postorder();
        System.out.println("\nPreorder: ");
        intTree.preorder();
        System.out.println("\nTesting of the BST class is now complete.");
    }

    public static void testAVLTree() {
        // AVL tree testing class
        System.out.println("\n\nNow we will begin testing the AVLTree class.");
        Integer[] numbers = {5, 20, 25};
        FernandoTorresAVLTree<Integer> tree = new FernandoTorresAVLTree<>(numbers);
        System.out.println("After inserting 5, 20, 25:");

        tree.FernandoTorresPrint(tree);

        tree.insert(34);
        tree.insert(50);
        System.out.println("\nAfter inserting 34, 50");

        tree.FernandoTorresPrint(tree);

        tree.insert(30);
        System.out.println("\nAfter inserting 30:");
        tree.FernandoTorresPrint(tree);

        tree.insert(10);
        System.out.println("\nAfter inserting 10?:");
        tree.FernandoTorresPrint(tree);

        tree.delete(34);
        tree.delete(30);
        tree.delete(50);
        System.out.println("\nAfter removing 34, 30, 50:");
        tree.FernandoTorresPrint(tree);

        tree.delete(5);
        System.out.println("\nAfter removing 5:");
        tree.FernandoTorresPrint(tree);

        System.out.println("Traverse the elements in the tree: ");
        for (int e : tree) {
            System.out.print(e + " ");
        }

        System.out.println("\nThe AVLTree testing is now complete.");
    }


}
