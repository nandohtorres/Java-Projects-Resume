import java.util.Iterator;

// members: head pointer, recursive print function, a sort function of my design
public class LinkList implements Iterable <Main.Player>{
    private Node head;
    private Node tail;

    public LinkList() {
        head = null;
        tail = null;
    }

    public void add(Main.Player player) {
        Node node = new Node(player);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
    }

    public void print() {
        print(head);
        System.out.println();
    }

    private void print(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node);
        print(node.getNext());
    }

    public void alphabetize() {
        Node curr;
        Node holder;
        Node holder2;
        boolean done = false;

        while (!done) {
            done = true;
            if (head.getNext() != null){
                if(head.getPlayer().getName().compareTo(head.getNext().getPlayer().getName()) > 0  ){
                    holder = head.getNext();
                    head.setNext(head.getNext().getNext());
                    holder.setNext(head);
                    head = holder;
                    done = false;
                }
            }else {
                break;
            }
            curr = head;
            while (curr.getNext().getNext() != null) {
                holder = curr.getNext();
                holder2 = curr.getNext().getNext();
                if (holder.getPlayer().getName().compareTo(holder2.getPlayer().getName()) > 0 ) {
                    curr.setNext(holder2);
                    holder.setNext(holder2.getNext());
                    holder2.setNext(holder);
                    done = false;
                }
                curr = curr.getNext();
            }
            tail = curr.getNext();
        }
    }

    @Override
    public Iterator<Main.Player> iterator() {
        return new PlayerIterator(head);
    }
    class PlayerIterator implements Iterator<Main.Player>{
        private Node curr;
        public PlayerIterator(Node head){
            curr = head;
        }

        @Override
        public boolean hasNext() {
            return curr !=null;
        }

        @Override
        public Main.Player next() {
            Main.Player player = curr.getPlayer();
            curr = curr.getNext();
            return player;
        }
    }
}
