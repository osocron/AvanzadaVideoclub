package controlador;

import entidades.ClientesEntity;
import entidades.PeliculasEntity;
import entidades.RPeliculaCopiaEntity;

import javax.persistence.EntityTransaction;
import java.sql.Date;
import java.util.List;

/**
 * Created by osocron on 5/06/15.
 */
public class ControladorRPeliculaCopia {

    public static List<RPeliculaCopiaEntity> getCopiasDePeliculas(){
        List<RPeliculaCopiaEntity> listaClientes = ConexionBD.getEm().createQuery("SELECT c FROM RPeliculaCopiaEntity c")
                .getResultList();
        return listaClientes;
    }

    public static void guardarCopiaDePelicula(RPeliculaCopiaEntity rPeliculaCopiaEntity){
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        ConexionBD.getEm().persist(rPeliculaCopiaEntity);
        entityTransaction.commit();
    }

    public static RPeliculaCopiaEntity crearCopiaPelicula(int estaRentada, Date fechaRenta, Date fechaEntrega,
                                                          ClientesEntity cliente, PeliculasEntity pelicula){
        RPeliculaCopiaEntity copiaPelicula = new RPeliculaCopiaEntity();
        final int[] codigo = new int[1];
        List<RPeliculaCopiaEntity> listaCopiasDePeliculas = getCopiasDePeliculas();
        listaCopiasDePeliculas.forEach(curCopia -> {
            codigo[0] = curCopia.getNumCopia();
        });
        codigo[0] = codigo[0] + 1;
        codigo[0] = codigo[0];
        copiaPelicula.setNumCopia(codigo[0]);
        copiaPelicula.setRentada(estaRentada);
        copiaPelicula.setFechaRenta(fechaRenta);
        copiaPelicula.setFechaEntrega(fechaEntrega);
        copiaPelicula.setClientesByNumCliente(cliente);
        copiaPelicula.setPeliculasByCodigoPelicula(pelicula);
        ConexionBD.getEm().persist(copiaPelicula);
        return copiaPelicula;
    }

    public static void modificarNombre(int id, int rentada){
        RPeliculaCopiaEntity pelicula = getCopiaDePeliculaPorCodigo(id);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        pelicula.setRentada(rentada);
        entityTransaction.commit();
    }

    public static void eliminarCopiaDePelicula(int codigo){
        RPeliculaCopiaEntity copiaDePelicula = getCopiaDePeliculaPorCodigo(codigo);
        if(copiaDePelicula != null){
            EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
            entityTransaction.begin();
            ConexionBD.getEm().remove(copiaDePelicula);
            entityTransaction.commit();
        }
    }

    public static RPeliculaCopiaEntity getCopiaDePeliculaPorCodigo(int codigo){
        return ConexionBD.getEm().find(RPeliculaCopiaEntity.class, codigo);
    }

}
