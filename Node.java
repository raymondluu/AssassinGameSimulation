/* Raymond Luu
 * John Mayor
 * TCSS143
 */

public class Node {

    String player;
    String killer;
    Node next;
    
    public Node(String player, String killer, Node next) {
        this.player = player;
        this.killer = killer;
        this.next = next;
    }
}