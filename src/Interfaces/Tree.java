package Interfaces;

import Interfaces.Position;

import java.util.Iterator;

public interface Tree<E> extends Iterable{

    Position<E> root();

    int height(Position<E> p);

    int depth(Position<E> p);

    Position<E> parent(Position<E> r);

    Iterable<Position<E>> children(Position<E> p);

    int numChildren(Position<E> p);

    boolean isInternal(Position<E> p);

    boolean isExternal(Position<E> p);

    boolean isRoot(Position<E> p);

    int size();

    boolean isEmpty();

    Iterator<E> iterator();

    Iterable<Position<E>> positions();
}
