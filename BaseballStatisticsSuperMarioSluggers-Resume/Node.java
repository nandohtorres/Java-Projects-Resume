// next pointer, design class to hold stats, dont hold stats calculated from others
//use functions to calculate when needed to prevent "stale" data
public class Node {
    private Main.Player player;
    private Node next;
    // inserts player into current node then makes node next null
    public Node(Main.Player player){
        this.player = player;
        next = null;
    }
    // getter for player
    public Main.Player getPlayer(){
        return player;
    }
    // getter and setter for next
    public Node getNext() {
        return next;
    }
    public void setNext(Node next) {
        this.next = next;
    }
    //
    public String toString(){
        return player.toString();
    }

}
