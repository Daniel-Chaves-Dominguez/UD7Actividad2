package modelo;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Socio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombreCompleto;
    private int edad;
    private boolean vip;

    @ManyToMany(mappedBy = "socios")
    private List<Gimnasio> gimnasios;

    public Socio(String nombreCompleto, int edad, boolean vip, List<Gimnasio> gimnasios) {
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.vip = vip;
        this.gimnasios = gimnasios;
    }

    public Socio() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public List<Gimnasio> getGimnasios() {
        return gimnasios;
    }

    public void setGimnasios(List<Gimnasio> gimnasios) {
        this.gimnasios = gimnasios;
    }

    @Override
    public String toString() {
        return "Socio{" +
                "id=" + id +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", edad=" + edad +
                ", vip=" + vip +
                '}';
    }
}