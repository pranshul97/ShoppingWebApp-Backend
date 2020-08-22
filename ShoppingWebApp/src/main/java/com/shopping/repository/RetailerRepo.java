package com.shopping.repository;

import javax.transaction.Transactional;

import com.shopping.entity.Retailers;

public interface RetailerRepo {

	void save(Retailers ret);

}