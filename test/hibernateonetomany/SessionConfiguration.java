/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateonetomany;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Mateusz
 */
public class SessionConfiguration {

    private static final SessionFactory sf = setupSessionFactory();

    private static SessionFactory setupSessionFactory() {
        try {
            // Create the SessionFactory from headeribernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SessionFactory getSessionFactory() {
        return sf;
    }
}
