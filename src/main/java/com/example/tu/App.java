package com.example.tu;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        int[][] data0 = { { 3, 0 }, { 0, 1 }, { 2, 2 } };
        int[][] data1 = { { 0, 1, 0, 0, 1, 0 }, {}, { 2, 2, 2, 2, 0, 1 } };
        int[] target1 = { 0, 0, 0 };
        int[] target2 = { 9, 9, 9, 9 };
        System.out.println(Arrays.deepToString(labelPath(3, new int[][] {})));
        System.out.println(Arrays.deepToString(labelPath(4, data0)));

        findMatches(data1, data0[1], target1);
        findMatches(data1, data0[2], target2);

        System.out.println(insertMiddle("XY", "abc"));
        System.out.println(insertMiddle("01234", "abc"));
        System.out.println(insertMiddle("01234567890123", "./-"));
    }

    /**
     * fügt Zeichen aus seps in input ein. In der Mitte der Rückgabe befindet sich
     * das erste Zeichen von seps. In der Mitte beider Hälften befindet sich dann
     * das zweite Zeichen von seps, usw. Diese Vorgehensweise
     * wird wiederholt, solange noch Zeichen zu vergeben sind und input in kleinere
     * Teile geteilt werden kann. Ansonsten wird input
     * zurückgegeben. Falls die Mitte nicht eindeutig bestimmt ist, wird so geteilt,
     * dass die linke Hälfte die kürzere ist. Die Buchstaben
     * aus seps werden bei der Bestimmung der Mitte nicht einbezogen.
     * Diese Methode muss rekursiv implementiert werden.
     * 
     * @param input Zeichenfolge in die eingesetzt werden soll
     * @param seps  Zeichenfolge die eingefügt werden soll
     * @return Der beabeitete Text
     */
    private static String insertMiddle(String input, String seps) {
        if (input.length() == 1) {
            return input;
        }
        int middle = 0;
        if (input.length() % 2 != 0) {
            middle = (input.length() - 1) / 2;
        } else {
            middle = input.length() / 2;
        }
        String replacedinput = "";
        replacedinput += input.substring(0, middle);
        replacedinput += seps.charAt(0);
        replacedinput += input.substring(middle, input.length());

        if (seps.length() < 2) {
            return replacedinput;
        }

        String replacedinputnew = insertMiddle(input.substring(0, middle), seps.substring(1)) + seps.charAt(0)
                + insertMiddle(input.substring(middle, input.length()), seps.substring(1));

        return replacedinputnew;
    }

    /**
     * bestimmt für jede Zeile i in data, wie oft die Folge der
     * Werte in pattern vorkommt. Die jeweilige Anzahl wird in target am Index i
     * abgelegt.
     * 
     * @param data    input Array in dem nach pattern gesucht werden soll
     * @param pattern die zu suchende Zeichenfolge
     * @param target  die aufsummierten einträge
     */
    private static void findMatches(int[][] data, int[] pattern, int[] target) {
        for (int i = 0; i < data.length; i++) { // foreach column
            int[] row = data[i];
            int counter = 0;
            for (int j = 0; j < row.length - pattern.length; j++) { // foreach row
                if (row[j] == pattern[0]) {
                    boolean b = true;
                    for (int k = 0; k < pattern.length; k++) { // foreach char in pattern
                        if (row[j + k] != pattern[k]) {
                            b = false;
                            break;
                        }
                    }
                    if (b) {
                        counter++;
                    }
                }
            }
            target[i] = counter;
        }
        System.out.println(Arrays.toString(target));
    }

    /**
     * erstellt ein neues n Mal n Array und gibt dieses zurück. Jede Zeile in points
     * beschreibt eine Stelle in dem neuen Array. Die Spalte 0 steht dabei immer für
     * den Zeilenindex und die Spalte 1 für den
     * Spaltenindex dieser Stelle. Das Rückgabearray enthält an jeder Stelle in
     * points den Wert -1. An allen anderen Stellen enthält
     * das Rückgabearray den Wert n.
     * 
     * @param n Größe der Arrays
     * @param points Array mit Punkten
     * @return Array in dem die Punkte makiert wurden
     */
    private static int[][] labelPath(int n, int[][] points) {
        int[][] myArray = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                myArray[i][j] = n;
            }
        }
        for (int k = 0; k < points.length; k++) {
            int x = points[k][0];
            int y = points[k][1];
            myArray[x][y] = -1;
        }
        return myArray;
    }
}
