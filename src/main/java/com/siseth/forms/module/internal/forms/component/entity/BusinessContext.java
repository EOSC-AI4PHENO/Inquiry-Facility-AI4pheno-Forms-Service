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
@Table(schema = "`public`", name = "`businessContext`")
public class BusinessContext extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private Long id;

    @Column(name="`name`")
    private String name;

    @OneToMany(mappedBy = "business", fetch = FetchType.LAZY)
    private List<Business2header> business2headerList;
}