import dao.GimnasioDAO;
import dao.SocioDAO;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import modelo.Gimnasio;
import modelo.Socio;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mibd.odb");

        GimnasioDAO gymDAO = new GimnasioDAO(emf);
        SocioDAO socioDAO = new SocioDAO(emf);

        //GIMNASIO
        System.out.println("INSERCIÓN DE UN GIMNASIO:");
        gymDAO.insertarGimnasio(new Gimnasio("Rafat", "Sevilla", 41.99, new ArrayList<>()));

        System.out.println("\nACTUALIZACIÓN DE CUALQUIER CAMPO DE UN GIMNASIO:");
        gymDAO.actualizarGimnasio(11,new Gimnasio("Rafit", "Sevilla", 45.99, new ArrayList<>()));

        System.out.println("\nOBTENER LOS SOCIOS DE UN GIMNASIO CONCRETO SEGÚN ID:");
        System.out.println(gymDAO.sociosDeGimnasioConcretoSegunID(5));

        System.out.println("\nOBTENER EL NÚMERO DE SOCIOS INSCRITOS A CADA GIMNASIO:");
        System.out.println(gymDAO.numeroSociosACadaGimnasio());

        System.out.println("\nOBTENER LOS GIMNASIOS CON MENOS DE 10 SOCIOS:");
        System.out.println(gymDAO.gimnasiosConMenosDe10Socios());

        System.out.println("\nOBTENER LOS 5 GIMNASIOS CON LA CUOTA MÁS ALTA:");
        System.out.println(gymDAO.top5CuotaAlta());

        System.out.println("\nOBTENER EL GIMNASIO DE UNA CIUDAD DADA POR PARÁMETRO CON LA CUOTA MÁS BAJA:");
        System.out.println(gymDAO.gimnasioMasBaratoPorCiudad("Sevilla"));




        //SOCIO
        System.out.println("\nINSERCIÓN DE UN SOCIO:");
        socioDAO.insertarSocio(new Socio("Rafa Schwarzenegger", 28, true, new ArrayList<>()));

        System.out.println("\nACTUALIZACIÓN DE CUALQUIER CAMPO DE UN SOCIO:");
        socioDAO.actualizarSocio(11, new Socio("Arnold Fresno", 28, false, new ArrayList<>()));

        System.out.println("\nASIGNAR UN SOCIO A UN GIMNASIO:");
        socioDAO.asignarSocio(11,11);

        System.out.println("\nOBTENER LOS GIMNASIO A LOS QUE ESTÁ APUNTADO UN SOCIO CONCRETO:");
        System.out.println(socioDAO.gimnasiosDeSocioEnConcreto(6));

        System.out.println("\nOBTENER LA MEDIA DE EDAD DE TODOS LOS SOCIOS:");
        System.out.println(socioDAO.mediaEdadSocios());

        System.out.println("\nOBTENER LOS SOCIOS QUE NO ESTÁN INSCRITOS EN NINGÚN GIMNASIO:");
        System.out.println(socioDAO.sociosSinGimnasio());


        //BORRADOS DE GIMNASIO Y SOCIO:
        System.out.println("\nBORRADO DE UN SOCIO DE UN GIMNASIO:");
        socioDAO.borrarSocioDeGimnasio(11,11);
        
        System.out.println("\nBORRADO DE UN GIMNASIO:");
        gymDAO.borrarGimnasio(11);

        System.out.println("\nBORRADO DE UN SOCIO:");
        socioDAO.borrarSocio(11);


        emf.close();











        /*gymDAO.insertarGimnasio(new Gimnasio("Iron Temple", "Madrid", 45.99, new ArrayList<>()));
        gymDAO.insertarGimnasio(new Gimnasio("Sparta Fitness", "Barcelona", 29.90,  new ArrayList<>()));
        gymDAO.insertarGimnasio(new Gimnasio("Yoga & Flow", "Valencia", 60.00, new ArrayList<>()));
        gymDAO.insertarGimnasio(new Gimnasio("LowCost Gym", "Sevilla", 19.95,new ArrayList<>()));
        gymDAO.insertarGimnasio(new Gimnasio("Elite Performance", "Madrid", 85.00,new ArrayList<>()));
        gymDAO.insertarGimnasio(new Gimnasio("CrossFit Box 33", "Bilbao", 70.00,new ArrayList<>()));
        gymDAO.insertarGimnasio(new Gimnasio("Padel & Gym", "Málaga", 35.50,new ArrayList<>()));
        gymDAO.insertarGimnasio(new Gimnasio("Wellness Center", "Zaragoza", 55.00,new ArrayList<>()));
        gymDAO.insertarGimnasio(new Gimnasio("Heavy Metal Lifting", "Vigo", 25.00,new ArrayList<>()));
        gymDAO.insertarGimnasio(new Gimnasio("Zumba Party", "Alicante", 32.00,new ArrayList<>()));

        socioDAO.insertarSocio(new Socio("Ana García", 28, true,new ArrayList<>()));
        socioDAO.insertarSocio(new Socio("Carlos Pérez", 45, false,new ArrayList<>()));
        socioDAO.insertarSocio(new Socio("Lucía Fernández", 19, false,new ArrayList<>()));
        socioDAO.insertarSocio(new Socio("Marcos Ruiz", 34, true,new ArrayList<>()));
        socioDAO.insertarSocio(new Socio("Elena Sanz", 52, false,new ArrayList<>()));
        socioDAO.insertarSocio(new Socio("David León", 23, true,new ArrayList<>()));
        socioDAO.insertarSocio(new Socio("Sara Cano", 31, false,new ArrayList<>()));
        socioDAO.insertarSocio(new Socio("Roberto Gómez", 60, true,new ArrayList<>()));
        socioDAO.insertarSocio(new Socio("Irene Molina", 26, false,new ArrayList<>()));
        socioDAO.insertarSocio(new Socio("Javier Ortiz", 40, false,new ArrayList<>()));


        socioDAO.asignarSocio(11, 1);
        socioDAO.asignarSocio(11, 3);
        socioDAO.asignarSocio(11, 5);
        socioDAO.asignarSocio(11, 8);
        socioDAO.asignarSocio(11, 10);
        socioDAO.asignarSocio(12, 2);
        socioDAO.asignarSocio(12, 4);
        socioDAO.asignarSocio(12, 9);
        socioDAO.asignarSocio(13, 2);
        socioDAO.asignarSocio(13, 4);
        socioDAO.asignarSocio(13, 10);
        socioDAO.asignarSocio(14, 1);
        socioDAO.asignarSocio(14, 2);
        socioDAO.asignarSocio(14, 3);
        socioDAO.asignarSocio(14, 4);
        socioDAO.asignarSocio(14, 5);
        socioDAO.asignarSocio(15, 6);
        socioDAO.asignarSocio(15, 7);
        socioDAO.asignarSocio(15, 8);
        socioDAO.asignarSocio(16, 1);
        socioDAO.asignarSocio(16, 6);
        socioDAO.asignarSocio(16, 9);
        socioDAO.asignarSocio(17, 2);
        socioDAO.asignarSocio(17, 4);
        socioDAO.asignarSocio(17, 7);
        socioDAO.asignarSocio(18, 1);
        socioDAO.asignarSocio(18, 3);
        socioDAO.asignarSocio(18, 5);
        socioDAO.asignarSocio(19, 6);
        socioDAO.asignarSocio(19, 10);

        System.out.println("Datos insertados sin errores");
        */
    }
}