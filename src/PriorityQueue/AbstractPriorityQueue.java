package PriorityQueue;

import Interfaces.Entry;
import Interfaces.PriorityQueue;
import Comparator.*;
import java.util.Comparator;

public abstract class AbstractPriorityQueue<K,V>  implements PriorityQueue<K,V> {

    protected static class PQEntry<K,V> implements Entry<K,V>{
        private K key;
        private V value;

        public PQEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "(" + this.getKey() +", " + this.getKey() + ")";
        }

        @Override
        public K getKey() {
            return null;
        }

        @Override
        public V getValue() {
            return null;
        }
    }



    public AbstractPriorityQueue(Comparator<K> comp){
        this.comparator = comp;
    }

    protected AbstractPriorityQueue(){
        this.comparator = new DefaultComparator();
    }

    protected Comparator<K> comparator;

    protected int compare(Entry<K,V> a , Entry<K,V> b){
        return this.comparator.compare(a.getKey(), b.getKey());
    }

    protected boolean checkKey(K key) throws IllegalArgumentException{

        try{
            return (comparator.compare(key,key) == 0);
        }
        catch (ClassCastException e){
            throw new IllegalArgumentException("Incompatible key");
        }

    }
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }
}
