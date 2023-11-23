package com.nostra.nostramovieapps.repository;

import com.nostra.nostramovieapps.entity.person.PersonDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDetailRepository extends JpaRepository<PersonDetail, Long>, JpaSpecificationExecutor<PersonDetail> {
}
