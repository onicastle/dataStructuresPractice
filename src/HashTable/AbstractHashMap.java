package HashTable;


import Interfaces.Entry;
import Interfaces.Map;

import java.util.Comparator;

public abstract class AbstractHashMap<K, V> implements Map<K,V> {

    protected static class HMEntry<K,V> implements Entry<K,V> {

        private K key;
        private V value;

        public HMEntry(K key, V value){
            super();
            this.key = key;
            this.value = value;
        }

        public K getKey(){
            return this.key;
        }

        public V getValue(){
            return this.value;
        }
    }

    public class DefaultComparator<K> implements Comparator<K> {

        @SuppressWarnings("unchecked")
        @Override
        public int compare(K a, K b) {
            return ((Comparable<K>) a).compareTo(b);
        }


    }

    protected Comparator<K> comparator;
    protected int size;

    protected AbstractHashMap() {
        super();
        this.comparator = new DefaultComparator<K>();
        this.size = 0;
    }

    protected AbstractHashMap(Comparator<K> comparator){
        super();
        this.comparator = comparator;
        this.size = 0 ;
    }

    public final int size(){
        return size;
    }

    public final boolean isEmpty(){
        return this.size() == 0;
    }

}
