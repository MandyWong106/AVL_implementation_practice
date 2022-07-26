import java.time.temporal.TemporalUnit;

public class Empty<E extends Comparable<E>> implements AVL_Tree<E>{

    // Create a nothing to do constructor.
    public Empty(){};
    private int height = 0;


    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public E smallest() {
        throw new RuntimeException("Attempted to find smallest node in an Empty Tree");
    }

    @Override
    public E largest() {
        throw new RuntimeException("Attempted to find largest node in an empty Tree");
    }

    @Override
    public boolean smaller(E e) {
        return true;
    }

    @Override
    public boolean bigger(E e) {
        return true;
    }

    @Override
    public boolean has(E e) {
        return false;
    }

    @Override
    public AVL_Tree<E> find(E e) {
        return null;
    }

    @Override
    public AVL_Tree<E> insert(E e) {
        return new Fork<E>(e, new Empty<E>(), new Empty<E>());
    }

    // why not throw an error but return this.
    @Override
    public AVL_Tree<E> delete(E e) {
        return this;
    }

    @Override
    public AVL_Tree<E> deleteSmallest() {
        throw new RuntimeException("Attempted to delete the smallest node");
    }

    @Override
    public AVL_Tree<E> deleteLargest() {
        throw new RuntimeException("Attempted to delete the largest node");
    }

    @Override
    public String toString(){
        return ("Empty");
    }

    @Override
    public String fancyToString() {
        return ("");
    }

    @Override
    public String fancyToString(int d) {
        return fancyToString();
    }

    public String fancyToStringVertical() {
        return ("");
    }

    @Override
    public AVL_Tree<E> getLeft() {
        throw new RuntimeException("Attempted to get the left subtree from an empty tree");
    }

    @Override
    public AVL_Tree<E> getRight() {
        throw new RuntimeException("Attempted to get the right subtree from an empty tree");
    }

    @Override
    public int getNodeNum() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getLeaveNum() {
        return 0;
    }

    @Override
    public void setHeight() {
        height = 0;
    }

    @Override
    public void insertItself(E e) {
        throw new RuntimeException("Attempt to insertItself in an empty tree");
    }

    @Override
    public int getBalance(){return 0;}

    public void leftRotateSelf (){
        throw new RuntimeException("Attempt to rotate an empty tree");
    }
    public void rightRotateSelf (){
        throw new RuntimeException("Attempt to rotate an empty tree");
    }
}
