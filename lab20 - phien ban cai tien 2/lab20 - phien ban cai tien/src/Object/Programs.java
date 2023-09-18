
package Object;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Programs implements Serializable{
    private String id;
    private String name;
    private String time;
    private Date begin;
    private Date end;
    private int days;
    private String location;
    private double cost;
    private String content;

    public Programs() {
    }

    public Programs(String id, String name, String time, Date begin, Date end, int days, String location, double cost, String content) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.begin = begin;
        this.end = end;
        this.days = days;
        this.location = location;
        this.cost = cost;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public Date getBegin() {
        return begin;
    }

    public Date getEnd() {
        return end;
    }

    public int getDays() {
        return days;
    }

    public String getLocation() {
        return location;
    }

    public double getCost() {
        return cost;
    }

    public String getContent() {
        return content;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedbegin = dateFormat.format(begin);
        String formattedend = dateFormat.format(end);
        
        return "Programs{" + "id: " + id + ", name: " + name + ", time: " + time + ", begin: " + formattedbegin + ", end: " + formattedend + ", days: " + days + ", location: " + location + ", cost: " + cost + ", content: " + content + '}';
    }
    
}
