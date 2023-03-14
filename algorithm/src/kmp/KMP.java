package kmp;

public class KMP {
    public static void main(String[] args) {
        String s="12345123451236";
        int[] next=getNextArray(s.toCharArray());
        System.out.println(next[13]);
    }
    public static int getIndexOf(String str1,String str2){
        if(str1==null||str2==null||str2.length()<1||str1.length()<str2.length()){
            return -1;
        }

        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();

        int i1=0;
        int i2=0;
        int[] next=getNextArray(ch2);

        while (i1<ch1.length&&i2<ch2.length){
            if(ch1[i1]==ch2[i2]){
                i1++;
                i2++;
            }else if(next[i2]==-1){
                i1++;
            }else {
                i2=next[i2];
            }
        }
        return i2==ch2.length?i1-i2:-1;
    }

    public static int[] getNextArray(char[] ms){
        if(ms.length==1){
            return new int[]{-1};
        }
        int[] next=new int[ms.length];
        next[0]=-1;
        next[1]=0;
        int i=2;
        int cn=0;
        while (i<next.length){
            if(ms[i-1]==ms[cn]){
                next[i++]=++cn;
            }else if(cn>0){
                cn=next[cn];
            }else {
                next[i++]=0;
            }
        }
        return next;
    }
}
