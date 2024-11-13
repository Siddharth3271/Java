package Project1;

import java.util.HashMap;
import java.util.Map;

public class AadhaarService {
    private static Map<String,Aadhaar>aadhaarMap=new HashMap<>();
    static{
        aadhaarMap.put("1232332234",new Aadhaar("1232332234","Siddharth","Brijesh Shukla","Kanpur"));
    aadhaarMap.put("1232332244",new Aadhaar("1232332244","Rohan","Barun Verma","Delhi"));
    }

    public Aadhaar getAadhaarByNumber(String aadhaarNumber){
        return aadhaarMap.get(aadhaarNumber);
    }
}
