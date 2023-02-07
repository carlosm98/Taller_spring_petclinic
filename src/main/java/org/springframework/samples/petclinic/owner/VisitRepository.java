package org.springframework.samples.petclinic.owner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends CrudRepository<Visit, Long> {

	Page<Pet> findAll(Pageable pageable);
}