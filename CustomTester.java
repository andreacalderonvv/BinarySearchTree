/*
 * Name: Andrea Calderon
 * Email: a6calderon@ucsd.edu
 * PID: A17303613
 * Sources Used: ZyBooks, Write Up, Tutors
 */

// imports used
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * This class tests methods from the MyBST class
 */
public class CustomTester {
    MyBST<Integer, Integer> tree;
    MyBST<Integer, Integer> pine;

    @Before
    public void setup() {
        MyBST.MyBSTNode<Integer, Integer> root =
                new MyBST.MyBSTNode<>(4, 1, null);
        MyBST.MyBSTNode<Integer, Integer> two =
                new MyBST.MyBSTNode<>(2, 1, root);
        MyBST.MyBSTNode<Integer, Integer> six =
                new MyBST.MyBSTNode<>(6, 1, root);
        MyBST.MyBSTNode<Integer, Integer> one =
                new MyBST.MyBSTNode<>(1, 2, two);
        //MyBST.MyBSTNode<Integer, Integer> three =
                //new MyBST.MyBSTNode<>(3, 30, two);
        MyBST.MyBSTNode<Integer, Integer> five =
                new MyBST.MyBSTNode<>(5, 50, six);

        this.tree = new MyBST<>();
        this.tree.root = root;
        root.setLeft(two);
        root.setRight(six);
        two.setLeft(one);
        //two.setRight(three);
        six.setLeft(five);
        tree.size = 5;
    }

    /**
     * This method tests the successor method 
     */
    @Test
    public void Testsuccessor(){
        MyBST.MyBSTNode<Integer, Integer> treeRoot = tree.root;
        assertEquals(treeRoot,treeRoot.getLeft().successor());
        assertEquals(5,this.tree.size());
    }

    /**
     * This test case tests a regualr insert method
     */
    @Test
    public void testInsert(){
        MyBST.MyBSTNode<Integer, Integer> treeRoot = tree.root;
        MyBST.MyBSTNode<Integer, Integer> ten =
                new MyBST.MyBSTNode<>(10, 1, treeRoot.getRight());
        assertEquals(null, tree.insert(10,1));
        assertEquals(6,tree.size());
        assertEquals(ten,treeRoot.getRight().getRight());
    }

    /**
     * Tests the insert method with an existing key already in the tree
     */
    @Test
    public void testInsertReplace(){
        MyBST.MyBSTNode<Integer, Integer> treeRoot = tree.root;

        assertEquals(1, tree.insert(6,5).intValue());
        assertEquals(5,tree.size);
        assertEquals(6,treeRoot.getRight().getKey().intValue());
        assertEquals(5,treeRoot.getRight().getValue().intValue());
    }

    /**
     * This tests the remove method on a node with one child
     */
    @Test
    public void testRemove(){
        MyBST.MyBSTNode<Integer, Integer> treeRoot = tree.root;

        assertEquals(1,tree.remove(2).intValue());
        assertEquals(4,tree.size());
        assertEquals(1,treeRoot.getLeft().getKey().intValue());
        assertEquals(2,treeRoot.getLeft().getValue().intValue());
    }
    
    /**
     * This tests the remove method on a node with two children
     */
    @Test
    public void testRemove2Kids(){
        MyBST.MyBSTNode<Integer, Integer> treeRoot = tree.root;
        assertEquals(1, tree.remove(4).intValue());
        assertEquals(4,tree.size());
        assertEquals(5,treeRoot.getKey().intValue());
    }

    /**
     * This test the inorder method on a non empty tree
     */
    @Test
    public void testInorder(){
        MyBST.MyBSTNode<Integer, Integer> treeRoot = tree.root;
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> expectedRes =
                new ArrayList<>();
        expectedRes.add(treeRoot.getLeft().getLeft());
        expectedRes.add(treeRoot.getLeft());
        expectedRes.add(treeRoot);
        expectedRes.add(treeRoot.getRight().getLeft());
        expectedRes.add(treeRoot.getRight());
        assertEquals(expectedRes,tree.inorder());
        assertEquals(5,tree.inorder().size());
    }

    /**
     * This tests the inorder method on an empty tree
     */
    @Test
    public void testInorderEmpty(){
        this.pine = new MyBST<>();
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> expectedRes =
                new ArrayList<>();
        assertEquals(expectedRes, pine.inorder());
        assertEquals(0,pine.inorder().size());
    }

    /**
     * This tests the search method on a non existing node and an existing
     * node
     */
    @Test
    public void testSearch(){
        //MyBST.MyBSTNode<Integer, Integer> treeRoot = tree.root;
        
        assertEquals(2,tree.search(1).intValue());
        assertEquals(1,tree.search(2).intValue());
        assertEquals(null,tree.search(7));
    }
}
