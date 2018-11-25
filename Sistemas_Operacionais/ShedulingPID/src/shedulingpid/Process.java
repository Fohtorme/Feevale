/*
 * Desenvolvido por Whale(1)
 */
package shedulingpid;

/**
 *
 * @author jonas
 */
public class Process {

    private static int id_count = 0;
    private int id;
    private int priority;
    private int totalTime;
    private int remainingTime;
    private int startTime;
    private int endTime;
    private int timeLastExecution;

    // Block default constructor
    private Process() {
    }

    public Process(int priority, int totalTime, int startTime) {
        this.id = id_count++;
        this.priority = priority;
        this.totalTime = totalTime;
        this.remainingTime = totalTime;
        this.startTime = startTime;
        this.endTime = 0;
        this.timeLastExecution = 0;
    }

    public int getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public boolean isFinished() {
        return this.remainingTime == 0;
    }
    
    public int getLeadTime(int time){
        if(isFinished()){
            return (this.endTime - this.startTime) - this.totalTime;
        } else {
            return (time - this.startTime) - (this.totalTime - this.remainingTime);
        }
    }
    
    public String getStatus(int time){
        if(time == this.timeLastExecution) return "Running";
        if(isFinished()) return "Finished";
        return "Stopped";
    }

    @Override
    public String toString() {
        return "Process{" + "id=" + id + 
             ", priority=" + priority + 
             ", totalTime=" + totalTime + 
             ", remainingTime=" + remainingTime + 
             ", startTime=" + startTime + 
             ", endTime=" + endTime + 
             '}';
    }

    public void runProcess(int time) {
        if (this.isFinished()) {
            throw new RuntimeException("Tried to execute a finished process!");
        }
        this.remainingTime--;
        this.timeLastExecution = time;
        if (this.remainingTime == 0) {
            this.endTime = time;
        }
    }
}
