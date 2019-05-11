package Interfaces;

import java.util.ArrayList;

    public interface BinarySearchTree<K,V> extends BinaryTree<Entry<K,V>>{

        ArrayList<Entry<K,V>> getAll(K key);

       Entry<K,V> get(K key);

       void add(K key, V value);

       Entry<K,V>  remove(K key);
}
