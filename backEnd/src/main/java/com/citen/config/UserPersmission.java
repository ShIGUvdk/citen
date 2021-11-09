package com.citen.config;

public enum UserPersmission {
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String persmission;

    UserPersmission(String permission){
        this.persmission = permission;
    }

    public String getPermission(){
        return persmission;
    }

}
