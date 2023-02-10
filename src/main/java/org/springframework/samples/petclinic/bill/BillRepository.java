package org.springframework.samples.petclinic.bill;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends CrudRepository<Bill, Long> {

	public List<Bill> findAll();
	
	public List<Bill> findAll(Sort sort);
}