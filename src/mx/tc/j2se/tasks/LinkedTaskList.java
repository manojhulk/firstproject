package mx.tc.j2se.tasks;

public class LinkedTaskList extends AbstractTaskList{
    private int lenLink;
    public Node head=null;

    static class Node {
        Task value;

        // connect each node to next node
        Node next;

        Node(Task data) {
            value = data;
            next = null;
        }
    }

    public void add(Task data){
        Node newNode = new Node(data);
        lenLink++;
        if(head == null){
            head = newNode;
        }else {
            Node currentNode = head;
            while(currentNode.next != null){
                currentNode = currentNode.next;
            }
            currentNode.next=newNode;
        }
    }
    public boolean remove (Task ob){
        Node node = head;
        int cost=1;
        if(node.value==ob){
            head=node.next;
            lenLink--;
            return true;
        }else{
            while(node.next.value != ob){
                node = node.next;
                cost++;
                if(cost>=lenLink){
                    return false;
                }
            }
            node.next=node.next.next;
            lenLink--;
            return true;
        }
    }
    public int size(){
        return this.lenLink;
    }
    public void display(){
        if(head != null){
            Node currentNode = head;
            while(currentNode!= null){
                System.out.println(currentNode.value);
                currentNode = currentNode.next;
            }
        }
        else {
            throw new IndexOutOfBoundsException("Empty list");
        }
    }
    /*
    * @index refers to the position of link
    * Here LinkedTaskList indexing starts from 1
    *
    */
    public Task getTask(int index){
        int point=1;
        if (index < 1 || index >lenLink) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size "
                    + lenLink);
        }
        if(head != null){
            Node currentNode = head;
            while(point!=index){
                currentNode = currentNode.next;
                point++;
            }
            return currentNode.value;
        }
        return null;
    }
    public LinkedTaskList incoming(int from, int to){
        LinkedTaskList subsets= new LinkedTaskList();
        if(head!=null){
            Node current=head;
            while(current!= null){
                if(current.value.getStartTime()>=from && current.value.getStartTime()<=to){
                    subsets.add(current.value);
                }
                current = current.next;
            }
        }
        return subsets;
    }
}
