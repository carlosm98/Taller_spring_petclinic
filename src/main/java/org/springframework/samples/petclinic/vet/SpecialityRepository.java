package org.springframework.samples.petclinic.vet;

import java.util.Collection;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SpecialityRepository extends Repository<Specialty, Integer> {


    @Transactional(readOnly = true)
    @Cacheable("specialties")
    Collection<Vet> findAll() throws DataAccessException;

    @Transactional(readOnly = true)
    @Cacheable("specialties")

    Page<Vet> findAll(Pageable pageable) throws DataAccessException;

    void save (Specialty specialty);

    Specialty findById(@Param("id") Integer id);
}
