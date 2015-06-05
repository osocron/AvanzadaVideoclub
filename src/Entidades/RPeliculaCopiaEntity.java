package Entidades;

import javax.persistence.*;

/**
 * Created by osocron on 5/06/15.
 */
@Entity
@Table(name = "RPeliculaCopia", schema = "", catalog = "videoClub")
public class RPeliculaCopiaEntity {
    private int numCopia;
    private int rentada;

    @Id
    @Column(name = "numCopia", nullable = false, insertable = true, updatable = true)
    public int getNumCopia() {
        return numCopia;
    }

    public void setNumCopia(int numCopia) {
        this.numCopia = numCopia;
    }

    @Basic
    @Column(name = "rentada", nullable = false, insertable = true, updatable = true)
    public int getRentada() {
        return rentada;
    }

    public void setRentada(int rentada) {
        this.rentada = rentada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RPeliculaCopiaEntity that = (RPeliculaCopiaEntity) o;

        if (numCopia != that.numCopia) return false;
        if (rentada != that.rentada) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numCopia;
        result = 31 * result + rentada;
        return result;
    }
}
