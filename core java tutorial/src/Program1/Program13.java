package Program1;

public class Program13 {
    public static void main(String[] args) {
        //string is immutable
        //Why?
        //Unnecessary changes should not be applied everywhere
        //sirf usi state kay liye apply ho
        String str=new String("Hello");
        String s1=str;
        String s2=str;
        System.out.println(s2);
        System.out.println(s1);
        System.out.println(str);
        str=str+"Siddharth";
        System.out.println(str);
        System.out.println(s1);
        System.out.println(s2);
    }
}
