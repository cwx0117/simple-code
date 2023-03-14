package sort;
/*
小和问题
在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和，求一个数组的小和
解：
等价于右边几个数比当前数大，举例：【1，3，4，2，5】
 */
public class test1 {
    public static  int process(int[] arr,int l,int r){
        if(l==r){
            return 0;
        }
        if(l<r){
            int mid=l+((r-l)>>1);
            return process(arr,l,mid)+process(arr,mid+1,r)+merge(arr,l,mid,r);
        }
        return 0;
    }

    private static int merge(int[] arr, int l, int m, int r) {
        int[] help=new int[r-l+1];
        int i=0;
        int p1=l;
        int p2=m+1;
        int res=0;
        while (p1<=m&&p2<=r){
            res+=arr[p1]<arr[p2]?(r-p2+1)*arr[p1]:0;
            help[i++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }

        while (p1<=m){
            help[i++]=arr[p1++];
        }
        while (p2<=r){
            help[i++]=arr[p2++];
        }
        for(i=0;i<help.length;i++){
            arr[l+i]=help[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr ={1,3,4,2,5};
        int res=process(arr,0,4);
        System.out.println(res);

    }
}
