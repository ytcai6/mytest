package com.wechatConnection;

public class test {
    public static void main(String[] args) {
        int[] a = {4, 2, 1, 3, 6, 7, 9, 8, 0, 10};
        Qsort(a, 0, a.length - 1);
        for (int i : a) {
            System.out.println(i);
        }
    }

    public static void Qsort(int[] a, int l, int r) {
        if(l>=r) return;
        int i=l,j=r;
        int key=a[i],temp;
        while (i < j) {
            while (i < j && a[j] > key) {
                j--;
            }
            while (i < j && a[i] <= key) {
                i++;
            }
            if (i < j) {
                swap(a, i, j);
            }
        }
        swap(a, i, l);
        Qsort(a, l, i - 1);
        Qsort(a, i + 1, r);
    }

    public static void swap(int a[], int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
