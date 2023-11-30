package com.siseth.forms.module.internal.forms.services;

import com.siseth.forms.module.internal.forms.api.request.FormReqDTO;
import com.siseth.forms.module.internal.forms.api.response.FormResDTO;
import com.siseth.forms.module.internal.forms.api.response.UsersForVerificationDTO;
import com.siseth.forms.module.internal.forms.component.entity.FormHeader;
import com.siseth.forms.module.internal.forms.component.entity.Header2user;
import com.siseth.forms.module.internal.forms.component.entity.HeaderDetails;
import com.siseth.forms.module.internal.forms.component.repository.*;
import com.siseth.forms.module.internal.forms.constant.AttributeName;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FormsInternalService {

    private final FormHeaderRepository headerRepository;
    private final Header2userRepository header2userRepository;
    private final HeaderDetailsRepository headerDetailsRepository;
    private final Header2attributeRepository header2attributeRepository;
    private final FormAttributeRepository formAttributeRepository;

    public UsersForVerificationDTO getUserInfo(String id, String realm) {
        Header2user header2user = header2userRepository.findDistinctByRealmAndUserId(realm, id).orElseThrow( () -> new RuntimeException("not found"));
        return new UsersForVerificationDTO(header2user);
    }

}
