package Project_Java_Advanced.daos;

import java.util.List;

public interface CRUD <T>{

    T insert(T t);
    T selectById(int id);
    List<T> selectAll();
    void update(T t);
    void delete(int id);
}
