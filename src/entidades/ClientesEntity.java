package entidades;

import javax.persistence.*;

/**
 * Created by osocron on 5/06/15.
 */
@Entity
@Table(name = "clientes", schema = "", catalog = "videoClub")
public class ClientesEntity {
    private int numCliente;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String correo;
    private long ifEocr;

    @Id
    @Column(name = "numCliente", nullable = false, insertable = true, updatable = true)
    public int getNumCliente() {
        return numCliente;
    }

    public void setNumCliente(int numCliente) {
        this.numCliente = numCliente;
    }

    @Basic
    @Column(name = "nombres", nullable = false, insertable = true, updatable = true, length = 45)
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    @Basic
    @Column(name = "apellidos", nullable = false, insertable = true, updatable = true, length = 80)
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Basic
    @Column(name = "direccion", nullable = false, insertable = true, updatable = true, length = 120)
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Basic
    @Column(name = "telefono", nullable = false, insertable = true, updatable = true, length = 45)
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Basic
    @Column(name = "correo", nullable = false, insertable = true, updatable = true, length = 45)
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Basic
    @Column(name = "IFEocr", nullable = false, insertable = true, updatable = true)
    public long getIfEocr() {
        return ifEocr;
    }

    public void setIfEocr(long ifEocr) {
        this.ifEocr = ifEocr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientesEntity that = (ClientesEntity) o;

        if (numCliente != that.numCliente) return false;
        if (ifEocr != that.ifEocr) return false;
        if (nombres != null ? !nombres.equals(that.nombres) : that.nombres != null) return false;
        if (apellidos != null ? !apellidos.equals(that.apellidos) : that.apellidos != null) return false;
        if (direccion != null ? !direccion.equals(that.direccion) : that.direccion != null) return false;
        if (telefono != null ? !telefono.equals(that.telefono) : that.telefono != null) return false;
        if (correo != null ? !correo.equals(that.correo) : that.correo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numCliente;
        result = 31 * result + (nombres != null ? nombres.hashCode() : 0);
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + (correo != null ? correo.hashCode() : 0);
        result = 31 * result + (int) (ifEocr ^ (ifEocr >>> 32));
        return result;
    }
}
