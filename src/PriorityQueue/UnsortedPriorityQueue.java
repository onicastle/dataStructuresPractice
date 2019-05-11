package PriorityQueue;

import Interfaces.Entry;
import Positional.*;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class UnsortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {

    List<Entry<K,V>> list;

    public UnsortedPriorityQueue(Comparator<K> comparator){
        super(comparator);
        this.list = new LinkedList<>();
    }

    public UnsortedPriorityQueue(){
        super();
        this.list = new LinkedList<>();
    }
    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public Entry<K, V> insert(K key, V value) {
        Entry<K,V> newest = new PQEntry<K,V>(key, value);
        this.list.add(0,newest);
        return newest;
    }

    private int findIndex(Entry<K,V> w){
        for(int i = 0; i < this.list.size(); ++i){
            if(comparator.compare(this.list.get(i).getKey(), w.getKey()) == 0){
                return i;
            }
        }

        return -1;
    }
    @Override
    public Entry<K, V> min() {
        if(this.isEmpty()){
            return null;
        }
        else{
            return findMin();
        }


    }

    private Entry<K, V> findMin() {
        Entry<K, V> temp= this.list.get(0);
        for(Entry<K,V> e : this.list){
            if(this.comparator.compare(e.getKey(),temp.getKey()) < 0){
                temp = e;
            }
        }

        return temp;
    }

    @Override
    public Entry<K, V> removeMin() {
        int var = this.findIndex(findMin());
        if(var == -1){
            return null;
        }
        else {
            return this.list.get(var);
        }
    }
}
