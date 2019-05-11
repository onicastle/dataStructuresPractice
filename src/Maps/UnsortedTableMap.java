package Maps;

import Interfaces.Entry;
import Interfaces.Map;
import PriorityQueue.AbstractPriorityQueue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

public class UnsortedTableMap<K,V> extends AbstractMap<K,V> {

    private ArrayList<MapEntry<K,V>> table = new ArrayList<>();

    public UnsortedTableMap(ArrayList<MapEntry<K, V>> table) {
        this.table = table;
    }

    public UnsortedTableMap(){}

    private int findIndex(K key){
        int counter = 0;
        for(MapEntry<K,V> e : this.table){
            if(e.getKey().equals(table.get(counter))){
                return counter;
            }
            counter++;
        }

        return -1;
    }

    @Override
    public int size() {
        return table.size();
    }

    @Override
    public boolean isEmpty() {
        return table.size() == 0;
    }

    @Override
    public V get(K key) {
        int i = findIndex(key);

        if(i == -1){
            return null;
        }else{
            return this.table.get(i).getValue();
        }
    }

    @Override
    public V put(K key, V value) {
        int var = findIndex(key);
        if(var == -1) {
            MapEntry<K, V> newEntry = new MapEntry<>(key, value);
            return null;
        }
        else{
           return this.table.get(var).getValue();

        }
    }

    @Override
    public V remove(K key) {
        int var = findIndex(key);
        int n = this.size();

        if (var == -1) return null;

        V old = this.table.get(var).getValue();
        if (var != n - 1) {
            table.set(var, table.get(n - 1));
        }

        table.remove(n - 1);
        return old;

    }

    private class EntryIterator implements Iterator<Entry<K,V>>{
        int j = 0;

        @Override
        public boolean hasNext() {
            return j < table.size();
        }

        @Override
        public Entry<K, V> next() {
        if(j == table.size()){
            throw new IllegalArgumentException();
        }

            return table.get(j++);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    private class EntryIterable implements Iterable<Entry<K,V>>{
        public Iterator<Entry<K,V>> iterator(){
            return new EntryIterator();
        }

    }
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return new EntryIterable();
        }
}
