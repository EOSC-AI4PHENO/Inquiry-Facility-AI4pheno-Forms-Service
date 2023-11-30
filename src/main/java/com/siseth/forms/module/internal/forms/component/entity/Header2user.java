package com.siseth.forms.module.internal.forms.component.entity;

import com.siseth.forms.component.entity.BaseEntity;
import com.siseth.forms.module.internal.forms.api.request.FormReqDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "`public`", name = "`header2user`")
public class Header2user extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private Long id;

    @Column(name="`headerId`")
    private Long headerId;

    @Column(name="`userId`")
    private String userId;

    @Column(name="`realm`")
    private String realm;

    @Column(name="`action`")
    @Enumerated(EnumType.STRING)
    private ACTION_TYPE action;

    @OneToMany(mappedBy = "header2user", fetch = FetchType.LAZY)
    private List<HeaderDetails> headerDetailsList;

    @Transient
    public HeaderDetails getDetailByParameter(String value) {
        return this.headerDetailsList.stream()
                .filter(x -> x.getHeader2attribute().getAttribute().getName().equals(value))
                .findFirst()
                .orElse(null);
    }

    @Transient
    public Map<String, String> getValues() {
        Map<String, String> result = new HashMap<>();
        headerDetailsList
                .forEach(x -> result.putIfAbsent(x.getHeader2attribute().getAttribute().getName(), x.getValue()));
        return result;

    }


    public Header2user(Long headerId, String userId, String realm) {
        this.headerId = headerId;
        this.userId = userId;
        this.realm = realm;
        this.action = ACTION_TYPE.WAITING;
    }

    public enum ACTION_TYPE {
        WAITING,
        ACCEPTED,
        DECLINED

    }
}