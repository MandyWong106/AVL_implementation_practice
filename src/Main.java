public class Main {

    public static void main(String[] args) {
//        AVL_Tree<Integer> t = new Empty<>();
//        System.out.println(t);
//        System.out.println(t.fancyToString());
//        t = t.insert(30);
//        System.out.println(t);
//        System.out.println(t.fancyToString());
//        t = t.insert(40);
//        System.out.println(t);
//        System.out.println(t.fancyToString());
//        t = t.insert(25);
//        System.out.println(t);
//        System.out.println(t.fancyToString());
//        t = t.insert(35);
//        System.out.println(t);
//        System.out.println(t.fancyToString());



        AVL_Tree<Integer> t1 = new Fork(1, new Empty(), new Empty());
        AVL_Tree<Integer> t3 = new Fork(3, new Empty(), new Empty());
        AVL_Tree<Integer> t = new Fork(2, t1, t3);
        t = t.insert(10);
        t = t.insert(8);
        System.out.println(t.getHeight());
        System.out.println(t);
        System.out.println(t.fancyToString(4));
        System.out.println(t.fancyToStringVertical());
        System.out.println("reached");
        System.out.println(t.getHeight());

        t.insertItself(8);
        System.out.println(t.fancyToString());

        t = t.insert (12);
        System.out.println(t.fancyToString());
        t.insertItself(13);
        System.out.println(t);
        System.out.println(t.fancyToString());
        System.out.println(t.getHeight());
        t.insertItself(14);
        t.insertItself(15);
        System.out.println(t.fancyToString());
        t.insertItself(0);

        t=t.delete(2);
        System.out.println(t.fancyToString());
        System.out.println(t.getHeight());
        t = t.delete(8);
        System.out.println(t.fancyToString());
        System.out.println(t.getHeight());

    }
}
