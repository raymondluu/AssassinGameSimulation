
public class AssassinManager {
    private List killRing = new List();
    private List graveyard = new List();

    //Pre: if names.length is less than equal to 0 then throw IllegalArgumentException
    //Post: Initialize Assassin Manager 
    public AssassinManager(final String[] names) {
        if(names.equals(null)) {
            throw new NullPointerException();
        } else if(names.length <= 0) {
            throw new IllegalArgumentException();
        } else {
            //add array of names into List
            for(int i = 0; i < names.length; i++) {
                killRing.addToBack(names[i], "");
            }

            //connect the list to make it circular
            Node current = killRing.front;
            while(current.next != null) {
                current = current.next;
            }
            current.next = killRing.front;
        }
    }

    // Post: Print out the killRing
    public void printKillRing() {
        Node current = killRing.front;
        while(current.next != null && current.next != killRing.front) {
            System.out.println("\t" + current.player + " is targeting " + current.next.player);
            current = current.next;
        }
        System.out.println("\t" + current.player + " is targeting " + current.next.player);
    }

    //Post: Print out the graveyard
    public void printGraveyard() {
        Node current = graveyard.front;
        while(current != null) {
            System.out.println("\t" + current.player + " was killed by " + current.killer);
            current = current.next;
        }
    }

    //Post: return true if killRing contains the name
    public boolean killRingContains(final String name) {
        if(name.equals(null)) {
            throw new NullPointerException();
        } else {
            Node current = killRing.front;
            while(current.next != null && current.next != killRing.front) {
                if(!current.player.equals(name)) {
                    current = current.next;
                } else {
                    return true;
                }
            }
            if(!current.player.equals(name)) {
                current = current.next;
            } else {
                return true;
            }
        }
        return false;
    }

    //Post: returns true if name is in graveyard
    public boolean graveyardContains(final String name) {
        if(name.equals(null)) {
            throw new NullPointerException();
        } else {
            Node current = graveyard.front;
            if(current == null) {
                return false;
            }
            while(current.next != null && current.next != graveyard.front) {
                if(!current.player.equals(name)) {
                    current = current.next;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    //Post: returns true when there is only one person left in killRing
    public boolean isGameOver() {
        if(killRing.front.next == killRing.front) {
            return true;
        }
        return false;
    }

    //Post: returns name of winner
    public String getWinner() {
        if(!isGameOver()) {
            throw new IllegalStateException();
        }
        return killRing.front.player;
    }

    //Post: adds target to graveyard and removes target from killRing
    public void kill(final String name) {
        if(name == null) {
            throw new NullPointerException();
        } else if(!killRingContains(name)) {
            throw new IllegalArgumentException();
        } else if(isGameOver()) {
            throw new IllegalStateException();
        } else {
            Node curr = killRing.front;
            Node prev = killRing.front;
            if(curr.player.equals(name)) {
                while(prev.next != null && prev.next != killRing.front) {
                    prev = prev.next;
                }
                graveyard.addToFront(name, prev.player);
                killRing.front = curr.next;
                prev.next = curr.next;
            } else {
                while(prev.next != null && prev.next != killRing.front) {
                    if(prev.next.player.equals(name)) {
                        graveyard.addToFront(name, curr.player);
                        prev.next = prev.next.next;
                    }
                    prev = prev.next;

                }
                while(curr.next != null && curr.next != killRing.front) {
                    if(curr.player.equals(name)) {
                        graveyard.addToFront(name, prev.player);
                        prev.next = curr.next;

                    }
                    prev = curr;
                    curr = curr.next; 
                }
            }
        }
    }
}
