package com.hea.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hea.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

	List<Product> findByName(String name);

	List<Product> findByNameAndDescription(String name, String description);

	List<Product> findByPriceGreaterThan(double price);

	List<Product> findByDescriptionContains(String description);

	List<Product> findByPriceBetween(Double min, Double max);

	List<Product> findByNameIsLike(String name);

	List<Product> findByIdIn(List<Integer> ids);

}
