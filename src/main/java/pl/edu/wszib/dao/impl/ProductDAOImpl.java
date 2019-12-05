package pl.edu.wszib.dao.impl;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import pl.edu.wszib.dao.IProductDAO;
import pl.edu.wszib.model.Product;

import java.util.List;

@Component
public class ProductDAOImpl implements IProductDAO {
    private static SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public ProductDAOImpl() {
    }

    public void persistProduct(Product product) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(product);
            //wiecej operacji
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }

    public Product getProductById(int productID) {
        Session session = factory.openSession();

        Product product = (Product) session.createQuery("FROM pl.edu.wszib.model.Product WHERE id = " + productID).uniqueResult();

        session.close();
        return product;
    }

    @Override
    public void updateProduct(Product product) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(product);
            //wiecej operacji
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }

}
