import java.util.ArrayList;

public class FCFS{

    public float response_time, return_time, waiting_time, time, number_processes;
    ArrayList<Process> proc;

    public FCFS(ArrayList proc){
        this.proc = (ArrayList<Process>) proc.clone();
        response_time = 0;
        return_time = 0;
        waiting_time = 0;
        number_processes = proc.size();
    }

        public void run(){

            time = proc.get(0).coming;
            for(int i = 0; i < proc.size(); i++){
                //tempo de resposta do processo i
                response_time += time -proc.get(i).coming;
                //System.out.println("t. resposta: " + response_time);

                //tempo de retorno do processo i
                return_time += (time + proc.get(i).duration) - proc.get(i).coming;

                //tempo de espera
                waiting_time += time - proc.get(i).coming;

                //atualizando time
                time += proc.get(i).duration;
                //System.out.println("time: " + time);
            }

            System.out.printf("FCFS %.1f %.1f %.1f %n", return_time/number_processes, response_time/number_processes,
                    waiting_time/number_processes);

        }

}
