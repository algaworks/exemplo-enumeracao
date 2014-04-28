package com.algaworks.exemplo;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.algaworks.exemplo.modelo.Liberacao;
import com.algaworks.exemplo.modelo.Usuario;
import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.JPAHelper;

public class TesteEnum {

	private JIntegrity helper;
	
	private EntityManager manager;
	
	@Before
	public void init() {
		helper = new JIntegrity();
		helper.useMySQL();
		
		helper.cleanAndInsert();
		
		this.manager = JPAHelper.currentEntityManager();
		
		if (!this.manager.getTransaction().isActive()) {
			this.manager.getTransaction().begin();
		}
	}
	
	@After
	public void end() {
		if (this.manager != null) {
			this.manager.getTransaction().commit();
		}
	}
	
	@Test
	public void deveRetornarUsuario() {
		Usuario u = this.manager.find(Usuario.class, 1L);
		
		System.out.println(u.getNome());
		for (Liberacao liberacao : u.getLiberacoes()) {
			System.out.println(liberacao);
		}
	}
	
}
