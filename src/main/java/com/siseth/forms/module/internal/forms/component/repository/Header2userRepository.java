package com.siseth.forms.module.internal.forms.component.repository;

import com.siseth.forms.module.internal.forms.component.entity.FormHeader;
import com.siseth.forms.module.internal.forms.component.entity.Header2attribute;
import com.siseth.forms.module.internal.forms.component.entity.Header2user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Header2userRepository extends JpaRepository<Header2user, Long> {
    List<Header2user> findAllByActionAndRealm(Header2user.ACTION_TYPE type, String realm);

    Optional<Header2user> findDistinctByActionAndRealmAndUserId(Header2user.ACTION_TYPE type, String realm, String userId);

    Optional<Header2user> findDistinctByRealmAndUserId(String realm, String userId);

    Optional<Header2user> findFirstByActionAndRealmAndUserId(Header2user.ACTION_TYPE type, String realm, String userId);
}
