package com.example.koffie_app;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @NonNull
    @PrimaryKey
    private String username;

    @ColumnInfo
    private String email;

    @ColumnInfo
    private String password;

    @ColumnInfo
    private String access_token;

    public User(String username, String email, String password, String access_token){
        this.username = username;
        this.email = email;
        this.password = password;
        this.access_token = access_token;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccess_token(String token){
        this.access_token = token;
    }
}
