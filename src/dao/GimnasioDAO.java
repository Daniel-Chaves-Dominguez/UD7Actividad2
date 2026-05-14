package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import modelo.Gimnasio;
import modelo.Socio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GimnasioDAO {

    private EntityManagerFactory emf;
    public GimnasioDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    //INSERCIÓN DE UN GIMNASIO:
    public void insertarGimnasio(Gimnasio g) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(g);
        em.getTransaction().commit();
        em.close();
    }

    //ACTUALIZAR GIMNASIO:
    public void actualizarGimnasio(int id, Gimnasio nuevo) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Gimnasio g = em.find(Gimnasio.class, id);
        if (g != null) {
            g.setNombre(nuevo.getNombre());
            g.setCiudad(nuevo.getCiudad());
            g.setCuotaMensual(nuevo.getCuotaMensual());
        }
        em.getTransaction().commit();
        em.close();
    }

    //BORRAR GIMNASIO:
    public void borrarGimnasio(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Gimnasio g = em.find(Gimnasio.class, id);
        if (g != null) {
            em.remove(g);
        }
        em.getTransaction().commit();
        em.close();
    }

    //OBTENER LOS SOCIOS DE UN GIMNASIO CONCRETO SEGÚN ID:
    public List<Socio> sociosDeGimnasioConcretoSegunID(int id) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Socio> q = em.createQuery(
                "SELECT s FROM Gimnasio g JOIN g.socios s WHERE g.id = :id",
                Socio.class);
        q.setParameter("id", id);
        List<Socio> lista = q.getResultList();
        em.close();
        return lista;
    }

    //OBTENER EL NÚMERO DE SOCIOS INSCRITOS A CADA GIMNASIO:
    public Map<String, Long> numeroSociosACadaGimnasio() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Object[]> query = em.createQuery("SELECT g.nombre, COUNT(s) FROM Gimnasio g LEFT JOIN g.socios s GROUP BY g.nombre", Object[].class);
        Map<String, Long> lista = new HashMap<>();
        for (Object[] obj : query.getResultList()) {
            String g = (String) obj[0];
            Long c = (Long) obj[1];
            lista.put(g, c);
        }
        em.close();
        return lista;
    }


    //OBTENER LOS GIMNASIOS CON MENOS DE 10 SOCIOS:
    public List<Gimnasio> gimnasiosConMenosDe10Socios() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Gimnasio> q = em.createQuery(
                "SELECT g FROM Gimnasio g WHERE SIZE(g.socios) < 10",
                Gimnasio.class);
        List<Gimnasio> lista = q.getResultList();
        em.close();
        return lista;
    }

    //OBTENER LOS 5 GIMNASIOS CON LA CUOTA MÁS ALTA:
    public List<Gimnasio> top5CuotaAlta() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Gimnasio> q = em.createQuery(
                "SELECT g FROM Gimnasio g ORDER BY g.cuotaMensual DESC",
                Gimnasio.class);
        q.setMaxResults(5);
        List<Gimnasio> lista = q.getResultList();
        em.close();
        return lista;
    }


    //OBTENER EL GIMNASIO DE UNA CIUDAD DADA POR PARÁMETRO CON LA CUOTA MÁS BAJA:
    public Gimnasio gimnasioMasBaratoPorCiudad(String ciudad) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Gimnasio> q = em.createQuery(
                "SELECT g FROM Gimnasio g WHERE g.ciudad = :ciudad ORDER BY g.cuotaMensual ASC",
                Gimnasio.class);
        q.setParameter("ciudad", ciudad);
        q.setMaxResults(1);

        Gimnasio g = q.getSingleResult();
        em.close();
        return g;
    }
}
