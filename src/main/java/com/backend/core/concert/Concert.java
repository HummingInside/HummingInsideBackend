package com.backend.core.concert;

import javax.persistence.*;

@Entity
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private String name;
}
