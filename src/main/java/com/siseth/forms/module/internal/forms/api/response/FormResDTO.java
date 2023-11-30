package com.siseth.forms.module.internal.forms.api.response;


import com.siseth.forms.module.internal.forms.api.AttributesDTO;
import com.siseth.forms.module.internal.forms.component.entity.FormAttribute;
import com.siseth.forms.module.internal.forms.component.entity.FormHeader;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
public class FormResDTO {
    private Long headerId;
    private String name;

    private List<AttributesDTO> attributes;

    public FormResDTO(FormHeader dto) {
        this.headerId = dto.getId();
        this.name = dto.getName();
        this.attributes = Optional.ofNullable(dto.getHeader2attributeList())
                .map(list -> list
                                    .stream()
                                    .map(h2a -> new AttributesDTO(h2a))
                                    .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
}
