import java.util.ArrayList;

public class SJF {
    public float response_time, return_time, waiting_time, time, number_processes;
    ArrayList<Process> proc;

    public SJF(ArrayList proc){
        this.proc = (ArrayList<Process>) proc.clone();
        response_time = 0;
        return_time = 0;
        waiting_time = 0;
        number_processes = proc.size();
    }

    public static ArrayList<Process> sortDurationTime(ArrayList<Process> proc){
        for(int i = 0; i < proc.size(); i++) {
            for (int j = 0; j < proc.size() - 1; j++) {
                if (proc.get(j).duration > proc.get(j + 1).duration) {
                    Process aux = proc.get(j);
                    proc.set(j, proc.get(j + 1));
                    proc.set(j + 1, aux);
                }
            }
        }
        return proc;
    }

    public void run(){

        ArrayList<Process> ready = new ArrayList();
        int num_proc = proc.size();
        Process current;
        time = proc.get(0).coming;

        for(int i = 0; i < num_proc; i++){

            while(!proc.isEmpty()){
                if (proc.get(0).coming <= time) {
                    ready.add(proc.remove(0));
                }else break;
            }

            ready = sortDurationTime(ready);


            current = ready.remove(0);

            //tempo de resposta do processo i
            response_time += time - current.coming;

            //tempo de retorno do processo i
            return_time += (time + current.duration) - current.coming;

            //tempo de espera
            waiting_time += time - current.coming;

            //atualizando time
            time += current.duration;

        }
        System.out.printf("SJF %.1f %.1f %.1f %n", return_time/number_processes, response_time/number_processes,
                waiting_time/number_processes);
    }
}
