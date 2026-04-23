/*
 * Project 4.1.5
 */ 
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SearchAndSortAnalysis {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Book> books = Data.getBooks();

        ArrayList<Book> selectionCopy = new ArrayList<>(books);
        ArrayList<Book> insertionCopy = new ArrayList<>(books);
        ArrayList<Book> mergeCopy = new ArrayList<>(books);

        System.out.println("SORTING ANALYSIS");

        double start = System.nanoTime();
        selectionSort(selectionCopy);
        System.out.println("Selection Sort: " + (System.nanoTime() - start) / 1e9 + "s");

        start = System.nanoTime();
        insertionSort(insertionCopy);
        System.out.println("Insertion Sort: " + (System.nanoTime() - start) / 1e9 + "s");

        start = System.nanoTime();
        mergeSort(mergeCopy);
        System.out.println("Merge Sort: " + (System.nanoTime() - start) / 1e9 + "s");

        // ADDED: SEARCH ANALYSIS
        // We search using mergeCopy because it is already sorted (required for Binary Search)
        String target = mergeCopy.get(mergeCopy.size() / 2).getTitle();
        System.out.println("\nSEARCHING ANALYSIS");

        long sStart = System.nanoTime();
        linearSearch(mergeCopy, target);

        System.out.println("Linear Search: " + (System.nanoTime() - sStart) + " ns");

        sStart = System.nanoTime();
        binarySearchIterative(mergeCopy, target);
        System.out.println("Iterative Binary: " + (System.nanoTime() - sStart) + " ns");


        sStart = System.nanoTime();
        binarySearchRecursive(mergeCopy, target, 0, mergeCopy.size() - 1);
        System.out.println("Recursive Binary: " + (System.nanoTime() - sStart) + " ns");
    }

    public static void selectionSort(ArrayList<Book> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).getTitle().compareTo(list.get(minIdx).getTitle()) < 0) {
                    minIdx = j;
                }
            }
            Book temp = list.get(minIdx);
            list.set(minIdx, list.get(i));
            list.set(i, temp);
        }
    }

    public static void insertionSort(ArrayList<Book> list) {
        for (int i = 1; i < list.size(); i++) {
            Book key = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j).getTitle().compareTo(key.getTitle()) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    public static void mergeSort(ArrayList<Book> list) {
        if (list.size() <= 1) return;
        int mid = list.size() / 2;
        ArrayList<Book> left = new ArrayList<>(list.subList(0, mid));
        ArrayList<Book> right = new ArrayList<>(list.subList(mid, list.size()));
        mergeSort(left);
        mergeSort(right);
        mergeHelper(list, left, right);
    }
    private static void mergeHelper(ArrayList<Book> list, ArrayList<Book> left, ArrayList<Book> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).getTitle().compareTo(right.get(j).getTitle()) <= 0) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) list.set(k++, left.get(i++));
        while (j < right.size()) list.set(k++, right.get(j++));
    }

    // --- ADDED SEARCH METHODS ---

    public static int linearSearch(ArrayList<Book> list, String target) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTitle().equals(target)) return i;
        }
        return -1;
    
    }


    public static int binarySearchIterative(ArrayList<Book> list, String target) {
        int low = 0, high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int compare = list.get(mid).getTitle().compareTo(target);
            if (compare == 0) return mid;
            else if (compare < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }


    public static int binarySearchRecursive(ArrayList<Book> list, String target, int low, int high) {
        if (low > high) return -1;
        int mid = (low + high) / 2;
        int compare = list.get(mid).getTitle().compareTo(target);
        if (compare == 0) return mid;
        else if (compare < 0) return binarySearchRecursive(list, target, mid + 1, high);
        else return binarySearchRecursive(list, target, low, mid - 1);
    }
}