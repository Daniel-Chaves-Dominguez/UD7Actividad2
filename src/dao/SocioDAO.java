package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import modelo.Gimnasio;
import modelo.Socio;
import java.util.List;

public class SocioDAO {
    private EntityManagerFactory emf;
    public SocioDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    //INSERCIÓN DE UN SOCIO:
    public void insertarSocio(Socio s) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(s);
        em.getTransaction().commit();
        em.close();
    }

    //ACTUALIZAR UN SOCIO:
    public void actualizarSocio(int id, Socio nuevo) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Socio s = em.find(Socio.class, id);
        if (s != null) {
            s.setNombreCompleto(nuevo.getNombreCompleto());
            s.setEdad(nuevo.getEdad());
            s.setVip(nuevo.isVip());
        }

        em.getTransaction().commit();
        em.close();
    }

    //BORRAR UN SOCIO:
    public void borrarSocio(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Socio s = em.find(Socio.class, id);
        if (s != null) {
            em.remove(s);
        }
        em.getTransaction().commit();
        em.close();
    }

    //ASIGNAR UN SOCIO A UN GIMNASIO:
    public void asignarSocio(int idSocio, int idGimnasio) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Socio s = em.find(Socio.class, idSocio);
        Gimnasio g = em.find(Gimnasio.class, idGimnasio);
        s.getGimnasios().add(g);
        g.getSocios().add(s);
        em.getTransaction().commit();
        em.close();
    }

    //BORRAR UN SOCIO DE UN GIMNASIO:
    public void borrarSocioDeGimnasio(int idSocio, int idGimnasio) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Socio s = em.find(Socio.class, idSocio);
        Gimnasio g = em.find(Gimnasio.class, idGimnasio);
        s.getGimnasios().remove(g);
        g.getSocios().remove(s);
        em.getTransaction().commit();
        em.close();
    }

    //OBTENER LOS GIMNASIO A LOS QUE ESTÁ APUNTADO UN SOCIO CONCRETO:
    public List<Gimnasio> gimnasiosDeSocioEnConcreto(int id) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Gimnasio> q = em.createQuery(
                "SELECT g FROM Socio s JOIN s.gimnasios g WHERE s.id = :id",
                Gimnasio.class);
        q.setParameter("id", id);
        List<Gimnasio> lista = q.getResultList();
        em.close();
        return lista;
    }


    //OBTENER LA MEDIA DE EDAD DE TODOS LOS SOCIOS:
    public double mediaEdadSocios() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Double> q = em.createQuery(
                "SELECT AVG(s.edad) FROM Socio s",
                Double.class);
        double media = q.getSingleResult();
        em.close();
        return media;
    }

    //OBTENER LOS SOCIOS QUE NO ESTÁN INSCRITOS EN NINGÚN GIMNASIO:
    public List<Socio> sociosSinGimnasio() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Socio> q = em.createQuery(
                "SELECT s FROM Socio s WHERE s.gimnasios IS EMPTY",
                Socio.class);
        List<Socio> lista = q.getResultList();
        em.close();
        return lista;
    }
}
