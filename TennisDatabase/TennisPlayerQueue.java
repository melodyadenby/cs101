//cs102 Melody Denby

package TennisDatabase;

// Interface (package-private) providing the specifications for the TennisPlayerQueue class.
class TennisPlayerQueue implements TennisPlayerQueueInterface{ 

   private TennisPlayer[] playerArr; // Internal circular array (resizable).
   private int sizePhysical; // Maximum number of items the array can store.
   private int sizeLogical; // Current number of items the array is storing.
   private int front; // Index of the item at the front of the queue.
   private int back; // Index of the item at the back of the queue.
   
   // Constructor.
   public TennisPlayerQueue() {
      this.sizePhysical = 8;
      this.sizeLogical = 0;
      this.front = 0;
      this.back = -1;
      //@SuppressWarnings("unchecked")
      this.playerArr = new TennisPlayer[ this.sizePhysical ];
   }
   
   // Desc.: Check if the queue is empty.
   // Output: True or false.
   public boolean isEmpty() { return this.sizeLogical == 0; }
   
   // Desc.: Insert a tennis player at the back of this queue.
   // Input: A tennis player.
   // Output: Throws a checked (critical) exception if the insertion fails.
   public void enqueue( TennisPlayer p ) throws TennisDatabaseException{
      // Check if queue is full.
      if( this.sizeLogical == this.sizePhysical ) {
         // Resize the circular array into a bigger circular array.
         int newSizePhysical = this.sizePhysical * 2;
         TennisPlayer[] newArray = new TennisPlayer[ newSizePhysical ];
         int newFront = 0;
         int currIndexOldArray = this.front;
         int currIndexNewArray = newFront;
         for( int i = 1; i <= this.sizeLogical; i++ ) {
            newArray[ currIndexNewArray ] = this.playerArr[ currIndexOldArray ];
            currIndexNewArray = ( currIndexNewArray + 1 ) % newSizePhysical;
            currIndexOldArray = ( currIndexOldArray + 1 ) % this.sizePhysical;
         }
         this.playerArr = newArray;
         this.sizePhysical = newSizePhysical;
         this.front = newFront;
         this.back = currIndexNewArray;
      }
      // Insert player in array at back.
      this.back = ( this.back + 1 ) % this.sizePhysical; // Update index of item at back.
      this.playerArr[ this.back ] = p;
      this.sizeLogical++;
   }
   
   // Desc.: Extract (return and remove) a tennis player from the front of this queue.
   // Output: Throws a checked (critical) exception if the extraction fails.
   public TennisPlayer dequeue() throws TennisDatabaseException{
      if( !isEmpty() ){
         TennisPlayer playerAtFront = this.playerArr[ this.front ]; // Store apart item at front of queue.
         this.playerArr[ this.front ] = null; // Totally unnecessary.
         this.front = ( this.front + 1 ) % this.sizePhysical; // Update index of item at front.
         this.sizeLogical--;
         return playerAtFront; // Return item stored apart.
      } else{
         throw new TennisDatabaseException("Queue is empty!");
      }
   }
   
   // Desc.: Return (without removing) the tennis player at the front of this queue.
   // Output: Throws a checked (critical) exception if the queue is empty.
   public TennisPlayer peek() throws TennisDatabaseException{
      // Check if this queue is empty.
      if( !isEmpty() ) { 
         // This queue is not empty, return front item.
         return this.playerArr[ this.front ];
      }
      else { 
         // This queue is empty, peek impossible, throw exception.
         throw new TennisDatabaseException( "TennisDatabaseException on peek: queue empty!" );
      }
   }     
}


