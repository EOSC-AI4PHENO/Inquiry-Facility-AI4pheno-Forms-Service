package com.siseth.forms.module.internal.forms.component.entity;

import com.siseth.forms.component.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "`public`", name = "`formAttribute`")
public class FormAttribute extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private Long id;

    @Column(name="`name`")
    private String name;

    @Column(name="`type`")
    private String type;

    @Column(name="`defaultValue`")
    private String defaultValue;

    @Column(name="`values`")
    private String values;

    @Column(name="`parentId`")
    private Long parentId;

    @OneToMany(mappedBy = "attribute", fetch = FetchType.LAZY)
    private List<Header2attribute> header2attributeList;

}