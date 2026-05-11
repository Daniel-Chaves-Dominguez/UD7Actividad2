package modelo;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Gimnasio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombre;
    private String ciudad;
    private double cuotaMensual;

    @ManyToMany
    private List<Socio> socios;

    public Gimnasio(String nombre, String ciudad, double cuotaMensual, List<Socio> socios) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.cuotaMensual = cuotaMensual;
        this.socios = socios;
    }

    public Gimnasio() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getCuotaMensual() {
        return cuotaMensual;
    }

    public void setCuotaMensual(double cuotaMensual) {
        this.cuotaMensual = cuotaMensual;
    }

    public List<Socio> getSocios() {
        return socios;
    }

    public void setSocios(List<Socio> socios) {
        this.socios = socios;
    }

    @Override
    public String toString() {
        return "Gimnasio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", cuotaMensual=" + cuotaMensual +
                '}';
    }
}
