package sort;

public class HeapSort {
    public  static void heapSort(int[] arr){
        //  1.用户依次给定数据，给当前所有数据排成大根堆
        for(int i=0;i<arr.length;i++){
            heapInsert(arr,i);
        }
        //  2.将0位置上的数和最后一个数的位置交换，heapSize--
        int heapSize=arr.length;
        swap(arr,0,--heapSize);//--在前是因为一开始是arr.length-1
        //  3.交换后进行heapify操作，将数据重排成大根堆,直到index没有子树
        while (heapSize>0){
            heapify(arr,0,heapSize);
            //  4.重复2操作
            swap(arr,0,--heapSize);
        }
    }

    public static void heapInsert(int[] arr,int index){
        while (arr[index]>arr[(index-1)/2]){
            swap(arr,index,(index-1)/2);
            index=(index-1)/2;
        }
    }

    public static void heapify(int[] arr,int index,int heapSize){
        //一直循环到index没有子树-->没有左树
        int left =index*2+1;
        while (left<heapSize){
            //孩子中的大值 有右树做比较，无就左边大
            int largest =left+1<heapSize&&arr[left+1]>arr[left]?left+1:left;
            //父亲和孩子谁最大
            largest =arr[largest]>arr[index]?largest:index;
            //如果父亲大 退出循环
            if(largest==index){
                break;
            }
            //孩子大则交换
            swap(arr,largest,index);
            index=largest; //父亲变成孩子
            left=2*index+1;//因为条件判断中需要判断left 所以还要将父亲变成孩子后的左树找到
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr ={5,4,6,7,3,9};
        heapSort(arr);
        for (int value : arr) {
            System.out.print(value+" ");
        }

        String s="hello world";
        byte[] bytes = s.getBytes();
        System.out.println(bytes);
    }
}
