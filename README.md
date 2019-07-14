# scheduling
Pretending CPU Scheduling Algorithms FCFS, RR, SRTF, SJF in Java

FCFS = First Come First Served
RR = Round Robin
SRTF = Shortest Remaining Time First
SJF = Shortest Job First

To Run this Application choose and run just the class "SchedulingAusfuehren". The prints are in German and all values are pretend milliseconds (ms). After running follow these steps:

Step 1: Choose the number for the Scheduling Algorithm you like to run.
Step 2: Choose the number of the turnaround time.

  if (RR || SJF) {
    Just enter the CPU-Bursttime as often as you choose turnaround time before;
  } else if (FCFS || SRTF) {
    Enter the Arrivaltime additional to the CPU-Bursttime for each Process;
  }
 
If you choose RR you have to enter the Time Quantum as well. 
