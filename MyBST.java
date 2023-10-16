/*
 * Name: Andrea Calderon
 * Email: a6calderon@ucsd.edu
 * PID: A17303613
 * Sources Used: ZyBooks, Write Up, Tutors
 */

// import used
import java.util.ArrayList;

/**
 * This class implements a BST
 */
public class MyBST<K extends Comparable<K>, V> {
    MyBSTNode<K, V> root = null;
    int size = 0;

    /**
     * gets the size of BST
     * @return size of BST
     */
    public int size() {
        return size;
    }

    /**
     * inserts node in BST
     * @param key key of node
     * @param value value of node
     * @return value of replaced value
     */
    public V insert(K key, V value) {
        if ( key == null){
            throw new NullPointerException();
        }
        MyBSTNode<K,V> node = new MyBSTNode<K,V>(key, value, null);
        if(root == null){
            root = node;
        }
        MyBSTNode<K,V> curr = root;
        V replaced = curr.getValue();
        while (curr != null){
            if(node.getKey().compareTo(curr.getKey()) < 0){
                if(curr.getLeft() == null){
                    node.setParent(curr);
                    curr.setLeft(node);
                    curr = null;
                }
                else{
                    replaced = null;
                    curr = curr.getLeft();
                }
            }
            else if(node.getKey().compareTo(curr.getKey()) == 0){
                replaced = curr.getValue();
                curr.setValue(value);
                return replaced;
            }
            else{
                if(curr.getRight() == null){
                    node.setParent(curr);
                    curr.setRight(node);
                    curr = null;
                    
                }
                else{
                    replaced = null;
                    curr = curr.getRight();
                }
            }
        }
        size++;
        return replaced;
    }

    /**
     * searches through BST for node by key
     * @param key key used to look for node
     * @return the value of the node found if not found null
     */
    public V search(K key) {
        MyBSTNode<K,V> curr = root;
        while(curr != null){
            if (key.compareTo(curr.getKey()) == 0){
                return curr.getValue();
            }
            else if (key.compareTo(curr.getKey()) < 0){
                curr = curr.getLeft();
            }
            else{
                curr = curr.getRight();
            }
        }
        return null;
    }

    /**
     * removes a node from the BST
     * @param key key of the node that will be removed
     * @return the value of the node that will be removed
     */
    public V remove(K key) {
        MyBSTNode<K,V> par = null;
        MyBSTNode<K,V> curr = root;
        V returnV = root.getValue();

        while(curr != null){
            if (curr.getKey().compareTo(key) == 0){
                if ( curr.getLeft() == null && curr.getRight() == null){
                    if ( par == null){
                        returnV = root.getValue();
                        root = null;
                        size --;
                    }
                    else if(par.getLeft() == curr){
                        returnV = curr.getValue();
                        par.setLeft(null);
                        curr.setParent(null);
                        size --;
                    }
                    else {
                        returnV = curr.getValue();
                        par.setRight(null);
                        curr.setParent(null);
                        size --;
                    }
                }
                else if ( curr.getRight() == null){
                    if (par == null){
                        returnV = root.getValue();
                        root = null;
                        size--;
                    }
                    else if (par.getLeft() == curr){
                        returnV = curr.getValue();
                        par.setLeft(curr.getLeft());
                        curr.setParent(null);
                        par.getLeft().setParent(par);
                        size--;
                    }
                    else{
                        returnV = curr.getValue();
                        par.setRight(curr.getLeft());
                        curr.setParent(null);
                        par.getRight().setParent(par);
                        size--;
                    }
                }
                else if( curr.getLeft() == null){
                    if (par == null){
                        returnV = root.getValue();
                        root = curr.getRight();
                        size--;
                    }
                    else if (par.getLeft() == curr){
                        returnV = curr.getValue();
                        par.setLeft(curr.getRight());
                        curr.setParent(null);
                        par.getLeft().setParent(par);
                        size--;
                    }
                    else{
                        returnV = curr.getValue();
                        par.setRight(curr.getRight());
                        curr.setParent(null);
                        par.getRight().setParent(par);
                        size--;
                    }
                }
                else{
                    MyBSTNode<K,V> succ = curr.successor();
                    MyBSTNode<K,V> copySucc = new MyBSTNode<K,V>(succ.getKey(), succ.getValue(), succ.getParent());
                    remove(succ.getKey());
                    curr.setValue(copySucc.getValue());
                    curr.setKey(copySucc.getKey());
                    //size--;
                }
                return returnV;
            }
            else if( curr.getKey().compareTo(key) < 0){
                par = curr;
                curr = curr.getRight();
            }
            else {
                par = curr;
                curr = curr.getLeft();
            }
        }
        return returnV;
    }

    /**
     * creates an ArrayList of the BST
     * @return an ArrayList of the BST
     */
    public ArrayList<MyBSTNode<K, V>> inorder() {
        ArrayList<MyBSTNode<K,V>> BSTArray = new ArrayList<>();
        if (size == 0){
            return new ArrayList<MyBSTNode<K, V>>();
        }
        
        MyBSTNode<K,V> curr = root;

        helperInOrder(curr, BSTArray);
        return BSTArray;
    }

    /**
     * Helper method for inorder method
     * @param node current node
     * @param list arraylist that will be added to
     */
    private void helperInOrder( MyBSTNode<K,V> node, ArrayList<MyBSTNode<K,V>> list ){ //arraylist as parameter
        if( node == null){
            return ;
        }
        //MyBSTNode<K,V> node = root;

        helperInOrder(node.getLeft(),list);
        list.add(node);
        helperInOrder(node.getRight(), list);
    }

    static class MyBSTNode<K, V> {
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K, V> parent;
        private MyBSTNode<K, V> left = null;
        private MyBSTNode<K, V> right = null;

        /**
         * Creates a MyBSTNode storing specified data
         *
         * @param key    the key the MyBSTNode will store
         * @param value  the data the MyBSTNode will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * Return the key stored in the the MyBSTNode
         *
         * @return the key stored in the MyBSTNode
         */
        public K getKey() {
            return key;
        }

        /**
         * Set the key stored in the MyBSTNode
         *
         * @param newKey the key to be stored
         */
        public void setKey(K newKey) {
            this.key = newKey;
        }

        /**
         * Return data stored in the MyBSTNode
         *
         * @return the data stored in the MyBSTNode
         */
        public V getValue() {
            return value;
        }

        /**
         * Set the data stored in the MyBSTNode
         *
         * @param newValue the data to be stored
         */
        public void setValue(V newValue) {
            this.value = newValue;
        }

        /**
         * Return the parent
         *
         * @return the parent
         */
        public MyBSTNode<K, V> getParent() {
            return parent;
        }

        /**
         * Set the parent
         *
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K, V> newParent) {
            this.parent = newParent;
        }

        /**
         * Return the left child
         *
         * @return left child
         */
        public MyBSTNode<K, V> getLeft() {
            return left;
        }

        /**
         * Set the left child
         *
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K, V> newLeft) {
            this.left = newLeft;
        }

        /**
         * Return the right child
         *
         * @return right child
         */
        public MyBSTNode<K, V> getRight() {
            return right;
        }

        /**
         * Set the right child
         *
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K, V> newRight) {
            this.right = newRight;
        }

        /**
         * gets the successor of the node
         * @return the node that is the successor of the current node
         */
        public MyBSTNode<K, V> successor() {
            MyBSTNode<K,V> node = this;
            if( node.getRight() == null ) {
                MyBSTNode<K,V> parNode = node.getParent();
                while (parNode.getLeft() != node){
                    node = parNode;
                    parNode = node.getParent();
                }
                return node.getParent();
            }
            else if(node.getRight().getLeft() == null){
                return node.getRight();
            }
            else if( node.getRight().getLeft() != null){
                node = node.getRight().getLeft();
                while (node.getLeft() != null){
                    node = node.getLeft();
                }
                return node;
            }
            return null;
        }

        /**
         * This method compares if two node objects are equal.
         *
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj) {
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K, V> comp = (MyBSTNode<K, V>) obj;

            return ((this.getKey() == null ? comp.getKey() == null :
                    this.getKey().equals(comp.getKey()))
                    && (this.getValue() == null ? comp.getValue() == null :
                    this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         *
         * @return "Key:Value" that represents the node object
         */
        public String toString() {
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}
