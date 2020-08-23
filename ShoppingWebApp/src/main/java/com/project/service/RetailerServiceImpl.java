//-----------------RetailerServiceImpl by bhavya---------------------------------------------------------------------------------------
package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.project.entity.Retailers;
import com.project.exception.RetailerServiceException;
import com.project.repository.RetailerRepository;

@Service
public class RetailerServiceImpl implements RetailerService {
	
	@Autowired
	private RetailerRepository retailerRepository;

	@Override
	public void addRetailer(Retailers retailers){
		
		if(!retailerRepository.isRetailerPresent(retailers.getEmail())) {
			retailerRepository.addRetailer(retailers);
		}
		else {
			throw new RetailerServiceException("Retailer Already Registered");
		}
				
	}

	@Override
	public Retailers login(String email, String password) {
		try {
			if(!retailerRepository.isRetailerPresent(email))
				throw new RetailerServiceException("Retailer needs to be registered");
			
			int retailerId = (int) retailerRepository.findByEmailAndPassword(email, password);
			Retailers retailers = retailerRepository.findById(retailerId);
			return retailers;
		}
		catch (EmptyResultDataAccessException e) {
			throw new RetailerServiceException("Incorrect username or password.Try again with different combination" );
		}
	}

	

}
