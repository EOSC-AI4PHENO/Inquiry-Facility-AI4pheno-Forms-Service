package com.siseth.forms.module.internal.forms.services;

import com.siseth.forms.module.internal.forms.api.AttributeValDTO;
import com.siseth.forms.module.internal.forms.api.request.FormReqDTO;
import com.siseth.forms.module.internal.forms.api.response.FormResDTO;
import com.siseth.forms.module.internal.forms.api.response.UsersForVerificationDTO;
import com.siseth.forms.module.internal.forms.component.entity.*;
import com.siseth.forms.module.internal.forms.component.repository.*;
import com.siseth.forms.module.internal.forms.constant.AttributeName;
import com.siseth.forms.module.internal.forms.feign.user.UserService;
import com.siseth.forms.module.internal.forms.feign.user.dto.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FormsService {

    private final FormHeaderRepository headerRepository;
    private final Header2userRepository header2userRepository;
    private final HeaderDetailsRepository headerDetailsRepository;
    private final Header2attributeRepository header2attributeRepository;
    private final FormAttributeRepository formAttributeRepository;

    private final UserService userService;

    public FormResDTO getFormByType(String type, String name) {
        FormHeader header = headerRepository.findByTypeAndName(type, name)
                .orElseThrow(() -> new RuntimeException("Form not found!"));
        return new FormResDTO(header);

    }
    @Transactional
    public String saveForm(FormReqDTO dto, String userId, String realm) {
        Long formHeaderId = headerRepository.findByName("verification").orElseThrow(() -> new EntityNotFoundException("Header not found")).getId();
        Header2user header2user = new Header2user(formHeaderId, userId, realm);
        header2userRepository.save(header2user);

        headerDetailsRepository.save(new HeaderDetails(header2user, dto.getFirstName(), header2attributeRepository.getByAttributeName(AttributeName.NAME).orElseThrow(() -> new RuntimeException("Attribute not found!"))));
        headerDetailsRepository.save(new HeaderDetails(header2user, dto.getLastName(), header2attributeRepository.getByAttributeName(AttributeName.SURNAME).orElseThrow(() -> new RuntimeException("Attribute not found!"))));
        headerDetailsRepository.save(new HeaderDetails(header2user, dto.getOrganisation(), header2attributeRepository.getByAttributeName(AttributeName.ORGANISATION).orElseThrow(() -> new RuntimeException("Attribute not found!"))));
        headerDetailsRepository.save(new HeaderDetails(header2user, dto.getCountry(), header2attributeRepository.getByAttributeName(AttributeName.COUNTRY).orElseThrow(() -> new RuntimeException("Attribute not found!"))));
        headerDetailsRepository.save(new HeaderDetails(header2user, dto.getPosition(), header2attributeRepository.getByAttributeName(AttributeName.POSITION).orElseThrow(() -> new RuntimeException("Attribute not found!"))));
        headerDetailsRepository.save(new HeaderDetails(header2user, dto.getScope(), header2attributeRepository.getByAttributeName(AttributeName.SCOPE).orElseThrow(() -> new RuntimeException("Attribute not found!"))));
        headerDetailsRepository.save(new HeaderDetails(header2user, StringUtils.join(dto.getPurpose(), ";"), header2attributeRepository.getByAttributeName(AttributeName.PURPOSE).orElseThrow(() -> new RuntimeException("Attribute not found!"))));
        headerDetailsRepository.save(new HeaderDetails(header2user, dto.getProject(), header2attributeRepository.getByAttributeName(AttributeName.PROJECT).orElseThrow(() -> new RuntimeException("Attribute not found!"))));

        return "OK";
    }

    public List<UsersForVerificationDTO> getUsersForVerification(String realm) {
        List<Header2user> header2userList = header2userRepository.findAllByActionAndRealm(Header2user.ACTION_TYPE.WAITING, realm);
        List<String> ids = header2userList.stream().map(Header2user::getUserId).collect(Collectors.toList());

        List<UserInfoDTO> userEmails = userService.getUsersInfo(ids, realm);

        Map<String, String> userEmailMap = userEmails.stream()
                .collect(Collectors.toMap(UserInfoDTO::getId, UserInfoDTO::getEmail));

        List<UsersForVerificationDTO> usersForVerification = header2userList.stream().map(header2user -> {
            UsersForVerificationDTO dto = new UsersForVerificationDTO(header2user);
            String email = userEmailMap.get(header2user.getUserId());
            dto.setEmail(email);
            return dto;
        }).filter(dto -> dto.getEmail() != null)
          .collect(Collectors.toList());

        return usersForVerification;

    }

    public String acceptForm(String realm, String userId) {
        Header2user header2user = header2userRepository.findDistinctByActionAndRealmAndUserId(Header2user.ACTION_TYPE.WAITING, realm, userId).orElseThrow( () -> new EntityNotFoundException("Users not found"));

        header2user.setAction(Header2user.ACTION_TYPE.ACCEPTED);
        header2userRepository.save(header2user);
        return "Successfully accepted user of id: " + userId;
    }

    public String rejectForm(String realm, String userId) {
        Header2user header2user = header2userRepository.findDistinctByActionAndRealmAndUserId(Header2user.ACTION_TYPE.WAITING, realm, userId).orElseThrow( () -> new EntityNotFoundException("Users not found"));

        header2user.setAction(Header2user.ACTION_TYPE.DECLINED);
        header2userRepository.save(header2user);
        return "Successfully rejected user of id: " + userId;
    }

    public Object getStatus(String userId, String realm) {
        return header2userRepository.findDistinctByRealmAndUserId(realm, userId).orElseThrow( () -> new RuntimeException("Not found!")).getAction();
    }


}
