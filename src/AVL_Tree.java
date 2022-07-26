public interface AVL_Tree<E extends Comparable<E>>{

// for this tree, I hope to be able to insert same number to the tree.

    public boolean isEmpty();
    public E       smallest();   // Returns smallest node (=left-most node).
    public E       largest();    // Returns largest node (=right-most node).
    // It is better to return the element instead of the tree....?
    public boolean smaller(E e); // Checks whether all nodes smaller than e. Used to preserve order when fork
    public boolean bigger(E e);  // Checks whether all nodes bigger than e.
    public boolean has(E e);     // Checks whether e occurs in "this". (*Shall be isIn instead)
    //public void has(E e); Oh dear, you want to check if have and not given a result?
    public AVL_Tree<E> find(E e);    // Returns subtree of "this" with e at root (or null).
    public AVL_Tree<E> insert(E e);  // Returns a copy of "this" with e inserted.
    // the advantage of return the tree is can preserve the original tree, you can use
    // t1 = t1.insert to avoid having more and more tree
    public AVL_Tree<E> delete(E e);  // Returns a copy of "this" with e deleted.
    public AVL_Tree<E> deleteSmallest();// Return new tree with smallest element deleted.
    public AVL_Tree<E> deleteLargest();// Return new tree with largest element deleted.
    public String  fancyToString();// 2-dimensional, rotated tree printing.
    public String  fancyToString(int d);// Starting at a given position d.
    public String  fancyToStringVertical();// Starting at a given position d.

    public AVL_Tree<E> getLeft(); // Returns the left subtree  * can have more method so that to avoid
    // the error when get left from an empty tree, but not in the lecture
    public AVL_Tree<E> getRight(); // Returns the right subtree
    public int getNodeNum(); // just as practice
    public int getHeight(); // just as practice
    public int getLeaveNum(); // just as practice
    public void setHeight();
    public void insertItself(E e);
    public int getBalance();
    public void leftRotateSelf ();
    public void rightRotateSelf ();



    /* incorrect thinking. you cannot directly return the smallest tree as there is empty attach there.
    public AVL_Tree<E> smallest();
    public AVL_Tree<E> biggest();
     */

}
