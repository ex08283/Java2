package be.kdg.kollections.sets;

import be.kdg.kollections.lists.ArrayList;
import be.kdg.kollections.lists.List;

public class TreeSet<T extends Comparable<T>> implements Set<T> {
    private static class TreeNode<V extends Comparable<V>> {
        private V value;
        private TreeNode<V> left;
        private TreeNode<V> right;

        public TreeNode(V value) {
            this.value = value;
        }
    }

    private TreeNode<T> root;
    private int size = 0;

    @Override
    public void add(T value) {
        if (this.root == null) {
            this.root = new TreeNode<T>(value);
            size++;
        } else {
            add(root, value);
        }
    }

    private void add(TreeNode<T> parent, T value) {
        if (value.compareTo(parent.value) < 0) {
            if (parent.left == null) {
                parent.left = new TreeNode<>(value);
            } else add(parent.left, value);
        } else if (value.compareTo(parent.value) > 0){
            if (parent.right == null) {
                parent.right = new TreeNode<>(value);
            } else add(parent.right, value);
        } else { //this is the case for a duplicate
            System.out.println("you are adding a duplicate");
        }
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        addToList(list, root);
        return list;
    }

    //inorder!
    private void addToList(List<T> list, TreeNode<T> node) {
        if (node.left!=null) {
            addToList(list, node.left);
        }
        list.add(node.value);
        if (node.right!=null) {
            addToList(list, node.right);
        }
    }


    @Override
    public boolean remove(T element) {
        //If the element is not present return false not need to execute else statement
        if (!contains(element)) {return false;}
        else {
            // 1. create a new list of the existing elements and remove from that list the element
            List<T> list = this.toList();
            list.remove(element);
            // 2. Empty the whole tree set by setting root to null and add elements from the list
            this.root = null;
            for (int i = 0; i < list.size(); i++) {
                this.add(list.get(i));
            }
            return true;
        }

    }

    @Override
    public boolean contains(T element) {
        return contains(root, element);
    }

    private boolean contains(TreeNode<T> node, T element) {
        if (node==null) return false;
        if (node.value.equals(element)) return true;
        return contains(node.left, element)||contains(node.right, element);
    }

    @Override
    public int size() {
        return size;
    }

}
