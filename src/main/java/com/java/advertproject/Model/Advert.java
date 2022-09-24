package com.java.advertproject.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "advert")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "advert_id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "created_time")
    private LocalDate createdTime;

    @Column(name = "updated_time")
    private LocalDate updatedTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
