package driver2;
import java.util.*;

public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PriorityQueue heap = new PriorityQueue();
        Random rand = new Random();
        int totalCustomers = 0;
        int maxLineLength = 0;
        int queueLength = 0;
        
        //for loop to iterate 60 times to represent 60 minutes
        for(int minutes = 60; minutes > 0; minutes-- ){         
            //generate random int betweek 1 and 4, giving a 25% chance a new customer arrives
            int newNum = rand.nextInt(4)+1;
            //if random number is 4, add Priority Customer to Priority Queue
            if(newNum == 4){
                //add new Priority Customer to our Priority Queue
                heap.add(new PriorityCustomer());
                //add one to our queue length
                queueLength++;
                //change max line length if this is the longest the queue has been
                if(queueLength>maxLineLength){
                    maxLineLength = queueLength;
                }
                //print out new customer added and new queue length
                System.out.println("New Priority Customer added! Priority Queue length is now " +queueLength);
            }
            //see if our line is empty or if we have someone to service
            if(heap.getSize()>0){
                //decrease their wait time by one
                heap.decreaseFirstServiceTime();
                //if they are done waiting, remove them from queue
                if(heap.getFirstServiceTime() == 0){
                    heap.remove();
                    //decrease queue length by 1
                    queueLength--;
                    System.out.println("Customer serviced and removed from the queue. Queue length is now: "+ queueLength);
                   //Used while testing: prints out the priority numbers in the Priority Queue
                    /*System.out.println("Priority queue is: ");
                    for(int j = 1; j<=heap.getSize(); j++){
                        System.out.println(heap.getPriority(j));
                    }
                    */
                    
                    //increase total customers by 1
                    totalCustomers++;
                }
            }
            //end of the 1 minute/1 iteration of loop
            System.out.println("-");
        }
        //end 60 minutes and prints out the final numbers
        System.out.println("Total number of customers serviced: "+ totalCustomers);
        System.out.println("Maximum line length during the simulation: " + maxLineLength);
    }  
}
