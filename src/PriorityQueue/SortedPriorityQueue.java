package PriorityQueue;

import Interfaces.Entry;
import Interfaces.Position;
import Interfaces.PositionalList;
import Positional.DLLPositionalList;

import java.util.Comparator;
import java.util.List;

public class SortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {

    PositionalList<Entry<K,V>> list = new DLLPositionalList<Entry<K,V>>();

    public SortedPriorityQueue(Comparator<K> comp, List<Entry<K, V>> list) {
        super(comp);
        this.list = new DLLPositionalList<Entry<K,V>>();;
    }

    public SortedPriorityQueue(List<Entry<K, V>> list) {
        this.list = new DLLPositionalList<Entry<K,V>>();;
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public Entry<K, V> insert(K key, V value) {
        Entry<K,V> newEntry = new PQEntry<K, V>(key,value);

        if(this.isEmpty()){
            this.list.addFirst(newEntry);
            return newEntry;
        }
        else{
            Position<Entry<K,V>> traveler = this.list.first();
            while(traveler != null){
                if(this.comparator.compare(newEntry.getKey(), traveler.getElement().getKey()) < 0){
                    this.list.addBefore(traveler,newEntry);
                    return newEntry;
                }
                 traveler = this.list.after(traveler);
            }
            this.list.addLast(newEntry);
            return newEntry;
        }

    }

    @Override
    public Entry<K, V> min() {
        if(this.isEmpty()) return null;
        else return this.list.first().getElement();
    }

    @Override
    public Entry<K, V> removeMin() {
        if(this.isEmpty()) return null;
        else return this.list.remove(this.list.first());
    }
}
