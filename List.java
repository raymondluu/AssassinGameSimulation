/* Raymond Luu
 * John Mayor
 * TCSS143
 */

public class List {

    Node front;


    
    public void addToBack(String newData, String killer) {
        if (front == null) {
            front = new Node(newData, killer, front);
        } else {
            Node curr = front;
            while (curr.next != null) {
               curr = curr.next;
            }
            curr.next =  new Node(newData, killer, null);
        }
    }
    
    public void addToFront(String newData, String killer) {
        front = new Node(newData, killer, front);
    }
    
    public String get(int index) {
        Node curr = front;
        for(int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.player;
    }
    
    public String toString() { 
        if (front == null) 
            return "{}";

        String str = "{" + front.player;
        Node curr = front.next;
        while (curr != null) {
            str = str + ", "+curr.player;
            curr = curr.next;
        }
        str = str + "}";
        return str;
    }


}
