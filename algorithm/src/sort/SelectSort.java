package sort;

public class SelectSort {


    private static int[] sort(int[] arr){
        int minIndex,temp;
        for (int i=0;i<arr.length-1;i++){
            minIndex=i;
            for (int j=i+1;j<arr.length;j++ ){ //找到最小
                if(arr[j]<arr[minIndex]){
                    minIndex=j;
                }
                temp = arr[i];//交换
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr ={3,5,2,1,6,9};
        sort(arr);
        for (int value : arr) {
            System.out.print(value+" ");
        }
    }

}
