package com.example.jwt_anhchinh2007.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users",uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Size(min = 6, max = 50)
    private String name;
    @NotBlank
    @Size(min = 6, max = 50)
    private String username;
    @NotBlank
    @Size(max = 60)
    @Email
    private String email;
    @NotBlank
    @JsonIgnore
    @Size(min = 5, max = 200)
    private String password;
    @Lob
    private String avatar;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles = new HashSet<>();

    public Users(String name, String username, String email, String password, String avatar, Set<Role> roles) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.roles = roles;
    }

    public Users(@NotBlank @Size(min = 6, max = 50)String name,
                 @NotBlank @Size(min = 6, max = 50)String username,
                 @Size(max = 60) @Email String email,
                 @NotBlank @Size(min = 5, max = 200) String encode) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = encode;
    }
}
