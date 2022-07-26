public class Fork<E extends Comparable<E>> implements AVL_Tree<E> {

    private E root;
    private AVL_Tree<E> left, right;
    private int height;

    public Fork(E e, AVL_Tree<E> l, AVL_Tree<E> r) {

        assert (l != null);
        assert (r != null);

        assert (l.smaller(e));//: "l is not smaller than root" ;
        assert (r.bigger(e));
        root = e;
        left = l;
        right = r;
        setHeight();
    }

    public Fork(Fork<E> e){
        root = e.root;
        left = e.left;
        right = e.right;
        setHeight();
    }

    public void updateFork(Fork<E> e){

        root = e.root;
        left = e.left;
        right = e.right;
        setHeight();

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public E smallest() {
        if (left.isEmpty())
            return root;
        return left.smallest();
    }

    @Override
    public E largest() {
        if (right.isEmpty())
            return root;
        return right.largest();
    }

    @Override
    // as in each insert, left must be smaller than root, so only need right.smaller (e)
    public boolean smaller(E e) {
        return root.compareTo(e) < 0 && right.smaller(e);
    }

    @Override
    // as in each insert, right must be bigger than root, so only need left.bigger (e)
    public boolean bigger(E e) {
        return root.compareTo(e) > 0 && left.bigger(e);
    }

    @Override
    public boolean has(E e) {
        return e.compareTo(root) == 0 || (e.compareTo(root) < 0 ? left.has(e) : right.has(e));
    }

    @Override
    public AVL_Tree<E> find(E e) {
        if (e.compareTo(root) == 0)
            return this;
        else if (e.compareTo(root) < 0)
            return left.find(e);
        else
            return right.find(e);
    }

    public void setLeft(AVL_Tree<E> aTree){
        this.left = aTree;
    }

    public void setRight(AVL_Tree<E> aTree){
        this.right = aTree;
    }


    @Override
    public AVL_Tree<E> insert(E e) {
        //Part I - insert
        Fork<E> temp;
        if (e.compareTo(root) == 0)
            return this;
        else if (e.compareTo(root) < 0){
            temp = new Fork<E>(root, left.insert(e), right);
            left.setHeight();
        }
        else{
            temp = new Fork<E>(root, left, right.insert(e));
            right.setHeight();
        }
        //Part II - re-balance
        //This will work from the bottom to the top once a node really added to the bottom of the current tree
        setHeight();
        if(temp.getBalance()>1){
            // this is the case of LL
            if (temp.getLeft().getBalance()==1){
                temp = rightRotate(temp);
            }else{
                // this is the case of LR
                temp.setLeft(leftRotate((Fork<E>) temp.getLeft()));
                temp = rightRotate(temp);
            }
        }else if (temp.getBalance()<-1){
            //This is the case of RL
            if (temp.getRight().getBalance()==1){
                temp.setRight(rightRotate((Fork<E>) temp.getRight()));
                temp = leftRotate(temp);
            }else{
                //This is the case of RR
                temp = leftRotate(temp);
            }
        }

        return temp;

    }


    public void insertItself(E e) {
        if (e.compareTo(root) < 0) {
            if (left.isEmpty())
                left = new Fork<E>(e, new Empty<>(), new Empty<>());
            else left.insertItself(e);
            left.setHeight();
            setHeight();
        } else if (e.compareTo(root) > 0) {
            if (right.isEmpty())
                right = new Fork<E>(e, new Empty<>(), new Empty<>());
            else right.insertItself(e);
            right.setHeight();
            setHeight();
        }
        if(this.getBalance()>1){
            if (this.getLeft().getBalance() != 1) {
                this.left.leftRotateSelf();
            }
            this.rightRotateSelf();
        }else if (this.getBalance()<-1){
            if (this.getRight().getBalance()==1){
                this.right.rightRotateSelf();
            }
            this.leftRotateSelf();
        }
    }

    @Override
    public AVL_Tree<E> delete(E e) {
       return delete2(e);
    }

    public AVL_Tree<E> delete1(E e) {
        // Delete without re-balance, this is an example for normal tree for reference
        if (e.compareTo(root) == 0) {
            if (left.isEmpty()) return right;
            else if (right.isEmpty()) return left;
            else {
                return new Fork<E>(right.smallest(), left, right.deleteSmallest());
            }
        } else if (e.compareTo(root) < 0) {
            return new Fork<E>(root, left.delete(e), right);
            // if just return left.delete(e), it would return a portion of the tree only.
        } else {
            return new Fork<E>(root, left, right.delete(e));
        }
    }

    public AVL_Tree<E> delete2(E e) {
        // Delete with re-balance
        // Part I - delete
        Fork<E> temp;
        if (e.compareTo(root) == 0) {
            if (left.isEmpty()) return right;
            else if (right.isEmpty()) return left;
            else {

                temp = new Fork<E>(right.smallest(), left, right.delete(right.smallest()));
            }
        } else if (e.compareTo(root) < 0) {
            temp = new Fork<E>(root, left.delete(e), right);
            // if just return left.delete(e), it would return a portion of the tree only.
        } else {
            temp = new Fork<E>(root, left, right.delete(e));
        }
        // Part II - re-balance
        temp.setHeight();
        if(temp.getBalance()>1){
            if (temp.getLeft().getBalance() != 1) {
                temp.setLeft(leftRotate((Fork<E>) temp.getLeft()));
            }
            temp = rightRotate(temp);
        }else if (temp.getBalance()<-1){
            if (temp.getRight().getBalance()==1){
                temp.setRight(rightRotate((Fork<E>) temp.getRight()));
            }
            temp = leftRotate(temp);
        }
        return temp;
    }

    @Override
    public AVL_Tree<E> deleteSmallest() {
        if (left.isEmpty()) {
            //once left is empty, the current node is the smallest, either promote the right tree
            //or return the empty to replace the leaf node
            return right;
        } // if there is still left tree, then there is a smaller node
        else return new Fork<E>(root, left.deleteSmallest(), right);
    }

    @Override
    public AVL_Tree<E> deleteLargest() {
        if (right.isEmpty()) {
            //once right is empty, the current node is the largest, either promote the left tree
            //or return the empty to remove the leaf node
            return left;
        } // if there is still right tree, then there is a larger node
        else return new Fork<E>(root, left, right.deleteLargest());
    }

    @Override
    public String toString() {
        return "Fork(" + root + ", " + left.toString() + ", " + right.toString() + ")";
    }

    @Override
    public String fancyToString() {
        return fancyToString(0);
    }

    @Override
    public String fancyToString(int d) {
        // d is the distance before root
        //step is the distance between each level
        int step = 4;
        StringBuilder space = new StringBuilder();
        for (int i = 0; i < d; i++) space.append(" ");
        return right.fancyToString(step + d) + space.toString() + root + "\n" + left.fancyToString(step + d);
    }

    public String fancyToStringVertical() {

//        for (int i = 0; i<)

        int step =4, d= this.height*step;
        StringBuilder space = new StringBuilder();
        for (int i = 0; i < d; i++) space.append(" ");
        return space.toString() + root + "\n" + left.fancyToStringVertical() +"\b"+ space.toString()+right.fancyToStringVertical();
    }

    @Override
    public AVL_Tree<E> getLeft() {
        return left;
    }

    @Override
    public AVL_Tree<E> getRight() {
        return right;
    }

    @Override
    public int getNodeNum() {
        return 1 + left.getNodeNum() + right.getNodeNum();
    }

    public void setHeight() {
        height = 1 + Math.max(left.getHeight(),right.getHeight());
    }

    @Override
    public int getHeight() {
        return height;
    }

    //get leave num
    @Override
    public int getLeaveNum() {
        if (left.isEmpty() && right.isEmpty())
            return 1;
        else return left.getLeaveNum() + right.getLeaveNum();
    }

    @Override
    public int getBalance(){
        return left.getHeight()-right.getHeight();
    }

    public Fork<E> rightRotate(Fork<E> fork){
        Fork<E> x = fork;
        Fork<E> y = (Fork<E>) fork.getLeft();
        AVL_Tree<E> b = fork.getLeft().getRight();
        y.right = x;
        x.left = b;
        x.setHeight();
        y.setHeight();
        return y;
    }

    public Fork<E> leftRotate(Fork<E> fork){
        Fork<E> x = fork;
        Fork<E> y = (Fork<E>) fork.getRight();
        AVL_Tree<E> b = fork.getRight().getLeft();
        y.left = x;
        x.right = b;
        x.setHeight();
        y.setHeight();
        return y;
    }

    public void rightRotateSelf (){
        Fork<E> x = new Fork<E> (this);
        Fork<E> y = new Fork<E> ((Fork<E>) this.getLeft());
        AVL_Tree<E> b = y.getRight();
        y.right = x;
        x.left = b;
        x.setHeight();
        y.setHeight();
        this.root = y.root;
        this.left = y.left;
        this.right = y.right;
    }

    public void leftRotateSelf (){
        Fork<E> x = new Fork<E> (this);
        Fork<E> y = new Fork<E> ((Fork<E>) x.getRight());
        AVL_Tree<E> b = y.getLeft();
        y.left = x;
        x.right = b;
        x.setHeight();
        y.setHeight();
        root = y.root;
        left = y.left;
        right = y.right;
    }

}
