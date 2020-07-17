package driver2;

public class PriorityQueue {
    private PriorityCustomer[] heap;
    private int size;
    
    //makes a new PriorityQueue with 50 PriorityCustomers
    public PriorityQueue(){
        //can hold 60 PriorityCustomers but isn't filled yet
        heap = new PriorityCustomer[60];
        size = 0;
    }
    
    //makes a new PriorityQueue with s PriorityCustomers
    public PriorityQueue(int s){
        heap = new PriorityCustomer[s];
        size = 0;
    }
    
    //returns the size of the PriorityQueue
    public int getSize(){
        return size;
    }
    
    //returns the priority number of PriorityCustomer at a specific index
    public int getPriority(int i){
        return heap[i].getPriority();
    }
    
    //adds a PriorityCustomer to PriorityQueue
    public void add(PriorityCustomer c){
        //make sure the heap isn't full
        if(size+2>heap.length){
            System.out.println("The heap is full");
            return;
        }
        
        //Used while testing: prints out the priority number before it is sorted
        //System.out.println("Priority of new customer: " + c.getPriority());
        
        //increase the size
        size++;
        
        //add new object to the next open position in the heap
        heap[size] = c;
        
        //create a variable to keep track of where our object is in the heap
        int index = size;
        
        //continue to compare the object to its parents 
        while(index>1){
            //grab parent's index
            int parentIndex = index/2;
            
            //making sure it doesn't effect the first customer
            if(parentIndex>1){
                //compare object to its parent to see if we need to swap
                if(heap[index].getPriority()>heap[parentIndex].getPriority()){
                    //swap objects
                    PriorityCustomer temporary = heap[index];
                    heap[index] = heap[parentIndex];
                    heap[parentIndex] = temporary;
                
                    //update index to parent's index after swap
                    index = parentIndex;
                }
                else{
                    //parent value is larger so no swap needed; break out of loop
                    break;
                }
            }
            else{
                //parent is the first customer; break out of loop
                break;
            }
        }
    }
    
    public PriorityCustomer remove(){
        //make sure the heap isn't empty
        if(size == 0){
            System.out.println("The heap is already empty");
            return null;
        }
        
        //store temporary reference to root object so we can return it at the end
        PriorityCustomer temporary = heap[1];
        
        //move object in the last position to the root
        heap[1] = heap[size];
        heap[size] = null;
        size--;
        
        //store index of the object we moved to the root
        int index = 1;
        
        //continue to compare index to its children as long as there are children
        while(index <= size/2){
            //store index and values of children
            int leftChildIndex = index * 2;
            int rightChildIndex = leftChildIndex + 1;
            int leftChildValue = heap[leftChildIndex].getPriority();
            int rightChildValue = Integer.MIN_VALUE;
            
            //if there is a right child get its value
            if(rightChildIndex <= size){
                rightChildValue = heap[rightChildIndex].getPriority();
            }
            
            //determine the larger of the two children
            int largerValue;
            int largerIndex;
            
            if(rightChildValue > leftChildValue){
                largerValue = rightChildValue;
                largerIndex = rightChildIndex;
            }
            else{
                largerValue = leftChildValue;
                largerIndex = leftChildIndex;
            }
            
            //determine if a swap should be made with the parent and larger child
            if(heap[index].getPriority() < largerValue){
                //swap
                PriorityCustomer swap = heap[index];
                heap[index] = heap[largerIndex];
                heap[largerIndex] = swap;
                
                //update the index since we moved it to a child position
                index = largerIndex;
            }
            else{
                //parent value is larger so no swap needed; break out of loop
                break;
            }      
        }
        //return the original root
        return temporary;
    }
    
    //returns the service time of the first customer
    public int getFirstServiceTime(){
        return(heap[1].getServiceTime());
    }
    
    //decreases the service time of the first customer by one
    public void decreaseFirstServiceTime() {
        heap[1].decServiceTime();
    }
}
