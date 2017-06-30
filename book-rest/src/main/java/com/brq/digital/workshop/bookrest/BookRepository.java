package com.brq.digital.workshop.bookrest;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {

}