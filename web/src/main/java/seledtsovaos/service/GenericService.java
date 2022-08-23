package seledtsovaos.service;

import java.util.List;

/**
 * Provides the methods to interact with representing data object.
 * @param <I> type
 * @param <T> object
 */
public interface GenericService<I, T> {

    List<T> getAll();

    T findById(I id);

    void add(T entity);

    void removeById(I id);

    void update(T entity);
}
