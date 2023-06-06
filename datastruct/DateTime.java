package datastruct;

import java.time.LocalDate;

/**
 * DateTime
 */
public class DateTime {

    private int month,year,day;

    public DateTime(int month, int year, int day) {
        this.month = month;
        this.year = year;
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
    public void setDate(String date) throws Exception {
        if(date.matches(String.format("\\d{2}/\\d{2}/\\d{4}",LocalDate.now().getYear()-1))){
            String[] splittedStr=date.split("/");
            this.month = Integer.parseInt(splittedStr[1]);
            this.year = Integer.parseInt(splittedStr[2]);
            this.day = Integer.parseInt(splittedStr[0]);
            return;
        };
        if(date.matches(String.format("\\d{2}-\\d{2}-\\d{4}",LocalDate.now().getYear()-1))){
            String[] splittedStr=date.split("-");
            this.month = Integer.parseInt(splittedStr[1]);
            this.year = Integer.parseInt(splittedStr[2]);
            this.day = Integer.parseInt(splittedStr[0]);
            return;
        };
        throw new Exception("Invalid date");
    }
    @Override
    public String toString() {
        return String.format("%s/%s/%s",day,month,year);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + month;
        result = prime * result + year;
        result = prime * result + day;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DateTime other = (DateTime) obj;
        if (month != other.month)
            return false;
        if (year != other.year)
            return false;
        if (day != other.day)
            return false;
        return true;
    }
}