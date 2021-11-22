package edu.cs.scu.dao;

import java.util.List;

public abstract class BaseDao {

    public abstract void add(List<Object> objectList);

    public abstract Object get(String key);

    public abstract List<Object> get(List<String> keys);
}
