package edu.cooper.ece366.util;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OperationHours {
    public enum DAY {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    private ArrayList<OperationHours> operationHours;

    public OperationHours(DAY day, int from, int to) {
        this.day = day;
        this.from = from; // format time using 800 for 8:00am or 2300 for 23:00
        this.to = to;
    }

    @Override
    public String toString() {
        return "OpeningHours [day=" + day + ", from=" + from + ", to=" + to + ", isAllDay=" + isAllDay + "]";
    }

    public OperationHours() {

    }

    public DAY day;
    public int from;
    public int to;
    public boolean isAllDay = false;

    public void isOpenx(LocalDateTime start) {

    }

    public boolean isOpen(LocalDateTime start) {

        if (day.ordinal() != start.getDayOfWeek().getValue() - 1) {
            return false;
        }

        if (isAllDay)
            return true;

        String f = String.format("%04d", from);
        String t = String.format("%04d", to);

        Integer fh = Integer.valueOf(f.substring(0, 2));
        Integer fm = Integer.valueOf(f.substring(2));

        Integer th = Integer.valueOf(t.substring(0, 2));
        Integer tm = Integer.valueOf(t.substring(2));

        LocalDateTime intStart = start.withHour(fh).withMinute(fm);
        LocalDateTime intEnd = start.withHour(th).withMinute(tm);

        if (intStart.equals(start) || intEnd.equals(start)) {
            return true;
        }
        if (intStart.isBefore(start) && intEnd.isAfter(start)) {
            return true;
        }

        return false;

    }
}