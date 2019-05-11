package PriorityQueue;

import Interfaces.Entry;
import Trees.LinkedBinaryTree;

import java.util.Comparator;
import java.util.List;

public class HeapPriorityQueue<K,V> extends AbstractPriorityQueue<K,V>{

    private List<Entry<K,V>> heap;

    public HeapPriorityQueue(Comparator<K> comp, List<Entry<K, V>> heap) {
        super(comp);
        this.heap = heap;
    }

    public HeapPriorityQueue(List<Entry<K, V>> heap){}

    protected boolean hasLeftChild(int i){
        return getLeftChild(i) < heap.size();
    }

    protected boolean hasRightChild(int i){
        return getRightChild(i) < heap.size();
    }

    protected void swap(int i, int j){

        Entry<K,V> temp = heap.get(i);

        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    protected void upHeap(int i){
        while(i > 0){
            int p = this.getParent(i);

            if(comparator.compare(heap.get(i).getKey(), heap.get(p).getKey()) < 0){
                swap(i,p);
            }
            i = p;
        }
    }






    protected void downHeap(int i){
        while(hasLeftChild(i)){
            int leftIndex = this.getLeftChild(i);
            int smallIndex = leftIndex;
            if(hasRightChild(i)) {
                int rightIndex = this.getRightChild(i);

                if (comparator.compare(heap.get(leftIndex).getKey(), heap.get(rightIndex).getKey()) > 0) {
                    smallIndex = rightIndex;
                }
            }
                if(comparator.compare(heap.get(smallIndex).getKey(), heap.get(i).getKey()) >= 0) {
                    break;
                }

                swap(i, smallIndex);
                i = smallIndex;

        }
    }

    protected int getLeftChild(int i){
        return 2*i + 1;
    }

    protected int getRightChild(int i){
        return 2*i + 2;
    }

    protected int getParent(int i){
        return ((i-1)/2);
    }

    @Override
    public int size() {
        return this.heap.size();
    }

    @Override
    public Entry<K, V> insert(K key, V value) {
        checkKey(key);
        Entry<K,V> newEntry = new PQEntry<>(key,value);
            this.heap.add(newEntry);

            upHeap(heap.size() -1 );
        return newEntry;
    }

    public Entry<K,V> max(){
        if(this.isEmpty()){
            return null;
        }

        return this.heap.get(this.size() -1);
    }

    @Override
    public Entry<K, V> min() {
        if(this.isEmpty()) return null;

        return this.heap.get(0);
    }

    @Override
    public Entry<K, V> removeMin() {

        if(this.isEmpty()){
            return null;
        }

        Entry<K,V> result = min();
        swap(0,heap.size()-1);
        heap.remove(heap.size()-1);
        downHeap(0);
        return result;
    }
}
