package com.siseth.forms.module.internal.forms.api;


import com.siseth.forms.module.internal.forms.component.entity.Header2attribute;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttributesDTO {

    private Long header2attributeId;
    private String name;
    private String type;
    private String defaultValue;
    private String values;
    private Boolean isRequired;

    public AttributesDTO(Header2attribute dto) {
        this.name = dto.getAttribute().getName();
        this.type = dto.getAttribute().getType();
        this.defaultValue = dto.getAttribute().getDefaultValue();
        this.values = dto.getAttribute().getValues();
        this.isRequired = dto.getIsRequired();
        this.header2attributeId = dto.getId();
    }
}
