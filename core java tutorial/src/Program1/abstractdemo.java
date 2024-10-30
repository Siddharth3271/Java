package Program1;
//does not have any object
abstract class vehicle{
    public abstract void engine();
    public void horsepower(){
        System.out.println("Horsepower is 4500");
    }
}

class BMW extends vehicle{
    @Override
    public void engine() {
        System.out.println("4000cc");
    }
    //we have to implement all methods of abstract class

}
class Tata extends vehicle{

    @Override
    public void engine() {
        System.out.println("2100cc");
    }
}
public class abstractdemo
{
    public static void main(String[] args) {
        BMW obj=new BMW();
        obj.engine();
        vehicle obj1=new BMW();
        obj1.engine();
        vehicle obj2=new Tata();
        obj2.engine();
    }
}
