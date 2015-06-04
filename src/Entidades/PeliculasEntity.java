package Entidades;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by osocron on 3/06/15.
 */
@Entity
@Table(name = "peliculas", schema = "", catalog = "videoClub")
public class PeliculasEntity {
    private int codigo;
    private String titulo;
    private Date anio;
    private String duracion;
    private String sinopsis;
    private String genero;
    private String actores;
    private String director;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo", nullable = false, insertable = true, updatable = true)
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Basic
    @Column(name = "titulo", nullable = false, insertable = true, updatable = true, length = 120)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Basic
    @Column(name = "anio", nullable = false, insertable = true, updatable = true)
    public Date getAnio() {
        return anio;
    }

    public void setAnio(Date anio) {
        this.anio = anio;
    }

    @Basic
    @Column(name = "duracion", nullable = false, insertable = true, updatable = true, length = 10)
    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    @Basic
    @Column(name = "sinopsis", nullable = true, insertable = true, updatable = true, length = 500)
    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    @Basic
    @Column(name = "genero", nullable = false, insertable = true, updatable = true, length = 50)
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Basic
    @Column(name = "actores", nullable = false, insertable = true, updatable = true, length = 500)
    public String getActores() {
        return actores;
    }

    public void setActores(String actores) {
        this.actores = actores;
    }

    @Basic
    @Column(name = "director", nullable = false, insertable = true, updatable = true, length = 120)
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PeliculasEntity that = (PeliculasEntity) o;

        if (codigo != that.codigo) return false;
        if (titulo != null ? !titulo.equals(that.titulo) : that.titulo != null) return false;
        if (anio != null ? !anio.equals(that.anio) : that.anio != null) return false;
        if (duracion != null ? !duracion.equals(that.duracion) : that.duracion != null) return false;
        if (sinopsis != null ? !sinopsis.equals(that.sinopsis) : that.sinopsis != null) return false;
        if (genero != null ? !genero.equals(that.genero) : that.genero != null) return false;
        if (actores != null ? !actores.equals(that.actores) : that.actores != null) return false;
        if (director != null ? !director.equals(that.director) : that.director != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigo;
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (anio != null ? anio.hashCode() : 0);
        result = 31 * result + (duracion != null ? duracion.hashCode() : 0);
        result = 31 * result + (sinopsis != null ? sinopsis.hashCode() : 0);
        result = 31 * result + (genero != null ? genero.hashCode() : 0);
        result = 31 * result + (actores != null ? actores.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        return result;
    }
}
