package com.siseth.forms.module.internal.forms.api.request;


import com.siseth.forms.module.internal.forms.api.AttributeValDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FormReqDTO {

    private String firstName;
    private String lastName;
    private String organisation;
    private String country;
    private String position;
    private String scope;
    private List<String> purpose;
    private String project;


}

