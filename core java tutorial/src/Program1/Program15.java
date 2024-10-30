package Program1;

public class Program15 {
    public static void main(String[] args) {
        StringBuilder sb=new StringBuilder("Siddharth");
        System.out.println(sb);
        StringBuilder sb1=sb.append(" Shukla");
        System.out.println(sb==sb1);
        System.out.println(sb.equals(sb1));
        System.out.println(sb);
        System.out.println(sb1);
    }
}
