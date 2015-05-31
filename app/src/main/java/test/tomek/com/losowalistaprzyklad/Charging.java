package test.tomek.com.losowalistaprzyklad;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tomasz on 24.05.2015.
 */
public class Charging {

    public static final SimpleDateFormat FMT = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

    private Date start;
    private Date stop;

    public void setStart(Date start) {
        this.start = start;
    }

    public void setStop(Date stop) {
        this.stop = stop;
    }

    public Date getStop() {
        return stop;
    }

    public Date getStart() {
        return start;
    }

    public void setStartAsNow()
    {
        setStart(new Date());
    }

    public void setStopAsNow() {
        setStop(new Date());
    }


    @Override
    public String toString() {
        return "Charging{" +
                "start=" + start +
                ", stop=" + stop +
                '}';
    }





}
