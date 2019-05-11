package Maps;

import Interfaces.Entry;
import Interfaces.Map;

import java.util.Iterator;
import java.util.function.Consumer;

public abstract class AbstractMap<K,V> implements Map<K,V> {

    protected static class MapEntry<K, V> implements Entry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        protected void setKey(K key) {
            this.key = key;
        }

        @Override
        public V getValue() {
            return value;
        }

        protected V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }
    }

    private class KeyIterator implements Iterator<K> {

        private Iterator<Entry<K, V>> entries = entrySet().iterator();


        @Override
        public boolean hasNext() {
            return entries.hasNext();
        }

        @Override
        public K next() {
            return entries.next().getKey();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    private class KeyIterable implements Iterable<K> {
        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }
    }

    public Iterable<K> keySet() {
        return new KeyIterable();
    }


    private class ValueIterator implements Iterator<V> {
        Iterator<Entry<K, V>> entries = entrySet().iterator();

        @Override
        public boolean hasNext() {
            return entries.hasNext();
        }

        @Override
        public V next() {
            return entries.next().getValue();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class ValueIterable implements Iterable<V> {

        @Override
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

    }

    public Iterable<V> values() {
        return new ValueIterable();
    }
}