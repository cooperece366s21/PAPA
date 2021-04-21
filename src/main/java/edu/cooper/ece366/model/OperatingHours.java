package edu.cooper.ece366.model;

import io.norberg.automatter.AutoMatter;

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
    public OperatingHours(Map<DayOfWeek, ArrayList<ArrayList<LocalTime>>> operatingHour){
        this.operatingHour = operatingHour != null ? operatingHour : Collections.emptyMap();
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

    public static class OperatingHoursBuilder{
        private Map<DayOfWeek, ArrayList<ArrayList<LocalTime>>> operatingHour;

        public OperatingHoursBuilder() {
        }

        private OperatingHoursBuilder(OperatingHours v) {
            Map<DayOfWeek, ArrayList<ArrayList<LocalTime>>> _operatingHour = v.operatingHour;
            this.operatingHour = _operatingHour == null ? null : new HashMap(_operatingHour);
        }

        private OperatingHoursBuilder(OperatingHoursBuilder v) {
            this.operatingHour = v.operatingHour == null ? null : new HashMap(v.operatingHour);
        }

        public Map<DayOfWeek, ArrayList<ArrayList<LocalTime>>> operatingHour() {
            if (this.operatingHour == null) {
                this.operatingHour = new HashMap();
            }

            return this.operatingHour;
        }

        public OperatingHoursBuilder operatingHour(Map<? extends DayOfWeek, ? extends ArrayList<ArrayList<LocalTime>>> operatingHour) {
            if (operatingHour == null) {
                throw new NullPointerException("operatingHour");
            } else {
                Iterator var2 = operatingHour.entrySet().iterator();

                Map.Entry entry;
                do {
                    if (!var2.hasNext()) {
                        this.operatingHour = new HashMap(operatingHour);
                        return this;
                    }

                    entry = (Map.Entry)var2.next();
                    if (entry.getKey() == null) {
                        throw new NullPointerException("operatingHour: null key");
                    }
                } while(entry.getValue() != null);

                throw new NullPointerException("operatingHour: null value");
            }
        }

        public OperatingHoursBuilder operatingHour(DayOfWeek k1, ArrayList<ArrayList<LocalTime>> v1) {
            if (k1 == null) {
                throw new NullPointerException("operatingHour: k1");
            } else if (v1 == null) {
                throw new NullPointerException("operatingHour: v1");
            } else {
                this.operatingHour = new HashMap();
                this.operatingHour.put(k1, v1);
                return this;
            }
        }

        public OperatingHoursBuilder operatingHour(DayOfWeek k1, ArrayList<ArrayList<LocalTime>> v1, DayOfWeek k2, ArrayList<ArrayList<LocalTime>> v2) {
            this.operatingHour(k1, v1);
            if (k2 == null) {
                throw new NullPointerException("operatingHour: k2");
            } else if (v2 == null) {
                throw new NullPointerException("operatingHour: v2");
            } else {
                this.operatingHour.put(k2, v2);
                return this;
            }
        }

        public OperatingHoursBuilder operatingHour(DayOfWeek k1, ArrayList<ArrayList<LocalTime>> v1, DayOfWeek k2, ArrayList<ArrayList<LocalTime>> v2, DayOfWeek k3, ArrayList<ArrayList<LocalTime>> v3) {
            this.operatingHour(k1, v1, k2, v2);
            if (k3 == null) {
                throw new NullPointerException("operatingHour: k3");
            } else if (v3 == null) {
                throw new NullPointerException("operatingHour: v3");
            } else {
                this.operatingHour.put(k3, v3);
                return this;
            }
        }

        public OperatingHoursBuilder operatingHour(DayOfWeek k1, ArrayList<ArrayList<LocalTime>> v1, DayOfWeek k2, ArrayList<ArrayList<LocalTime>> v2, DayOfWeek k3, ArrayList<ArrayList<LocalTime>> v3, DayOfWeek k4, ArrayList<ArrayList<LocalTime>> v4) {
            this.operatingHour(k1, v1, k2, v2, k3, v3);
            if (k4 == null) {
                throw new NullPointerException("operatingHour: k4");
            } else if (v4 == null) {
                throw new NullPointerException("operatingHour: v4");
            } else {
                this.operatingHour.put(k4, v4);
                return this;
            }
        }

        public OperatingHoursBuilder operatingHour(DayOfWeek k1, ArrayList<ArrayList<LocalTime>> v1, DayOfWeek k2, ArrayList<ArrayList<LocalTime>> v2, DayOfWeek k3, ArrayList<ArrayList<LocalTime>> v3, DayOfWeek k4, ArrayList<ArrayList<LocalTime>> v4, DayOfWeek k5, ArrayList<ArrayList<LocalTime>> v5) {
            this.operatingHour(k1, v1, k2, v2, k3, v3, k4, v4);
            if (k5 == null) {
                throw new NullPointerException("operatingHour: k5");
            } else if (v5 == null) {
                throw new NullPointerException("operatingHour: v5");
            } else {
                this.operatingHour.put(k5, v5);
                return this;
            }
        }

        public OperatingHoursBuilder putOperatingHour(int day, String start, String end, boolean is_overnight) {
            if (day < 0) {
                throw new NullPointerException("operatingHour: key");
            } else if (start == null || end == null) {
                throw new NullPointerException("operatingHour: value");
            } else {
                if (this.operatingHour == null) {
                    this.operatingHour = new HashMap();
                }
                OperatingPeriod oPeriod = new OperatingPeriod(day, start, end, is_overnight);
                this.operatingHour.computeIfAbsent(oPeriod.getDayOfWeek(), k -> new ArrayList<>()).add(oPeriod.getHours());
                return this;
            }
        }

        public OperatingHours build() {
            Map<DayOfWeek, ArrayList<ArrayList<LocalTime>>> _operatingHour = this.operatingHour != null ? Collections.unmodifiableMap(new HashMap(this.operatingHour)) : Collections.emptyMap();
            return new OperatingHours(_operatingHour);
        }

        public static OperatingHoursBuilder from(OperatingHours v) {
            return new OperatingHoursBuilder(v);
        }

        public static OperatingHoursBuilder from(OperatingHoursBuilder v) {
            return new OperatingHoursBuilder(v);
        }

    }
}