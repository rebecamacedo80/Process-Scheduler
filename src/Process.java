import java.util.ArrayList;

public class Process {
    public int coming, duration, peak;
    public boolean responseTime_calculator = false;
    public String name;

    public Process(int coming, int duration, String name){
        this.coming = coming;
        this.duration = duration;
        this.peak = duration;
        this.name = name;
    }

}
