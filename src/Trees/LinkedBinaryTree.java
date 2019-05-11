package Trees;

import Interfaces.Position;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    protected static class Node<E> implements Position<E> {

        private E element;

        private Node<E> parent;

        private Node<E> leftChild;

        private Node<E> rightChild;

        public Node(E element, Node<E> parent, Node<E> leftChild, Node<E> rightChild) {
            this.element = element;
            this.parent = parent;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public Node<E> getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node<E> leftChild) {
            this.leftChild = leftChild;
        }

        public Node<E> getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node<E> rightChild) {
            this.rightChild = rightChild;
        }

        @Override
        public E getElement() {
            return this.element;
        }
    }

    protected Node<E> createNode(E element, Node<E> parent, Node<E> leftChild, Node<E> rightChild) {
        return new Node<E>(element, parent, leftChild, rightChild);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    Node<E> root;
    int size = 0;

    public LinkedBinaryTree(){}

    protected Node<E> validate(Position p){
        if(!(p instanceof Node)) throw new IllegalArgumentException();

        Node<E> node = (Node<E>) p;

        if(node == parent(node)) throw new IllegalArgumentException();

        return node;

    }

    public Node<E> root(){
        if(this.isEmpty()){
            return null;
        }
        return this.root;
    }

    @Override
    public Position<E> left(Position<E> p) {
        Node<E> target = validate(p);
        return target.getLeftChild();
    }

    public Position<E> addRoot(E element){

        if(this.isEmpty()){
            root = createNode(element, null, null, null);
            size++;
            return (Position<E>) root;
        }
        else{
            throw new IllegalArgumentException();
        }

    }

    public Position<E> addLeft(Position<E> p, E element){
        Node<E> target = validate(p);
        if(this.isEmpty() || target != null){
            throw new IllegalArgumentException();
        }
        else{
           Node<E> child =  createNode(element, target,null,null);
           target.setLeftChild(child);
           size++;
           return(Position<E>) child;
        }
    }

    public E setElement(Position<E> p, E element){

        Node<E> target = validate(p);
        E result = target.getElement();
        target.setElement(element);
        return result;

    }


    public Position<E> addRight(Position<E> p, E element){
        Node<E> target = validate(p);
        if(this.isEmpty() || target != null){
            throw new IllegalArgumentException();
        }
        else{
            Node<E> child =  createNode(element, target, null,null);
            target.setRightChild(child);
            size++;
            return (Position<E>) child;
        }
    }

    public void attach(Position<E> p, LinkedBinaryTree t, LinkedBinaryTree two) {
        Node<E> target = validate(p);

        if (isInternal(p)) throw new IllegalArgumentException();
        this.size += t.size + two.size;
        if(!t.isEmpty()){
            t.root.setParent(target);
            target.setLeftChild(t.root);
            t.root = null;
            t.size = 0;
        }

        if(!two.isEmpty()){
            two.root.setParent(target);
            target.setRightChild(two.root);
            two.root = null;
            two.size =0;
        }

    }

    public E remove(Position<E> p){

        Node<E> node = validate(p);

        if(numChildren(p) == 2){
            throw new IllegalArgumentException();
        }

        Node<E> child = (node.getLeftChild() != null ? node.getLeftChild() : node.getRightChild());

        if(child!=null){
            child.setParent(node.getParent());
        }

        if(node == root){
            root = child;
        }
        else{
            Node<E> parent = node.getParent();

            if(node == parent.getLeftChild()){
                parent.setLeftChild(child);
            }
            else{
                parent.setRightChild(child);
            }
        }

        size--;
        E result = node.getElement();
        clear(node);
        return result;


    }

    public Iterable<Position<E>> postOrder(Position<E> root){
        if(root == null){
        }
        ArrayList<Position<E>> list = new ArrayList<Position<E>>();
        preOrderIterable(root,list);
        return list;
    }

    private void postOrderIterable(Position<E> p, ArrayList<Position<E>> list){
        Node<E> travaler  =validate(p);

        if(travaler == null){


        }

        else{

            preOrderIterable(travaler.leftChild, list);

            preOrderIterable(travaler.rightChild, list);

            list.add(travaler);


        }

    }

    public Iterable<Position<E>> bfsIterable(){
        List<Position<E>> list = new LinkedList<Position<E>>();

        List<Position<E>> queue = new LinkedList<Position<E>>();

        Position<E> r = this.root();

        if(r == null){
            return list;
        }

        queue.add(r);
        Position<E> p = null;
        while(!queue.isEmpty()){
            p = queue.remove(0);

            for(Position<E> child : this.children(p)){
                queue.add(child);
            }
        }
        return list;

    }


    public Iterable<Position<E>> inOrder(Position<E> root){
        if(root == null){
        }
        ArrayList<Position<E>> list = new ArrayList<Position<E>>();
        preOrderIterable(root,list);
        return list;
    }

    private void inOrderIterable(Position<E> p, ArrayList<Position<E>> list){
        Node<E> travaler  =validate(p);

        if(travaler == null){
            return;

        }else{
            preOrderIterable(travaler.leftChild, list);

            list.add(travaler);

            preOrderIterable(travaler.rightChild, list);
        }

    }

    public Iterable<Position<E>> PreOrder(Position<E> root){
        if(root == null){
        }
            ArrayList<Position<E>> list = new ArrayList<Position<E>>();
            preOrderIterable(root,list);
            return list;
    }

    private void preOrderIterable(Position<E> p, ArrayList<Position<E>> list){
        Node<E> travaler  =validate(p);

        if(travaler == null){
            return;

        }else{
            list.add(travaler);
            preOrderIterable(travaler.leftChild, list);

            preOrderIterable(travaler.rightChild, list);
        }

    }

    private void clear(Node<E> p){
        p.setRightChild(null);
        p.setLeftChild(null);
        p.setParent(p);
        p.setElement(null);
    }

    @Override
    public Position<E> right(Position<E> p) {
        Node<E> target = validate(p);
        return target.getRightChild();
    }
    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Iterable<Position<E>> positions() {
        return null;
    }

    @Override
    public Position<E> parent(Position<E> r) {
        Node<E> target = validate(r);
        return target.getParent();
    }

    public int size(){
        return this.size;
    }


}