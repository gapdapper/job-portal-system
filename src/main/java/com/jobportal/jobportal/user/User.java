package com.jobportal.jobportal.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jobportal.jobportal.entitiy.Applicant;
import com.jobportal.jobportal.entitiy.Company;
import com.jobportal.jobportal.token.Token;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

  @Id
  @GeneratedValue
  private Integer id;
  private String parentId;
  private String firstname;
  private String lastname;
  @Column(unique = true)
  private String username;
  private String email;
  private String password;
  private Boolean enabled;

  @OneToOne(mappedBy = "user")
  private Applicant applicant;


  @OneToOne(mappedBy = "user")
  private Company company;

  @Enumerated(EnumType.STRING)
  @ElementCollection
  @Builder.Default
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<Role> roles = new ArrayList<>();

  @OneToMany(mappedBy = "user")
  private List<Token> tokens;
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
}
