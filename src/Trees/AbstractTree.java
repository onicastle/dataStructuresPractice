package Trees;

import Interfaces.Position;
import Interfaces.Tree;

public abstract class AbstractTree<E> implements Tree<E> {

    @Override
    public boolean isRoot(Position<E> p){
        return p == root();
    }

    @Override
    public boolean isInternal(Position<E> p){
        return numChildren(p) > 0;
    }

    @Override
    public boolean isExternal(Position<E> p){
        return numChildren(p) == 0;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int height(Position<E> p) {
        int height = 0;

        for(Position<E> c : children(p)){
            height = Math.max(height, 1 + height(c));
        }

        return height;
    }

    @Override
    public int depth(Position<E> p){
        if(isRoot(p)){
            return 0;
        }
        else {
            return 1 + depth(parent(p));
        }
    }

}
