package com.glsid.medapp.dao;

import com.glsid.medapp.modele.Exception;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExceptionRepository extends JpaRepository<Exception, Long> {
}
