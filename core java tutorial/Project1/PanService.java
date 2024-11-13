package Project1;

import java.util.HashMap;
import java.util.Map;

public class PanService {
    private static Map<String,Pan> panMap=new HashMap<>();
    static {
        panMap.put("EDWABC123N8",new Pan("EDWABC123N8","1232332234","Union Bank","Stocks,ELSS,FD"));
        panMap.put("EDWABC12348",new Pan("EDWABC12348","1232332244","Axis Bank","Stocks,FD"));
    }

    public Pan getPanByAadhaarNumber(String aadhaarNumber){
        for(Pan pan:panMap.values()){
            if(pan.getAadhaarNumber().equals(aadhaarNumber)){
                return pan;
            }
        }
        return null;
    }

}
