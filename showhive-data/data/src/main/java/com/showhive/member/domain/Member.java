package com.showhive.member.domain;

import com.showhive.BaseEntity;
import com.showhive.file.domain.File;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;


    @OneToOne
    @JoinColumn(name = "profile_file_id")
    private File profileFile;

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
