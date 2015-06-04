package controlador;

import Entidades.PeliculasEntity;
import avanzadavideoclub.Peliculas;

import javax.persistence.EntityTransaction;
import java.sql.Date;
import java.util.List;

/**
 * Created by osocron on 3/06/15.
 * Controlador para las entidades de Peliculas
 */
public class ControladorPeliculas{

    public static List<PeliculasEntity> getPeliculas(){
        List<PeliculasEntity> listaPeliculas = ConexionBD.getEm().createQuery("SELECT p FROM PeliculasEntity p")
                .getResultList();
        return listaPeliculas;
    }

    public static void guardarPelicula(PeliculasEntity pelicula){
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        ConexionBD.getEm().persist(pelicula);
        entityTransaction.commit();
    }

    public static PeliculasEntity crearPelicula(String titulo, Date anio, String duracion, String sinopsis,
                                                String genero, String actores, String director){
        PeliculasEntity pelicula = new PeliculasEntity();
        pelicula.setTitulo(titulo);
        pelicula.setAnio(anio);
        pelicula.setDuracion(duracion);
        pelicula.setSinopsis(sinopsis);
        pelicula.setGenero(genero);
        pelicula.setActores(actores);
        pelicula.setDirector(director);
        ConexionBD.getEm().persist(pelicula);
        return pelicula;
    }

    public static void modificarTitulo(int id, String titulo){
        PeliculasEntity pelicula = getPeliculaPorCodigo(id);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        pelicula.setTitulo(titulo);
        entityTransaction.commit();
    }


    public static void eliminarPelicula(int codigo){
        PeliculasEntity pelicula = getPeliculaPorCodigo(codigo);
        if(pelicula != null){
            ConexionBD.getEm().remove(pelicula);
        }
    }

    public static PeliculasEntity getPeliculaPorCodigo(int codigo){
        return ConexionBD.getEm().find(PeliculasEntity.class, codigo);
    }


}
