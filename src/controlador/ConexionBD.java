package controlador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by osocron on 3/06/15.
 */
public class ConexionBD {

    private static EntityManager em;

    public static void conectar() {
        EntityManagerFactory emf = Persistence.
                createEntityManagerFactory("videoClubPU");
        em = emf.createEntityManager();
    }

    public static EntityManager getEm() {
        return em;
    }

}
