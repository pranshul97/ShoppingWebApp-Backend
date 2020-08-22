package com.project.repository;

import javax.transaction.Transactional;

import com.project.entity.Retailers;
import com.project.entity.User;

public interface RetailerRepo {

	void save(User ret);

}