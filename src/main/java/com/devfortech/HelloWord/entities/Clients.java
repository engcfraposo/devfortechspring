package com.devfortech.HelloWord.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.devfortech.HelloWord.dto.ClientsDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_clients")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class Clients implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private @NonNull Long id;
	private @NonNull String name;
	private @NonNull String cpf;
	private @NonNull String email;
	private @NonNull String endereco;
	private @NonNull String cep;
	private @NonNull String city;
	private @NonNull String country;
	private @NonNull String username;
	private @NonNull String password;
	
	@OneToMany(mappedBy = "client")
	private Set<Order> orders_id;

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	@CreationTimestamp
	private Instant created_at;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	@UpdateTimestamp
	private Instant updated_at;

	public Clients(ClientsDTO dto) {
		this.id = dto.getId();
		this.name = dto.getName();
		this.cpf = dto.getCpf();
		this.email = dto.getEmail();
		this.endereco = dto.getEndereco();
		this.cep = dto.getCep();
		this.city = dto.getCity();
		this.country = dto.getCountry();
		this.username = dto.getUsername();
		this.password = dto.getPassword();
	}

	public Clients(Long id, ClientsDTO dto) {
		this.id = id;
		this.name = dto.getName();
		this.cpf = dto.getCpf();
		this.email = dto.getEmail();
		this.endereco = dto.getEndereco();
		this.cep = dto.getCep();
		this.city = dto.getCity();
		this.country = dto.getCountry();
		this.username = dto.getUsername();
		this.password = dto.getPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}