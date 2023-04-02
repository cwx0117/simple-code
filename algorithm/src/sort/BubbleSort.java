package sort;

//个人理解插入排序和冒泡排序的不同：
//冒泡排序的元素都是在循环数组里进行比较
//而插入是从循环数组后的一个元素开始比较
public class BubbleSort {
    private static void bubbleSort(int[] arr) {


        if(arr==null || arr.length < 2 ){
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i -1; j++) {   // 这里说明为什么需要-1
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr ={3,5,2,1,6,9};
        bubbleSort(arr);
        for (int value : arr) {
            System.out.print(value+" ");
        }
    }
}
