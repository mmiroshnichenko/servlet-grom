package lesson2.DAO;

import lesson2.entity.Item;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ItemDAO {
    private static SessionFactory sessionFactory;

    public static Item save(Item item) throws HibernateException {
        try(Session session = createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();

            tr.begin();

            session.save(item);

            tr.commit();

            return item;
        } catch (HibernateException e) {
            throw new HibernateException("Save is failed");
        }
    }

    public static Item update(Item item) throws HibernateException {
        try (Session session = createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.update(item);

            tr.commit();

            return item;
        } catch (HibernateException e) {
            throw new HibernateException("Update is failed");
        }
    }

    public static void delete(Item item) {
        try (Session session = createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.delete(item);

            tr.commit();
        } catch (HibernateException e) {
            throw new HibernateException("Delete is failed");
        }
    }

    public static Item findById(long itemId) {
        try (Session session = createSessionFactory().openSession()) {

            return session.get(Item.class, itemId);
        } catch (HibernateException e) {
            throw new HibernateException("Search is failed");
        }
    }

    public static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }

        return sessionFactory;
    }
}
