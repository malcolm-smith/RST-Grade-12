import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

// this class is used to keep track and maintain all scores 
public class ScoreSystem {

    public final String filePath = "../src/scores.txt";
    public Scanner in;

    // constructor
    public ScoreSystem() {
        try {
            in = new Scanner(new File(this.filePath));
        } catch (FileNotFoundException e) { // if the file is not found
            e.printStackTrace();
            System.exit(0);
        }
    }

    // shows the highest five scores in the scores.txt file
    public void showHighScores() {
        int[] array = sortScores(readScores());
        String str = "";
        for (int i = 0; i < 5; i++) {
            str += Integer.toString(array[i]) + "\n";
        }
        JOptionPane.showMessageDialog(null, str);
    }

    // sorts the scores in descending order
    public int[] sortScores(int[] arr) {
        // using merge sort to sort ALL the high scores, RECURSION
        sort(arr, 0, arr.length - 1);
        return arr;
    }

    // reads the scores from a file
    public int[] readScores() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        while (in.hasNext()) {
            list.add(in.nextInt());
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    // appends a score to the file, for future use
    public void addScore(int score) {
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));
            printWriter.println(Integer.toString(score));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // merge sort methods, are recursive
    private void merge(int arr[], int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] >= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    private void sort(int arr[], int l, int r) {
        if (l < r) {

            int m = (l + r) / 2;

            sort(arr, l, m);
            sort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }
}