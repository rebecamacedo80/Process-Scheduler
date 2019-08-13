import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        String pathFile = "/home/rebeca/IdeaProjects/Process_scheduler/src/input.txt";
        //String pathFile = "input.txt";

        BufferedReader br = new BufferedReader(new FileReader(pathFile));

        ArrayList<Process> processes = new ArrayList<>();
        int time_coming, time_duration;
        String line;

        int n = 0;
        while(true){
            n++;
            String name = Integer.toString(n);
            line = br.readLine();

            if(line == null) break;

            String[] line_split = line.split(" ");

            time_coming = Integer.parseInt(line_split[0]);
            time_duration = Integer.parseInt(line_split[1]);

            processes.add(new Process(time_coming, time_duration, name));

        }

        processes = sortProcess(processes);

        //chama execução FCFS
        FCFS fcfs = new FCFS(processes);
        fcfs.run();

        //chamar execução SJF
        SJF sjf = new SJF(processes);
        sjf.run();

        //chamar execução RR
        RoundRobin rr = new RoundRobin(processes, 2);
        rr.run();
    }

    public static ArrayList<Process> sortProcess(ArrayList<Process> proc){
        for(int i = 0; i < proc.size(); i++) {
            for (int j = 0; j < proc.size() - 1; j++) {
                if (proc.get(j).coming > proc.get(j + 1).coming) {
                    Process aux = proc.get(j);
                    proc.set(j, proc.get(j + 1));
                    proc.set(j + 1, aux);
                }
            }
        }
        return proc;
    }
}
