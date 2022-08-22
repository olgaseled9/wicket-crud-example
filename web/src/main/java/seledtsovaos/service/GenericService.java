package seledtsovaos.service;

import java.util.List;


public interface GenericService<I, T> {

    List<T> getAll();

    T findById(I id);

    void add(T entity);

    void removeById(I id);

    void update(T entity);
}
