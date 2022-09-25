package com.java.advertproject.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "advert_url")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Url {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "url_id")
    private String id;

    @Column(name = "advert_url")
    private String advertUrl;

    @Column(name = "click_count")
    private long clickCount;

    @OneToOne
    @JoinColumn(name = "advert_id")
    @JsonIgnore
    private Advert advert;
}
