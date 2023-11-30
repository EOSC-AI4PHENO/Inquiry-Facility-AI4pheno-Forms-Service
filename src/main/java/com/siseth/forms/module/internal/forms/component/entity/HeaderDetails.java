package com.siseth.forms.module.internal.forms.component.entity;

import com.siseth.forms.component.entity.BaseEntity;
import com.siseth.forms.module.internal.forms.api.AttributeValDTO;
import com.siseth.forms.module.internal.forms.api.request.FormReqDTO;
import com.siseth.forms.module.internal.forms.component.repository.Header2attributeRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "`public`", name = "`headerDetails`")
public class HeaderDetails extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private Long id;

    @JoinColumn(name="`header2userId`")
    @ManyToOne
    private Header2user header2user;

    @JoinColumn(name="`header2attributeId`")
    @ManyToOne
    private Header2attribute header2attribute;

    @Column(name="`value`")
    private String value;


    public HeaderDetails(Header2user header2user, String value, Header2attribute h2a) {
        this.header2user = header2user;
        this.header2attribute = h2a;
        this.value = value;
    }
}