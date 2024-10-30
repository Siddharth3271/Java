package Program1;

public class Program12 {
    public static void main(String args[]){
        //2 objects are created by using new
        //one in heap and one in string constant pool
        String str=new String("Hello");
        String str3=new String("Hello World");
        //only  one object is created in string constant pool
        //if object with same content is in "SCP" then reference variable start pointing to the existing object
        //in "SCP" duplicates are not allowed
        String str1="Hello World";
        System.out.println(str==str1);
        String str2="Hello World";   //creates str1 and str2 separately in heap
        System.out.println(str1==str2);  //comparing reference
        System.out.println(str1.equals(str2));
        System.out.println(str3==str2);
        System.out.println(str3.equals(str2));

        //array mey length keyword hota hai
        //string mey length method hota hai
    }
}
