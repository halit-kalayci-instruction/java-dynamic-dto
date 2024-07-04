package com.mia.dto.dynamic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="role_dto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDtoMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name="role_id")
    private Role role;

    private String dtoName;
    private List<String> allowedFields;
}
