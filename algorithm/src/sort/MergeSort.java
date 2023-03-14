package sort;

public class MergeSort {


    private static  void mergeSort(int[] arr,int left,int right){
        if(left==right){
            return;
        }
        int mid =left+((right-left)>>1);
        if(left<right){
            mergeSort(arr,left,mid);
            mergeSort(arr,mid+1,right);
            merge(arr,left,mid,right);
        }

    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right-left+1];
        int i=0;
        int p1=left;
        int p2=mid+1;
        while (p1<=mid&&p2<=right){
            help[i++]=arr[p1]<=arr[p2]?arr[p1++]:arr[p2++];
        }
        while (p1<=mid){
            help[i++]=arr[p1++];
        }
        while (p2<=right){
            help[i++]=arr[p2++];
        }
        for(i=0;i<help.length;i++){
            arr[left+i]=help[i];
        }

    }
    public static void main(String[] args) {
        int[] arr ={5,4,6,7,3,9,1,1,8,5,2,0,3,6,7,10};
        mergeSort(arr,0,15);
        for (int value : arr) {
            System.out.print(value+" ");
        }
    }
}
