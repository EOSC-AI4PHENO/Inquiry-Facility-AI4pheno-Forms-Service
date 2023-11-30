package com.siseth.forms.module.internal.forms.api.response;


import com.siseth.forms.module.internal.forms.api.AttributesDTO;
import com.siseth.forms.module.internal.forms.component.entity.FormHeader;
import com.siseth.forms.module.internal.forms.component.entity.Header2user;
import com.siseth.forms.module.internal.forms.constant.AttributeName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class UsersForVerificationDTO {
    private String userId;
    private String firstName;
    private String lastName;
    private String organisation;
    private String country;
    private String position;
    private String scope;
    private List<String> purpose;
    private String project;
    private OffsetDateTime submissionDate;
    private String email;

    public UsersForVerificationDTO(Header2user user) {
        Map<String, String> map = user.getValues();
        this.firstName = map.get(AttributeName.NAME);
        this.lastName = map.get(AttributeName.SURNAME);
        this.organisation = map.get(AttributeName.ORGANISATION);
        this.country = map.get(AttributeName.COUNTRY);
        this.position = map.get(AttributeName.POSITION);
        this.scope = map.get(AttributeName.SCOPE);
        this.purpose = Arrays.asList(map.get(AttributeName.PURPOSE).split(";"));
        this.project = map.get(AttributeName.PROJECT);
        this.userId = user.getUserId();
        this.submissionDate = user.getCreatedAt();
        this.email = null;
    }
}
