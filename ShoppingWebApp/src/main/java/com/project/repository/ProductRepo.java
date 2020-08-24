package com.project.repository;

import com.project.entity.Product;

public interface ProductRepo {

	Product fetchById(int id);
}