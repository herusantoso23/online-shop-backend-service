package com.herusantoso.tokonezia.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User extends BaseDomain implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @Column(name = "username",length = 20, unique = true)
    private String username;

    @Column(name = "email",length = 254, unique = true)
    private String email;

    @Column(name = "fullname",length = 100)
    private String fullname;

    @Column(name = "password",length = 100)
    private String password;

    @Column(name = "phone_number", length = 12)
    private String phoneNumber;

    @Column(name = "profile_image", length = 1000)
    private String profileImage;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "account_locked")
    private boolean accountNonLocked;

    @Column(name = "account_expired")
    private boolean accountNonExpired;

    @Column(name = "credentials_expired")
    private boolean credentialsNonExpired;

    @NotNull
    @Column(nullable = false)
    private boolean isEnabled = false;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(name = "role_user", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id") })
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            String name = role.getName().toUpperCase();
            if (!name.startsWith("ROLE_")) {
                name = "ROLE_" + name;
            }
            authorities.add(new SimpleGrantedAuthority(name));
        }
        return authorities;
    }

    @PrePersist
    public void prePersist(){
        super.prePersist();
        this.isEnabled = true;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.balance = new BigDecimal(0);
    }
}
