package driver2;

import java.util.Random;

public class PriorityCustomer {
    private int serviceTime;
    private int priority;
    
    //Constructor
    public PriorityCustomer(){
        serviceTime = new Random().nextInt(5)+1;
        priority = new Random().nextInt(5)+1;
    }
    
    public int getPriority(){
        return priority;
    }
    
    public int getServiceTime(){
        return serviceTime;
    }
    
    public void decServiceTime(){
        serviceTime--;
    }
    
    
}
