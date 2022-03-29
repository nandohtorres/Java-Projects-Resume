package com.company;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // sets array size equal to the getsize resultant
        int FernandoTorresArraySize = FernandoTorresGetSize();
        System.out.println("The size of the array is: " + FernandoTorresArraySize);
        // creates original array
        int[] FernandoTorresArray = FernandoTorresGetArray(FernandoTorresArraySize);
        // copies of arrays to be sorted
        int[] FernandoTorresArrayI = FernandoTorresCopyArray(FernandoTorresArray);
        int[] FernandoTorresArrayB = FernandoTorresCopyArray(FernandoTorresArray);
        int[] FernandoTorresArrayM = FernandoTorresCopyArray(FernandoTorresArray);
        int[] FernandoTorresArrayQ = FernandoTorresCopyArray(FernandoTorresArray);
        int[] FernandoTorresArrayH = FernandoTorresCopyArray(FernandoTorresArray);
        // call functions to sort the arrays according to different sorts
        FernandoTorresInsertionSort(FernandoTorresArrayI);
        FernandoTorresBubbleSort(FernandoTorresArrayB);
        FernandoTorresMergeSort(FernandoTorresArrayM);
        FernandoTorresQuickSort(FernandoTorresArrayQ);
        // so I created Integer object "array" to make a copy of FernandoTorresArrayH, then used a for loop to fill
        // the Integer array with the contents of the ArrayH
        // apparently I needed to do this because the code given to us for the heap class is a "generics" class and
        // also needs to be in its own class, so in order to be able to use the code for heap I need to pass it a
        // generics type of data because generics do not take in primitive data types
        Integer[] array = new Integer[FernandoTorresArrayH.length];
        for (int i = 0; i < FernandoTorresArrayH.length; i++) {
            array[i] = FernandoTorresArrayH[i];
        }
        // this is the call to the heap class where we pass the Integer[] array
        Heap.<Integer>FernandoTorresHeapSort(array);
        // for loop to then reconvert the Integer[] back into int[] ArrayH
        for (int i = 0; i < FernandoTorresArrayH.length; i++) {
            FernandoTorresArrayH[i] = array[i];
        }
        // these are the print outs with headers for each sort
        System.out.println("Insertion Sort: ");
        FernandoTorresPrintArray(FernandoTorresArrayI);
        System.out.println("Bubble Sort: ");
        FernandoTorresPrintArray(FernandoTorresArrayB);
        System.out.println("Merge Sort: ");
        FernandoTorresPrintArray(FernandoTorresArrayM);
        System.out.println("Quick Sort: ");
        FernandoTorresPrintArray(FernandoTorresArrayQ);
        System.out.println("Heap Sort: ");
        FernandoTorresPrintArray(FernandoTorresArrayH);
        // this bracket is for the main method
    }
    // ask user for input on how big array should be and stores it in N, in case they enter incorrect input there is
    // a recursive call if loop to make sure numbers are correct
    public static int FernandoTorresGetSize() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a number between 10 and 20: ");
        int N = in.nextInt();
        // get an error when i close scanner, idk why, does java/ intellij jdm close scanner for me ?
        //in.close();
        if (N >= 10 && N <= 20) {
            return N;
        } else {
            System.out.println("TEST STATEMENT TO SEE IF ITS STUCK IN RECURSIVE LOOP");
            return FernandoTorresGetSize();
        }
    }

    // takes in parameter N which is the size of the array and then ask the user for the numbers to fill the array with
    // uses a for loop to iterate N times and prints out how many more numbers need to be entered
    // returns the number to create original array
    public static int[] FernandoTorresGetArray(int N) {
        Scanner in = new Scanner(System.in);
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            System.out.println("We are going to ask you for " + (N - i) + " more numbers. Please enter a number and " +
                    "press enter.");
            int Nnumber = in.nextInt();
            A[i] = Nnumber;
        }
        return A;
    }
    // creates clone of original array and returns it
    public static int[] FernandoTorresCopyArray(int[] A) {
        int[] B = A.clone();
        return B;
    }
    // for loop to print out array
    public static void FernandoTorresPrintArray(int[] A) {
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i]);
            if (i < A.length - 1) {
                System.out.print(", ");
            }
        }
        // line feed
        System.out.println();
    }
    // insertion sort algorithm from textbook
    public static int[] FernandoTorresInsertionSort(int[] A) {
        for (int i = 1; i < A.length; i++) {
            int loca = A[i];
            int k;
            for (k = i - 1; k >= 0 && A[k] > loca; k--) {
                A[k + 1] = A[k];
            }
            A[k + 1] = loca;
        }
        return A;
    }
    // bubble sort algorithm from textbook
    public static int[] FernandoTorresBubbleSort(int[] A) {
        boolean needNextPass = true;
        for (int k = 1; k < A.length && needNextPass; k++) {
            needNextPass = false;
            for (int i = 0; i < A.length - k; i++) {
                if (A[i] > A[i + 1]) {
                    int temp = A[i];
                    A[i] = A[i + 1];
                    A[i + 1] = temp;
                    needNextPass = true;
                }
            }
        }
        return A;
    }
    // helper merge method for mergeSort
    public static void merge(int[] list1, int[] list2, int[] temp) {
        int current1 = 0;
        int current2 = 0;
        int current3 = 0;
        while (current1 < list1.length && current2 < list2.length) {
            if (list1[current1] < list2[current2]) {
                temp[current3++] = list1[current1++];
            } else {
                temp[current3++] = list2[current2++];
            }
        }
        while (current1 < list1.length) {
            temp[current3++] = list1[current1++];
        }
        while (current2 < list2.length) {
            temp[current3++] = list2[current2++];
        }
    }
    // mergeSort algorithm from textbook
    public static void FernandoTorresMergeSort(int[] A) {
        if (A.length > 1) {
            int[] firstHalf = new int[A.length / 2];
            System.arraycopy(A, 0, firstHalf, 0, A.length / 2);
            FernandoTorresMergeSort(firstHalf);
            // break
            int secondHalfLength = A.length - A.length / 2;
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(A, A.length / 2, secondHalf, 0, secondHalfLength);
            FernandoTorresMergeSort(secondHalf);
            // just spacer line to show when merge is used
            merge(firstHalf, secondHalf, A);
        }
    }
    // quickSort algorithm from textbook
    public static void FernandoTorresQuickSort(int[] A) {
        quickSort(A, 0, A.length - 1);
    }
    // quickSort helper method
    private static void quickSort(int[] A, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(A, first, last);
            quickSort(A, first, pivotIndex - 1);
            quickSort(A, pivotIndex + 1, last);
        }
    }
    // helper partition method for quickSort
    private static int partition(int[] A, int first, int last) {
        int pivot = A[first];
        int low = first + 1;
        int high = last;
        while (high > low) {
            while (low <= high && A[low] <= pivot) {
                low++;
            }
            while (low <= high && A[high] > pivot) {
                high--;
            }
            if (high > low) {
                int temp = A[high];
                A[high] = A[low];
                A[low] = temp;
            }
        }
        while (high > first && A[high] >= pivot) {
            high--;
        }
        if (pivot > A[high]) {
            A[first] = A[high];
            A[high] = pivot;
            return high;
        } else {
            return first;
        }
    }


// this bracket is for the Main
}
