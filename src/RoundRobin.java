import java.util.ArrayList;

public class RoundRobin{
    public float response_time, return_time, waiting_time, time, number_processes;
    public int quantum;
    ArrayList<Process> proc;

    public RoundRobin(ArrayList p, int quantum){
        this.proc = (ArrayList<Process>) p.clone();
        response_time = 0;
        return_time = 0;
        waiting_time = 0;
        time = 0;
        number_processes = proc.size();
        this.quantum = quantum;
    }

    public void run(){

        time = proc.get(0).coming;
        Process current;
        ArrayList<Process> ready = new ArrayList<>();
        ArrayList<Process> wait = new ArrayList<>();

        for(int i = 0; i < proc.size(); i++){
            ready.add(proc.get(i));
        }

        while(true){
            if((wait.isEmpty() && ready.isEmpty())) break;

            if(wait.isEmpty() && !ready.isEmpty()){
                if(!ready.get(0).responseTime_calculator){
                    response_time += time - ready.get(0).coming;
                    ready.get(0).responseTime_calculator = true;
                }
                current = ready.remove(0);

            }else {
                if(!wait.get(0).responseTime_calculator){
                    response_time += time - wait.get(0).coming;
                    wait.get(0).responseTime_calculator = true;
                }
                current = wait.remove(0);
            }

            //time += quantum;
            //System.out.println("time: " + time);
            //System.out.println("P"+current.name + "(" + current.duration + ")");

            int count = ready.size();
            for(int i = 0; i < count; i++){
                    if(ready.get(0).coming <= time+quantum){
                    wait.add(ready.remove(0));

                }
            }
            time += quantum;
            current.duration -= quantum;

            if(current.duration == 0){
                //time += quantum;
                return_time += time - current.coming;
                waiting_time += (time - current.coming) - current.peak;
            }else{
                if(current.duration == 1){
                    wait.add(current);

                }else{
                    if(current.duration < 0){
                        time--;
                        return_time += time - current.coming;
                        waiting_time += (time - current.coming) - current.peak;
                        continue;
                    }

                    wait.add(current);
                }
            }

        }
        System.out.printf("RR %.1f %.1f %.1f %n", return_time/number_processes, response_time/number_processes,
                waiting_time/number_processes);
    }
}
