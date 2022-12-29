package com.myproject.app.Classes;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class TimePeriod{
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;

    public TimePeriod(){
        day = DayOfWeek.MONDAY;
        startTime = LocalTime.of(1, 0);
        endTime = LocalTime.of(1,30);
    }

    public TimePeriod(DayOfWeek day, LocalTime startTime, LocalTime endTime) throws Exception{
        this.day = day;
        if (startTime.isBefore(endTime)){
            this.startTime = startTime;
            this.endTime = endTime;
        } else {
            throw new Exception("ERROR: Start time cannot be after end time");
        }
        
    }
    public DayOfWeek getDay() {
        return day;
    }
    public void setDay(DayOfWeek day) {
        this.day = day;
    }
    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) throws Exception{
        if (startTime.isAfter(endTime)){
            throw new Exception("ERROR: Start time cannot be after end time");
        } else {
            this.startTime = startTime;
        }
        
    }
    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) throws Exception {
        if (startTime.isAfter(endTime)){
            throw new Exception("ERROR: End time cannot be before start time");
        } else {
            this.endTime = endTime;
        }

    }

    public void setTimes(LocalTime startTime, LocalTime endTime) throws Exception{
        if (startTime.isAfter(endTime)){
            throw new Exception("ERROR: Start time cannot be after end time");
        } else {
            this.startTime = startTime;
            this.endTime = endTime;
        }
        
    }

    public boolean overlap(TimePeriod other){
        boolean bol = false;
        if (day.equals(other.getDay())){
            if (startTime.isBefore(other.getEndTime()) && endTime.isAfter(other.getStartTime())){
                bol = true;
            }
        }
        return bol;
    }
}