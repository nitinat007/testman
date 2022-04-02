package com.testman.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

/**
 * Author: kunitin
 * Created: 19/03/22
 * Info: Product information to persist in db
 **/

@Data
@Entity
@NoArgsConstructor
@Table(name = "product")
public class ProductEntity extends AuditExtensionEntity {

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
