package com.showhive.member.domain;

public enum Role {

    USER(0),
    MANAGER(1),
    ADMIN(2);

    private final int level;

    Role(int level) {
        this.level = level;
    }

    public boolean canAccess(Role otherRole) {
        return this.level >= otherRole.level;
    }
}
