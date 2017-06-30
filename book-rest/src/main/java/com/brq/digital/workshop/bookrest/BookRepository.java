package com.brq.digital.workshop.bookrest;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {

}