package dao;

import java.util.Collection;
import java.util.Optional;

public interface DAO<T>{
	boolean save(T objeto);
	Optional<T> read(Long id);
	boolean update(T t);
	boolean delete(T t);
	Collection<T> readAll();
}
