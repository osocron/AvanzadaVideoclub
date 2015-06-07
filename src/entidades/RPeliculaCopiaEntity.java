package entidades;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by osocron on 5/06/15.
 */
@Entity
@Table(name = "RPeliculaCopia", schema = "", catalog = "videoClub")
public class RPeliculaCopiaEntity {
    private int numCopia;
    private int rentada;
    private Date fechaRenta;
    private Date fechaEntrega;
    private ClientesEntity clientesByNumCliente;
    private PeliculasEntity peliculasByCodigoPelicula;

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

    @Basic
    @Column(name = "fechaRenta", nullable = true, insertable = true, updatable = true)
    public Date getFechaRenta() {
        return fechaRenta;
    }

    public void setFechaRenta(Date fechaRenta) {
        this.fechaRenta = fechaRenta;
    }

    @Basic
    @Column(name = "fechaEntrega", nullable = true, insertable = true, updatable = true)
    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RPeliculaCopiaEntity that = (RPeliculaCopiaEntity) o;

        if (numCopia != that.numCopia) return false;
        if (rentada != that.rentada) return false;
        if (fechaRenta != null ? !fechaRenta.equals(that.fechaRenta) : that.fechaRenta != null) return false;
        if (fechaEntrega != null ? !fechaEntrega.equals(that.fechaEntrega) : that.fechaEntrega != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numCopia;
        result = 31 * result + rentada;
        result = 31 * result + (fechaRenta != null ? fechaRenta.hashCode() : 0);
        result = 31 * result + (fechaEntrega != null ? fechaEntrega.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "numCliente", referencedColumnName = "numCliente")
    public ClientesEntity getClientesByNumCliente() {
        return clientesByNumCliente;
    }

    public void setClientesByNumCliente(ClientesEntity clientesByNumCliente) {
        this.clientesByNumCliente = clientesByNumCliente;
    }

    @ManyToOne
    @JoinColumn(name = "codigoPelicula", referencedColumnName = "codigo", nullable = false)
    public PeliculasEntity getPeliculasByCodigoPelicula() {
        return peliculasByCodigoPelicula;
    }

    public void setPeliculasByCodigoPelicula(PeliculasEntity peliculasByCodigoPelicula) {
        this.peliculasByCodigoPelicula = peliculasByCodigoPelicula;
    }


}
