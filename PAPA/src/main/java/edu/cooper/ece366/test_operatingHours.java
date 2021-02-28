package edu.cooper.ece366;

import edu.cooper.ece366.model.OperatingHours;

import java.util.ArrayList;

public class test_operatingHours {
    public static void main(String[] args) {
        OperatingHours op = new OperatingHours();
        op.add(0,"0800","2200",false);
        op.add(1,"0800","2200",false);
        op.add(2,"0800","2200",false);
        op.add(3,"0800","2200",false);
        op.add(4,"0800","2200",false);
        //op.add(5,"0800","2200",false);
        op.add(6,"0800","1200",false);
        op.add(6,"1800","2200",false);
        op.add(6,"1300","1500",false);

        ArrayList<String> s = op.pString();
        for(String str : s) {
            System.out.println(str);
        }
        /*
        Map<DayOfWeek, ArrayList<ArrayList<LocalTime>>> result = new HashMap<>();

        result = op.getOperatingHour();
        Iterator it = result.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry entry = (Map.Entry)it.next();
            System.out.println(entry.getKey().toString() + "    " + entry.getValue().toString());
            it.remove();
        }

         */

    }
}
