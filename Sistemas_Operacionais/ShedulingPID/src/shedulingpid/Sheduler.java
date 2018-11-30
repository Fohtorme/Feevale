/*
 * Desenvolvido por Whale(1)
 */
package shedulingpid;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonas
 */
public class Sheduler {

    private int time;
    private final List<Process> processes;
    private int runningProcess;
    private int quantum;
    private int quantumRemainingTime;
    private ShedulerType type;

    Sheduler() {
        this.time = 0;
        this.processes = new ArrayList<>();
        this.runningProcess = -1;
        this.quantumRemainingTime = 0;
    }

    public void runProcess(ShedulerType type) {
        this.time++;
        this.type = type;

        switch (this.type) {
            case NOT_PREEMPTIVE:
                notPreemptive();
                break;
            case PREEMPTIVE_FOR_PRIORITY:
                preemptiveForPriority();
                break;
            case PREEMPTIVE_FOR_PRIORITY_AND_TIME:
                preemptiveForPriorityAndTime();
                break;
        }

    }

    private void notPreemptive() {
        this.quantumRemainingTime = 0;
        // Run the first process awaiting
        for (int i = 0; i < processes.size(); i++) {
            if (!processes.get(i).isFinished()) {
                runProcess(i);
                break;
            }
        }
    }

    private void preemptiveForPriority() {
        // Find the high priority element
        int priority = -1;
        int elementIndex = -1;
        for (int i = 0; i < processes.size(); i++) {
            // Ignore finished processes
            if (processes.get(i).isFinished()) {
                continue;
            }
            // If find a greater prioriry element
            if (processes.get(i).getPriority() > priority) {
                priority = processes.get(i).getPriority();
                elementIndex = i;
            }
        }
        // If element was not finded
        if (elementIndex == -1) {
            return;
        }
        // Run the high priority element
        runProcess(elementIndex);
    }

    private void preemptiveForPriorityAndTime() {
        // Find the high priority element
        int priority = -1;
        int elementIndex = -1;
        for (int i = 0; i < processes.size(); i++) {
            // Ignore finished processes
            if (processes.get(i).isFinished()) {
                continue;
            }
            // If the process time is not over
            if (quantumRemainingTime != 0) {
                // If it's the process running
                if (processes.get(i).getId() == this.runningProcess) {
                    elementIndex = i;
                }
                continue;
            }
            // If find a greater prioriry element
            if (processes.get(i).getPriority() > priority) {
                priority = processes.get(i).getPriority();
                elementIndex = i;
            }
            // If it's not the same priority
            if (processes.get(i).getPriority() != priority) {
                continue;
            }
            // If it is not the next process
            if (!(processes.get(i).getId() > this.runningProcess)) {
                continue;
            }
            // If already had find a next element
            if (processes.get(elementIndex).getId() > this.runningProcess) {
                // If the element finded is lesser than the current element
                if (processes.get(i).getId() < processes.get(elementIndex).getId()) {
                    elementIndex = i;
                }
            } else {
                elementIndex = i;
            }
        }
        // If element was not finded
        if (elementIndex == -1) {
            this.quantumRemainingTime = 0;
            return;
        }
        // If the element has been swapped
        if (processes.get(elementIndex).getId() != this.runningProcess) {
            this.quantumRemainingTime = quantum - 1;
        } else {
            this.quantumRemainingTime--;
        }
        // Run the high priority element
        runProcess(elementIndex);
        // If the process is over
        if(processes.get(elementIndex).isFinished()){
            this.quantumRemainingTime = 0;
        }
        
    }

    private void runProcess(int i) {
        processes.get(i).runProcess(time);
        this.runningProcess = processes.get(i).getId();
    }

    public int getTime() {
        return time;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public int getQuantumRemainingTime() {
        if (this.type != ShedulerType.PREEMPTIVE_FOR_PRIORITY_AND_TIME) {
            return 0;
        }
        for (int i = 0; i < processes.size(); i++) {
            if (!processes.get(i).isFinished()) {
                return quantumRemainingTime + 1;
            }
        }
        return 0;
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public void addProcess(int priority, int totalTime) {
        processes.add(new Process(priority, totalTime, this.getTime()));
    }

    public float getMediumTime() {
        int sumOfLeadTime = 0;
        int processQuantity = 0;
        for (int i = 0; i < processes.size(); i++) {
            if (processes.get(i).isFinished()) {
                sumOfLeadTime += processes.get(i).getLeadTime(time);
                processQuantity++;
            }
        }
        if(processQuantity == 0) return 0;
        return (float) sumOfLeadTime / processQuantity;
    }

}
