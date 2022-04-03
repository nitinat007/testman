package com.testman.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

/**
 * Author: kunitin
 * Created: 03/04/22
 * Info: Info on the test category. eg: UI, API, End-to-End, Integration etc
 **/


@Data
@Entity
@NoArgsConstructor
@Table(name = "test_category")
public class TestCategoryEntity extends AuditExtensionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(unique = true, nullable = false, length = 100)
    private String name;

    @NonNull
    @Column(unique = false, nullable = true, length = 255)
    private String desc;

}
