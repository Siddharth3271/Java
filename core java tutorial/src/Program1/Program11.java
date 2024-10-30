package Program1;

import java.util.Scanner;
class AddNum{
    void sumnum(int a,int b){
        int c=a+b;
        System.out.println(c);
    }
}
public class Program11 {
    public static void main(String[] args) {
        System.out.println("Hello world");
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the value:");
        int data=sc.nextInt();
        System.out.println(data);

        AddNum obj=new AddNum();
        obj.sumnum(5,6);
    }
}
