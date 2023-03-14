package sort;

public class QuickSort {

    private static void sort(int arr[],int low,int high) {
        if(low<high){
            int mid=partition(arr,low,high);
            sort(arr,low,mid-1);
            sort(arr,mid+1,high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        // 设定基准值（pivot）
        int pivot = low;
        int index = pivot + 1;
        for (int i = index; i <= high; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);//找到比基准值小的数依次排列到基准值前面
                index++;
            }
        }
        swap(arr, pivot, index - 1);//交换所以比基准值小的数的最后一位和基准值的位置即基准值到达正确位置，完成一次排序
        return index - 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr ={5,4,6,7,3,9};
        sort(arr,0,5);
        for (int value : arr) {
            System.out.print(value+" ");
        }
    }

}
