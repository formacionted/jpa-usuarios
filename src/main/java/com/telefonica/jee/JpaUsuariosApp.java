package com.telefonica.jee;

import java.util.List;

import com.telefonica.jee.dao.UsuarioDAOImpl;
import com.telefonica.jee.entities.Usuario;

public class JpaUsuariosApp {

	
	
	public static void main(String[] args) {

		
		UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();
		
		// metodo login
		System.out.println(usuarioDAOImpl.login("sdfdsdfsdf", "5454543213")	);
		System.out.println(usuarioDAOImpl.login("alan@alansastre.co", "admin")	);
		
		// metodo find
		Usuario usuario1 = usuarioDAOImpl.findById(1l);
		System.out.println(usuario1);

		// metodo findAll
		List<Usuario> usuarios = usuarioDAOImpl.findAll();
		System.out.println(usuarios.size());
		System.out.println(usuarios);
		
		// metodo create
		Usuario usuario = new Usuario("Farinfanfunfi", "Rigatuso",
				"654654654", "farin@google.com", "1234"); 
		usuario = usuarioDAOImpl.create(usuario);
		System.out.println(usuario);
		
		
		// metodo update
		usuario.setEmail("cursojava@telefonica.com");
		usuario = usuarioDAOImpl.update(usuario);
		System.out.println(usuario);
		
		
		// metodo delete
		usuarioDAOImpl.delete(1l);
		
		
		
	}

}
