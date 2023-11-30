package com.siseth.forms.module.internal.forms.component.repository;

import com.siseth.forms.module.internal.forms.component.entity.Header2user;
import com.siseth.forms.module.internal.forms.component.entity.HeaderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeaderDetailsRepository extends JpaRepository<HeaderDetails, Long> {

}
