package cn.lianrf.structure;

/**
 * 红黑树实现
 * @version: v1.0
 * @date: 2021/4/19
 * @author: lianrf
 */
public class RBTree<K extends Comparable<K>,V> {
    private static final boolean RED=false;
    private static final boolean BLACK=true;
    private RBNode<K,V> root;
    public static void main(String[] args) {

        RBNode<Integer, Integer> root = new RBNode<>(4,4,null,null,null,RED);

        RBNode<Integer, Integer> p = new RBNode<>(5,5,root,null,null,RED);
        RBNode<Integer, Integer> r = new RBNode<>(7,7,p,null,null,RED);
        RBNode<Integer, Integer> rr = new RBNode<>(8,8,r,null,null,RED);
        RBNode<Integer, Integer> rl = new RBNode<>(6,6,rr,null,null,RED);
        p.setRight(r);
        r.setLeft(rl);
        r.setRight(rr);
        if(root!=null){
            root.setLeft(p);
        }
        RBTree rbTree = new RBTree();
        rbTree.root=root;
        rbTree.leftRotate(p);
        System.out.println();
    }


    /**
     *左旋
     *    x | y           x | y
     *     \|/             \|/
     *      5               7
     *     / \             / \
     *    3   7   =>      5   8
     *       / \         / \
     *      6  8        3   6
     * @param p p
     */
    private void leftRotate(RBNode<K,V> p){
        if(p!=null){
            RBNode<K, V> r = p.right;
            p.right=r.left;
            if(r.left!=null){
                r.left.parent=p;
            }
            r.left=p;
            r.parent=p.parent;
            if(p.parent==null){
                root=r;
            }else if(p.parent.right==p){
                p.parent.right=r;
            }else {
                p.parent.left=r;
            }
            p.parent=r;
        }
    }

    private void rightRotate(RBNode<K,V> p){
        if(p!=null){
            RBNode<K, V> l = p.left;
            p.left=l.right;
            if(l.right!=null){
                l.right.parent=p;
            }
            l.parent=p.parent;
            if(p==root){
                root=l;
            }else if(p.parent.left==p){
                p.parent.left=l;
            }else {
                p.parent.right=l;
            }
            l.right=p;
            p.parent=l;
        }
    }

    static class RBNode<K extends Comparable<K>,V>{
        private K key;
        private V value;

        private RBNode<K,V> parent;
        private RBNode<K,V> left;
        private RBNode<K,V> right;
        private boolean colour;

        public RBNode() {
        }

        public RBNode(K key, V value, RBNode<K, V> parent, RBNode<K, V> left, RBNode<K, V> right, boolean colour) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.colour = colour;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public RBNode<K, V> getParent() {
            return parent;
        }

        public void setParent(RBNode<K, V> parent) {
            this.parent = parent;
        }

        public RBNode<K, V> getLeft() {
            return left;
        }

        public void setLeft(RBNode<K, V> left) {
            this.left = left;
        }

        public RBNode<K, V> getRight() {
            return right;
        }

        public void setRight(RBNode<K, V> right) {
            this.right = right;
        }

        public boolean isColour() {
            return colour;
        }

        public void setColour(boolean colour) {
            this.colour = colour;
        }
    }
}
