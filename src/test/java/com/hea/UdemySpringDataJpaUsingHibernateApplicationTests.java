package com.hea;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.hea.entities.Product;
import com.hea.repository.ProductRepository;

@SpringBootTest
class UdemySpringDataJpaUsingHibernateApplicationTests {

	@Autowired
	private ProductRepository repo;

	@Autowired
	private EntityManager e;

	@Test
	void contextLoads() {
	}

	@Test
	public void createProduct() {
		// Product p = new Product("TV PLAZMA", "this is my description", 180.3);
		// Product p1 = new Product("Iphone X3", "this is my Iphone", 1000.3);
		Product p2 = new Product("LG Machine", "From LG ", 1800.3);
		repo.save(p2);
	}

	@Test
	// @Transactional
	public void findSpecificProduct() {
		// Optional<Product> p = repo.findById(1);
		// System.out.println(p.get().getDescription());
		repo.findById(1);
		repo.findById(1);
		repo.findById(1);
	}

	@Test
	public void updateProduct() {
		Optional<Product> p = repo.findById(3);
		// p.get().setPrice(100.2);
		p.get().setDescription("This is a good drayer");
		System.out.println(">>>>>>>>>>>>>>>>>>>>> updated" + p.get().getName());
		System.out.println(">>>>>>>>>>>>>>>>>>>>> updated" + p.get().getDescription());
		repo.save(p.get());
	}

	@Test
	public void deleteProduct() {
		if (repo.existsById(3)) {
			repo.deleteById(3);
			System.out.println(">>>>>>>>>>>>>>>>>>>>> deleted");
		}
	}

	@Test
	public void findAllProducts() {

		List<Product> list = new ArrayList<>();
		Iterable<Product> findAll = repo.findAll();
		findAll.forEach(list::add);
		list.forEach(p -> System.out.println(p.getDescription()));
	}

	@Test
	public void countRecords() {
		System.out.println(">>>>>>>>>>>>>> counting records: ");
		System.out.println(repo.count());
	}

	@Test
	@Transactional
	public void testCaching() {
		Session s = e.unwrap(Session.class);
		Optional<Product> p;
		p = repo.findById(1);
		s.evict(p.get());
		repo.findById(1);

	}

}
