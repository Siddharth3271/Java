package Program1;

public class Program14 {
    public static void main(String[] args) {
        StringBuffer str=new StringBuffer("Siddharth");
        StringBuffer str1=str.append(" Shukla");
        System.out.println(str);

        String s1="hello";
        String s2=s1.concat(" World");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1==s2);  //reference not same

        System.out.println(str1);
        System.out.println(str1==str);  //do changes in same objects

        
    }
}
