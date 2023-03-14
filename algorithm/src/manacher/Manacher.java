package manacher;

import java.util.Arrays;

//在已知以C为圆心的回文串中，在该半径内找到以i为轴的最长回文半径与以C为圆心的回文串进行比较，存储最大值，
// 当i到达C的半径边界时，扩R，将圆心设为i（保证了i一定在右半径上）
//在找以i为轴的最长回文半径时可以利用已知信息进行加速
public class Manacher {
    public static int[] maxLcpsLength(String s){
        if(s==null||s.length()==0){
            //return "";
            return new int[]{0};
        }
        char[] str=manacherString(s);
        for (int i=0;i<str.length;i++){
            System.out.print(str[i]);
        }
        int[] pArr=new int[str.length];
        int C=-1;//定义首次圆心所在的位置在-1
        int R=-1;//回文右边界再往右一个位置，最右有效区是R-1位置
        int max=Integer.MIN_VALUE;
        for (int i=0;i!=str.length;i++){
            //定义普遍情况
            // R>i时pArr[i]=1
            //当i在C的对称点的最大回文半径等于i的最大回文半径，则i点的最大回文半径就等于pArr[2*C-i]，此时pArr[2*C-i]<R-i
            //当i在C的对称点的最大回文半径大于i的最大回文半径,则i点的最大回文半径就等于R-i，此时pArr[2*C-i]>R-i
            //当i在C的对称点的最大回文半径恰好在边界的时候，i点的最大回文半径就等于R-i，此时pArr[2*C-i]=R-i
            pArr[i]=R>i?Math.min(pArr[2*C-i],R-i):1;
            while (i+pArr[i]<str.length&&i-pArr[i]>-1){
                //对第三种情况进行讨论，如果再下一个点仍然关于i对称，则pArr[i]++
                if(str[i+pArr[i]]==str[i-pArr[i]])
                    pArr[i]++;
                else {
                    break;
                }
            }
            //以i为轴，pArr[i]为半径超过R时
            if(i+pArr[i]>R){
                //扩R
                R=i+pArr[i];
                //中心设为i
                C=i;

            }
            max=Math.max(max,pArr[i]);
        }
        return pArr;
        //return str0;
    }

    private static char[] manacherString(String s) {
        char[] charArr=s.toCharArray();
        char[] res=new char[s.length()*2+1];
        int index=0;
        for (int i=0;i!=res.length;i++){
            res[i]=(i&1)==0?'#':charArr[index++];
        }
        return res;
    }

    public static void main(String[] args) {
        String s1="abcdcbakskabcdcbak";
        int n0 = s1.indexOf("cbak");
        System.out.println(n0);
        String s2="cbbd";
        String s="abcbak1234321";
        //int n=maxLcpsLength(s1);
        int[] pArr=maxLcpsLength(s2);
        int n=0;
        int max=Integer.MIN_VALUE;
        System.out.println(Arrays.toString(pArr));
        for (int i=0;i<pArr.length;i++){
            if(pArr[i]>max){
                max=pArr[i];
                n=i;
            }

        }
        System.out.println(n);
        System.out.println(pArr[n]);
        System.out.println((n)/2);
        if(pArr[n]%2==0){
            System.out.println(s2.substring(n/2-pArr[n]/2+1,n/2+pArr[n]/2));
        }else {
            System.out.println(s2.substring(n/2-pArr[n]/2,n/2+pArr[n]/2));
        }

    }
}
