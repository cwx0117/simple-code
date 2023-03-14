package sort;

public class InsertSort {
    public static int [] sort(int[] arr){
        int swap;
        for(int i=0;i<arr.length-1;i++){
            for(int j=i+1;j>0;j--){
                if(arr[j]<arr[j-1]){
                    swap=arr[j-1];
                    arr[j-1]=arr[j];
                    arr[j]=swap;
                }
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
