package ua.kiev.sinenko.otpservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"access_group\"")
public class AccessGroupEntity implements Serializable {
    @Id
    @Column(name = "\"id\"")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accgrp_seq")
    @SequenceGenerator(name = "accgrp_seq", sequenceName = "accgrp_seq", allocationSize = 1)
    @Type(type = "pg-uuid")
    private Long id;

    @Column(name = "\"group_name\"")
    private String name;

    @Version
    @Column(name = "\"version\"")
    private Integer version;
}
