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
        cliente.setNombres(nombres);
        cliente.setApellidos(apellidos);
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);
        cliente.setCorreo(correo);
        cliente.setIfEocr(ifeOcr);
        ConexionBD.getEm().persist(cliente);
        return cliente;
    }


    public static void eliminarCliente(int codigo){
        ClientesEntity cliente = getClientePorCodigo(codigo);
        if(cliente != null){
            ConexionBD.getEm().remove(cliente);
        }
    }

    public static ClientesEntity getClientePorCodigo(int codigo){
        return ConexionBD.getEm().find(ClientesEntity.class, codigo);
    }

}
