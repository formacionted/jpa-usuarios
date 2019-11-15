package com.telefonica.jee.dao;

import java.util.List;

import com.telefonica.jee.entities.Usuario;

/**
 * Interface for database persistence operations
 */
public interface UsuarioDAO {


	/**
	 * Recibe como parámetro el string login y el string password y ejecuta
	 * una query contra la base de datos verificando si hay algún usuario con esos
	 * datos. Si lo hay devuelve true, si no false.
	 * @param login
	 * @param password
	 * @return true o false si existe el usuario
	 */
	public boolean login(String email, String password);
	
	/**
	 * recibe un id, busca y devuelve el usuario por ese id
	 * @param id
	 * @return un usuario
	 */
	public Usuario findById(Long id);
	
	/**
	 * no recibe parametros, devuelve una lista de todos los usuarios de la base de datos
	 * @return lista de usuarios
	 */
	public List<Usuario> findAll();
	
	/**
	 * Recibe un objeto usuario y lo almacena en la base de datos
	 * @param usuario
	 * @return
	 */
	public Usuario create(Usuario usuario);
	
	/**
	 * recibe un objeto usuario y lo actualiza en la base de datos
	 * @param usuario
	 * @return
	 */
	public Usuario update(Usuario usuario);
	
	
/**
 * Actualiza la password de un usuario a partir de su id
 * @param userId
 * @param newPass
 * @return
 */
	public Usuario updatePassword(Long userId, String newPass);
	
	
	/**
	 * Recibe un id y borra el usuario de la base de datos correspondiente a ese id
	 * @param id
	 */
	public void delete(Long id);
	

	/**
	 * Filtrar un usuario por sus propiedades con combinaciones 
	 * @param usuario
	 * @return
	 */
	public List<Usuario> filterByCriteria(Usuario usuario); 
		
	
}
