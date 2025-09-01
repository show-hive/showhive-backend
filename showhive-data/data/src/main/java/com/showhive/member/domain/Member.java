package com.showhive.member.domain;

import com.showhive.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    protected Member() {
    }

    private Member(String name) {
        this.name = name;
    }

    public static Member create(String name) {
        return new Member(name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
