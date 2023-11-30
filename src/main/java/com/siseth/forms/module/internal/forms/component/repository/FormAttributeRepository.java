package com.siseth.forms.module.internal.forms.component.repository;

import com.siseth.forms.module.internal.forms.component.entity.FormAttribute;
import com.siseth.forms.module.internal.forms.component.entity.FormHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormAttributeRepository extends JpaRepository<FormAttribute, Long> {
    Optional<FormAttribute> findByName(String name);
}
