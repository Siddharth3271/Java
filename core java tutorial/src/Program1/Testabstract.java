package Program1;
class checkabs{
    public void printEngineDetails(vehicle obj){
        obj.engine();
    }
}
public class Testabstract {
    public static void main(String[] args) {
        checkabs t=new checkabs();
        //dynamic dispatching=>parent can hold child object
        t.printEngineDetails(new Tata());
        t.printEngineDetails(new BMW());
    }
}
