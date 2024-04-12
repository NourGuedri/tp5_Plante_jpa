package dao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import metier.entities.Type;
import util.JPAutil;
public class TypeDaoImpl implements ITypeDao {
// TP6_JEE à replacer par votre persistence unit, consultez votre
//fichier persistence.xml <persistence-unit name="TP6_JEE">
private EntityManager entityManager=JPAutil.getEntityManager("Plante_TP5");
@Override
public Type save(Type t ) {
EntityTransaction tx = entityManager.getTransaction();
tx.begin();
entityManager.persist(t);
tx.commit();
return t;
}
@Override
public Type getType(Long id) {
return entityManager.find(Type.class, id);
}
@Override
public Type updateType(Type t) {
EntityTransaction tx = entityManager.getTransaction();
tx.begin();
entityManager.merge(t);
tx.commit();
return t;
}
@Override
public void deleteType(Long id) {
	Type type = entityManager.find(Type.class, id);
entityManager.getTransaction().begin();
entityManager.remove(type);
entityManager.getTransaction().commit();
}
@Override
public List<Type> getAllTypes() {
List<Type> types =

entityManager.createQuery("select t from Type t").getResultList();
return types;
}
}