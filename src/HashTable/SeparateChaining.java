package HashTable;

import Interfaces.Entry;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SeparateChaining<K,V> extends AbstractHashMap<K,V> {

    private List<Entry<K,V>>[] buckets;
    private static final int DEFAULT_SIZE = 101;


    public SeparateChaining() {
        super();
        this.buckets = ((List<Entry<K,V>>[])new List[DEFAULT_SIZE]);
        for(int i = 0 ; i < this.buckets.length; ++i){
            this.buckets[i] = new LinkedList<>();
        }
    }

    private int hash(K key){
        return key.hashCode() % this.buckets.length;
    }

    @Override
    public V get(K key){
        int target = this.hash(key);
        List<Entry<K,V>> B = this.buckets[target];

        for(Entry<K,V> e : B){
            if(this.comparator.compare(e.getKey(), key) == 0){
                return e.getValue();
            }
        }
        return null;
    }






    @Override
    public V put(K key, V value) {
        this.remove(key);
        int target = this.hash(key);
        List<Entry<K,V>> B = this.buckets[target];
        B.add(new HMEntry<>(key, value));
        this.size++;
        return value;
    }


    @Override
    public V remove(K key) {
        int target = this.hash(key);
        List<Entry<K,V>> B = this.buckets[target];
        int pos = -1;

        for(int i = 0; i < B.size() ; ++i){
            if(this.comparator.compare(key, B.get(i).getKey()) == 0){
                pos = i;
                break;
            }
        }

        if(pos != -1){
            this.size--;
            return B.remove(pos).getValue();
        }
        return null;
    }

    @Override
    public Iterable<K> keySet() {
        List<K> list = new ArrayList<>(this.size);

        for(Entry<K,V> e : entrySet()){

            list.add(e.getKey());

        }

        return list;
    }

    @Override
    public Iterable<V> values() {

        List<V> list = new ArrayList<>(this.size);

        for(Entry<K,V> e : entrySet()){

            list.add(e.getValue());

        }

        return list;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {

        List<Entry<K,V>> list = new ArrayList<>(this.size);

        for(List<Entry<K,V>> B: this.buckets){

            for(Entry<K,V> e : B){

                list.add(e);

            }

        }

        return list;
    }

}
