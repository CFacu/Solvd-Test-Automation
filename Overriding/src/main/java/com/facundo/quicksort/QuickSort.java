package com.facundo.quicksort;

import java.util.Arrays;

public class QuickSort {
    public static void quickSort(int[] array, int left, int right) {
        if (array == null || array.length == 0) {
            return;
        }
        if (left >= right) {
            return;
        }

        int middle = left + (right - left) / 2;
        int pivot = array[middle];

        int i = left;
        int j = right;

        while (i <= j) {
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }

            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (left < j) {
            quickSort(array, left, j);
        }
        if (right > i) {
            quickSort(array, i, right);
        }
    }

    public static void main(String[] args) {
        int[] array = {5,9,4,7,2,4,1,6,3,4};
        System.out.println(Arrays.toString(array));

        quickSort(array, 0, array.length - 1);

        System.out.println(Arrays.toString(array));
    }
}
