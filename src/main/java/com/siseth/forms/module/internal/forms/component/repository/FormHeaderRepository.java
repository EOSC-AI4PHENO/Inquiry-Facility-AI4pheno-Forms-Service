package com.siseth.forms.module.internal.forms.component.repository;

import com.siseth.forms.module.internal.forms.component.entity.FormAttribute;
import com.siseth.forms.module.internal.forms.component.entity.FormHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormHeaderRepository extends JpaRepository<FormHeader, Long> {
    Optional<FormHeader> findById(Long id);

    Optional<FormHeader> findByName(String name);

    Optional<FormHeader> findByType(String type);

    Optional<FormHeader> findByTypeAndName(String type, String name);
}
