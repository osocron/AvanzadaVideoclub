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
        final int[] codigo = new int[1];
        List<PeliculasEntity> peliculas = getPeliculas();
        peliculas.forEach(peli -> {
            codigo[0] = peli.getCodigo();
        });
        codigo[0] = codigo[0] + 1;
        codigo[0] = codigo[0];
        pelicula.setCodigo(codigo[0]);
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

    public static void modificarDuracion(int id, String duracion){
        PeliculasEntity pelicula = getPeliculaPorCodigo(id);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        pelicula.setDuracion(duracion);
        entityTransaction.commit();
    }

    public static void modificarSinopsis(int id, String sinopsis){
        PeliculasEntity pelicula = getPeliculaPorCodigo(id);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        pelicula.setSinopsis(sinopsis);
        entityTransaction.commit();
    }

    public static void modificarGenero(int id, String genero){
        PeliculasEntity pelicula = getPeliculaPorCodigo(id);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        pelicula.setGenero(genero);
        entityTransaction.commit();
    }

    public static void modificarActores(int id, String actores){
        PeliculasEntity pelicula = getPeliculaPorCodigo(id);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        pelicula.setActores(actores);
        entityTransaction.commit();
    }

    public static void modificarDirector(int id, String director){
        PeliculasEntity pelicula = getPeliculaPorCodigo(id);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        pelicula.setDirector(director);
        entityTransaction.commit();
    }

    public static void modificarAnio(int id, Date anio){
        PeliculasEntity pelicula = getPeliculaPorCodigo(id);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        pelicula.setAnio(anio);
        entityTransaction.commit();
    }

    public static void eliminarPelicula(int codigo){
        PeliculasEntity pelicula = getPeliculaPorCodigo(codigo);
        if(pelicula != null){
            EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
            entityTransaction.begin();
            ConexionBD.getEm().remove(pelicula);
            entityTransaction.commit();
        }
    }

    public static PeliculasEntity getPeliculaPorCodigo(int codigo){
        return ConexionBD.getEm().find(PeliculasEntity.class, codigo);
    }


}
