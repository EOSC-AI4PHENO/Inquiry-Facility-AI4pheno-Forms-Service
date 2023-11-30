package com.siseth.forms.module.internal.forms.component.repository;

import com.siseth.forms.module.internal.forms.component.entity.FormHeader;
import com.siseth.forms.module.internal.forms.component.entity.Header2attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Header2attributeRepository extends JpaRepository<Header2attribute, Long> {
    List<Header2attribute> findAllByHeaderId(Long id);

    List<Header2attribute> findAllByIdIn(List<Long> ids);

    Optional<Header2attribute> findById(Long id);

    @Query("SELECT h2a " +
            "FROM Header2attribute h2a " +
            "INNER JOIN FormAttribute fa ON h2a.attribute = fa " +
            "WHERE fa.name = :name ")
    Optional<Header2attribute> getByAttributeName(@Param("name") String name);

}
