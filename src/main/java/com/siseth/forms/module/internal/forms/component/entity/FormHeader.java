package com.siseth.forms.module.internal.forms.component.entity;

import com.siseth.forms.component.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "`public`", name = "`formHeader`")
public class FormHeader extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private Long id;

    @Column(name="`name`")
    private String name;

    @Column(name="`isActive`")
    private Boolean isActive;

    @Column(name="`type`")
    private String type;

    @OneToMany(mappedBy = "header", fetch = FetchType.LAZY)
    @Where(clause = "`isActive`")
    private List<Header2attribute> header2attributeList;

    @OneToMany(mappedBy = "header", fetch = FetchType.LAZY)
    private List<Business2header> business2headerList;
}