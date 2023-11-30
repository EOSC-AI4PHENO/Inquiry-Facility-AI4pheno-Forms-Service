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
@Table(schema = "`public`", name = "`business2header`")
public class Business2header extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private Long id;

    @JoinColumn(name="`headerId`")
    @ManyToOne
    private FormHeader header;

    @JoinColumn(name="`businessId`")
    @ManyToOne
    private BusinessContext business;

    @Column(name="`isActive`")
    private Boolean isActive;

}