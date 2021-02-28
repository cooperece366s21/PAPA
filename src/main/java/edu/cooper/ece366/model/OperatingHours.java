package edu.cooper.ece366.model;

import java.util.*;
import java.time.LocalTime;
import java.time.DayOfWeek;

public class OperatingHours {

    private Map<DayOfWeek, ArrayList<ArrayList<LocalTime>>> operatingHour;

    public static class OperatingPeriod{
        private final int day;
        private final DayOfWeek dayOfWeek;

        private String start;
        private String end;

        private LocalTime open;
        private LocalTime close;

        private boolean is_overnight;

        private ArrayList<LocalTime> hours;

        String[] operatingPeriodStr;

        public OperatingPeriod(int day, String start, String end, boolean is_overnight){
            this.day = day;
            this.dayOfWeek = DayOfWeek.of(day+1);

            this.start = start;
            this.end = end;

            this.open = LocalTime.of(Integer.parseInt(start.substring(0,2)), Integer.parseInt(start.substring(2)));
            this.close = LocalTime.of(Integer.parseInt(end.substring(0,2)), Integer.parseInt(end.substring(2)));

            this.is_overnight = is_overnight;

            this.hours = new ArrayList<>();
            hours.add(open); hours.add(close);

            this.operatingPeriodStr = new String[]{dayOfWeek.toString(), open.toString(), close.toString()};

        }

        public DayOfWeek getDayOfWeek(){
            return dayOfWeek;
        }
        public LocalTime getOpen(){
            return open;
        }
        public LocalTime getClose(){
            return close;
        }
        public ArrayList<LocalTime> getHours(){
            return hours;
        }
        public String[] toStr(){
            return operatingPeriodStr;
        }
    }

    public void add(int day, String start, String end, boolean is_overnight){
        OperatingPeriod oPeriod = new OperatingPeriod(day, start, end, is_overnight);
        operatingHour.computeIfAbsent(oPeriod.getDayOfWeek(), k -> new ArrayList<>()).add(oPeriod.getHours());
    }

    //public void add()

    public OperatingHours(){
        this.operatingHour = new HashMap<>();
        for(int i=1; i<8; i++){
            operatingHour.put(DayOfWeek.of(i), new ArrayList<>());
        }
    }
    public Map<DayOfWeek, ArrayList<ArrayList<LocalTime>>> getOperatingHour(){
        return operatingHour;
    }

    // pretty string format
    public ArrayList<String> pString(){
        ArrayList<String> s = new ArrayList<>();
        ensureSize(s,7);

        Iterator it = this.operatingHour.entrySet().iterator();
        while(it.hasNext()){

            Map.Entry entry = (Map.Entry)it.next();

            DayOfWeek d = (DayOfWeek) entry.getKey();
            String dayofweek = "";
            switch (d.getValue()){
                case 1:
                    dayofweek = "MON";
                    break;
                case 2:
                    dayofweek = "TUE";
                    break;
                case 3:
                    dayofweek = "WED";
                    break;
                case 4:
                    dayofweek = "THU";
                    break;
                case 5:
                    dayofweek = "FRI";
                    break;
                case 6:
                    dayofweek = "SAT";
                    break;
                case 7:
                    dayofweek = "SUN";
                    break;
                default:
                    dayofweek = "Special Hours: ";
                    break;
            }

            ArrayList<ArrayList<LocalTime>> t = (ArrayList<ArrayList<LocalTime>>) entry.getValue();
            String hours = "";
            if(t.isEmpty()){
                hours = "Closed";
            } else {
                //Collections.sort(t.get().get(0));
                for (int i = 0; i < t.size(); i++) {
                    String punctuation = "";
                    if(i+1 < t.size()) { punctuation = ", "; }
                    else { punctuation = ";"; }
                    hours += t.get(i).get(0).toString() + " - " + t.get(i).get(1).toString() + punctuation;
                }
            }

            s.set(d.getValue()-1, dayofweek + "    " + hours);
            it.remove();
        }
        /*
        for(String str : s) {
            System.out.println(str);
        }
         */
        return s;
    }

    private static void ensureSize(ArrayList<?> list, int size) {
        list.ensureCapacity(size);
        while (list.size() < size) {
            list.add(null);
        }
    }
}

/*
    private ArrayList<OperatingPeriod> op;
    private ArrayList<String[]> opStr;
    boolean is_open_now;

    public static class OperatingPeriod{
        private final int day;
        private String start;
        private String end;
        private boolean is_overnight;
        private final DayOfWeek dayOfWeek;
        private LocalTime open;
        private LocalTime close;
        String[] operatingPeriodStr;

        public OperatingPeriod(int day, String start, String end, boolean is_overnight){
            this.day = day;
            this.start = start;
            this.end = end;
            this.is_overnight = is_overnight;
            this.dayOfWeek = DayOfWeek.of(day+1);
            this.open = LocalTime.of(Integer.parseInt(start.substring(0,2)), Integer.parseInt(start.substring(2)));
            this.close = LocalTime.of(Integer.parseInt(end.substring(0,2)), Integer.parseInt(end.substring(2)));
            this.operatingPeriodStr = new String[]{dayOfWeek.toString(), open.toString(), close.toString()};
        }

        public String[] toStr(){
            return operatingPeriodStr;
        }

        public DayOfWeek getDayOfWeek(){
            return dayOfWeek;
        }
        public LocalTime getOpen(){
            return open;
        }
        public LocalTime getClose(){
            return close;
        }
        public void set(int day){
            this.day = day;

        }
    }

    public void add(int day, String start, String end, boolean is_overnight){
        OperatingPeriod oPeriod = new OperatingPeriod(day, start, end, is_overnight);
        this.op.add(oPeriod);
        this.opStr.add(oPeriod.toStr());
    }
    public ArrayList<OperatingPeriod> getOperatingHours(){
        return op;
    }
    public ArrayList<String[]> toStr(){
        return opStr;
    }

    public OperatingHours(){
        this.op = new ArrayList<>();
        this.opStr = new ArrayList<>();
    }
 */