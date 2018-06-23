/***************************************************
*   Program Title: Assignment2                     *   
*   Author: Blake Bartenbach                       *
*   Class: CSCI3320,  Summer 2018                  *   
*   Assignment #2                                  *   
****************************************************/
package net.alureon.uno;

import java.util.Scanner;

public class Assignment2 {

    /**
     * The current binary search tree for the current interactive session of the program.
     */
    private static BinarySearchTree<Integer> tree;

    /**
     * The main method of the program.
     * @param args The arguments from the command line.
     */
    public static void main(String[] args) {
        while (true) {
            /* Get user input from the command line */
            showMenu();
            Scanner scn = new Scanner(System.in);
            String input = scn.next();
            int parsed;
            while (true) {
                try {
                    parsed = Integer.parseInt(input);
                    if (parsed < 1 || parsed > 8) {
                        throw new NumberFormatException();
                    } else {
                        break;
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid Option.  Please select a valid option from the menu.");
                    showMenu();
                    input = scn.next();
                }
            }

            /* Parse the validated input into a command */
            switch (parsed) {
                case 1:
                    constructNewTree();
                    break;
                case 2:
                    tree.printTreeDescending();
                    break;
                case 3:
                    System.out.println("The tree contains " + tree.numLeaves(tree.getRoot()) + " leaves.");
                    break;
                case 4:
                    System.out.println("The tree contains " + tree.numOneChildNodes(tree.getRoot()) + " single child nodes");
                    break;
                case 5:
                    System.out.println("The tree contains " + tree.numTwoChildrenNodes(tree.getRoot()) + " two children nodes");
                    break;
                case 6:
                    tree.levelOrder();
                    break;
                case 7:
                    System.out.print("Please enter the starting point: ");
                    int i = getInt();
                    System.out.print("Please enter the ending point: ");
                    int j = getInt();
                    tree.printBetween(i, j);
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.out.println("unsupported operation");
                    System.exit(1);
            }
        }
    }

    /**
     * Gets an integer value from the console.
     * @return the entered integer value.
     */
    private static int getInt() {
        Scanner scn = new Scanner(System.in);
        String input = scn.next();
        int parsed;
        while (true) {
            try {
                parsed = Integer.parseInt(input);
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input.  Please enter an integer value.");
                input = scn.next();
            }
        }
        return parsed;
    }

    /**
     * Constructs a new (global) binary search tree.
     */
    private static void constructNewTree() {
        tree = new BinarySearchTree<>();
        System.out.print("Enter the values to add to the tree: ");
        Scanner scn = new Scanner(System.in);
        String values = scn.nextLine();
        String[] inputValues = values.split("\\s");
        for (String x : inputValues) {
            try {
                tree.insert(Integer.parseInt(x));
            } catch (NumberFormatException ex) {
                System.out.println("Skipping invalid integer '" + x +"'");
            }
        }
    }

    /**
     * Provides the user with a menu of options to select from.
     */
    private static void showMenu() {
        System.out.println(">> Enter a choice [1-8] from the menu below:");
        System.out.println("   1) Construct a tree");
        System.out.println("   2) Print tree in descending order");
        System.out.println("   3) Print number of leaves in tree");
        System.out.println("   4) Print the number of nodes in T that contain only one child");
        System.out.println("   5) Print the number of nodes in T that contain only two children");
        System.out.println("   6) Print the level order traversal of the tree");
        System.out.println("   7) Print all elements in the tree between k1 and k2");
        System.out.println("   8) Exit program");
    }
}
