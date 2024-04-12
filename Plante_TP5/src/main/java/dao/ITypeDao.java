package dao;


import java.util.List;
import metier.entities.Type;
public interface ITypeDao {
public Type save(Type t);
public Type getType(Long id);
public Type updateType(Type t);
public void deleteType(Long id);
public List<Type> getAllTypes();
}