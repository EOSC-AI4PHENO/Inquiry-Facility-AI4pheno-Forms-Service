package com.siseth.forms.module.internal.forms.component.entity;

import com.siseth.forms.component.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "`public`", name = "`header2attribute`")
@Where(clause = "`isActive`")
public class Header2attribute extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private Long id;

    @JoinColumn(name="`headerId`")
    @ManyToOne
    private FormHeader header;

    @JoinColumn(name="`attributeId`")
    @ManyToOne
    private FormAttribute attribute;

    @Column(name="`isActive`")
    private Boolean isActive;

    @Column(name="`isRequired`")
    private Boolean isRequired;

    @Column(name="`regexValid`")
    private String regexValid;

    @OneToMany(mappedBy = "header2attribute", fetch = FetchType.LAZY)
    private List<HeaderDetails> headerDetailsList;

}