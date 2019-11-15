package com.telefonica.jee.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.telefonica.jee.entities.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

	private static final String USER_COUNT = "SELECT COUNT(u) FROM Usuario u "
			+ "WHERE u.email = :email AND u.password = :password";

	private EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("jpa-usuarios");
	private EntityManager manager;

	public UsuarioDAOImpl() {
		manager = managerFactory.createEntityManager();
	}

	@Override
	public boolean login(String email, String password) {

		TypedQuery<Long> query = manager.createQuery(USER_COUNT, Long.class);
		query.setParameter("email", email);
		query.setParameter("password", password);
		Long numUsuario = query.getSingleResult();
		System.out.println("Numero de usuarios con email y password: " + numUsuario);

		return numUsuario > 0;
	}

	@Override
	public Usuario findById(Long id) {
		if (id == null) {
			return null;
		}
		return manager.find(Usuario.class, id);
	}

	@Override
	public List<Usuario> findAll() {

		TypedQuery<Usuario> namedQuery = manager.createNamedQuery("Usuario.findAll", Usuario.class);
		List<Usuario> usuarios = namedQuery.getResultList();
		return usuarios;
	}

	@Override
	public Usuario create(Usuario usuario) {
		if (usuario == null || usuario.getId() != null) {
			return usuario;
		}
		try {
			manager.getTransaction().begin();
			manager.persist(usuario);
			manager.getTransaction().commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
		return usuario;
	}

	@Override
	public Usuario updatePassword(Long userId, String newPass) {
		if (userId == null) {
			return null;
		}
		manager.getTransaction().begin();
		Usuario userDB = this.findById(userId);
		userDB.setPassword(newPass);
		manager.getTransaction().commit();
		return userDB;
	}

	@Override
	public Usuario update(Usuario usuario) {
		if (usuario.getId() == null) {
			return null;
		}
		manager.getTransaction().begin();
		Usuario usuarioManaged = manager.merge(usuario);
		usuario.setNombre("dlfjksjkdf");
		usuarioManaged.setNombre("sdfgsgfg");
		manager.getTransaction().commit();

		return usuarioManaged;
	}

	@Override
	public void delete(Long id) {

		// jpql
//		manager.getTransaction().begin();
//		String jpql = "DELETE FROM Usuario u WHERE u.id = :id ";
//		Query queryJpql = manager.createQuery(jpql);
//		queryJpql.setParameter("id", id);
//		int num = queryJpql.executeUpdate();
//		System.out.println("Numero entidades borradas " + num);
//		manager.getTransaction().commit();

		// native sql
//		manager.getTransaction().begin();
//		String sql = "DELETE FROM usuario WHERE id = " + id;
//		Query querySql = manager.createNativeQuery(sql);
//		int num = querySql.executeUpdate();
//		System.out.println("Numero entidades borradas " + num);
//		manager.getTransaction().commit();

		// manager remove
		manager.getTransaction().begin();
		manager.remove(manager.find(Usuario.class, id));
		manager.getTransaction().commit();
	}

	@Override
	public List<Usuario> filterByCriteria(Usuario usuario) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);

		Root<Usuario> root = criteria.from(Usuario.class);
		List<Predicate> predicates = new ArrayList<>();

		if (usuario.getApellido() != null) {
			predicates.add(builder.equal(root.get("apellido"), usuario.getApellido()));
		}

		criteria.where(predicates.toArray(new Predicate[] {}));
		List<Usuario> usuarios = manager.createQuery(criteria).getResultList();

		return usuarios;
	}

}
