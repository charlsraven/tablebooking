package com.voronina.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.Set;

import static javax.persistence.CascadeType.REMOVE;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "guests")
public class Guest {
    @Id
    @JsonIgnore
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Length(min = 1)
    private String name;

    @Email
    private String email;

    @Length(min = 10, max = 11)
    private String phone;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate birthday;

    @OneToMany(cascade = REMOVE, mappedBy = "guest", fetch = FetchType.EAGER)
    private Set<Booking> booking;
}
