package com.atguigu;

public class Sort {

    /**
     * 主方法iasda阿萨德qwe
     * @param args
     */
    public static void main(String[] args) {
        int[] a = {3,6,1,8,3,0};
        int[] b = quickSort(0,a.length-1,a);
        for (int x : b) {
            System.out.println(x);
        }
    }

    public static int[] quickSort(int start, int end, int[] a) {
        if (start > end) {
            return a;
        }
        if (a.length <= 1){
            return a;
        }
        int i = start;
        int j = end;
        int begin = a[i];
        while (i < j) {
            while (j >= i && a[j] > begin) {
                j --;
            }
            while (i < j && a[i] <= begin) {
                i ++;
            }
            swap(a,i,j);
        }
        swap(a,start,i);
        quickSort(start,i-1,a);
        quickSort(i+1,end,a);
        return a;
    }

    public static int[] swap(int[] a,int i,int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
        return a;
    }
}


