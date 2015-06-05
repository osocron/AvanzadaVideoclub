package controlador;

import entidades.ClientesEntity;

import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by osocron on 3/06/15.
 * Controlados para la entidad Cliente
 */
public class ControladorClientes {

    public static List<ClientesEntity> getClientes(){
        List<ClientesEntity> listaClientes = ConexionBD.getEm().createQuery("SELECT c FROM ClientesEntity c")
                .getResultList();
        return listaClientes;
    }

    public static void guardarCliente(ClientesEntity cliente){
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        ConexionBD.getEm().persist(cliente);
        entityTransaction.commit();
    }

    public static ClientesEntity crearCliente(String nombres, String apellidos, String direccion, String telefono,
                                              String correo, Long ifeOcr){
        ClientesEntity cliente = new ClientesEntity();
        final int[] codigo = new int[1];
        List<ClientesEntity> clientes = getClientes();
        clientes.forEach(curCliente -> {
            codigo[0] = curCliente.getNumCliente();
        });
        codigo[0] = codigo[0] + 1;
        codigo[0] = codigo[0];
        cliente.setNumCliente(codigo[0]);
        cliente.setNombres(nombres);
        cliente.setApellidos(apellidos);
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);
        cliente.setCorreo(correo);
        cliente.setIfEocr(ifeOcr);
        ConexionBD.getEm().persist(cliente);
        return cliente;
    }

    public static void modificarNombre(int id, String nombre){
        ClientesEntity pelicula = getClientePorCodigo(id);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        pelicula.setNombres(nombre);
        entityTransaction.commit();
    }

    public static void modificarApellidos(int id, String apellidos){
        ClientesEntity pelicula = getClientePorCodigo(id);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        pelicula.setApellidos(apellidos);
        entityTransaction.commit();
    }

    public static void modificarDireccion(int id, String direccion){
        ClientesEntity pelicula = getClientePorCodigo(id);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        pelicula.setDireccion(direccion);
        entityTransaction.commit();
    }

    public static void modificarTelefono(int id, String telefono){
        ClientesEntity pelicula = getClientePorCodigo(id);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        pelicula.setTelefono(telefono);
        entityTransaction.commit();
    }

    public static void modificarEmail(int id, String email){
        ClientesEntity pelicula = getClientePorCodigo(id);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        pelicula.setCorreo(email);
        entityTransaction.commit();
    }

    public static void modificarIfeOrc(int id, Long ifeOrc){
        ClientesEntity pelicula = getClientePorCodigo(id);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        pelicula.setIfEocr(ifeOrc);
        entityTransaction.commit();
    }

    public static void eliminarCliente(int codigo){
        ClientesEntity cliente = getClientePorCodigo(codigo);
        if(cliente != null){
            EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
            entityTransaction.begin();
            ConexionBD.getEm().remove(cliente);
            entityTransaction.commit();
        }
    }

    public static ClientesEntity getClientePorCodigo(int codigo){
        return ConexionBD.getEm().find(ClientesEntity.class, codigo);
    }

}
