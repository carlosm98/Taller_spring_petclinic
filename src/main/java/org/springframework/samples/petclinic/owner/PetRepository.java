package org.springframework.samples.petclinic.owner;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long>{
    
	List<Pet> findByBirthDate(LocalDate birthDate, Sort sort);

	List<Pet> findByBirthDateBetween(LocalDate dmenor, LocalDate dmayor, Sort sort);
	Pet findByName(String name);
}
