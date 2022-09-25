package com.java.advertproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "approval")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Approval {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "approval_id")
    private String id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "advert_id")
    private Advert advert;

    @Column(name = "advert_confirm")
    private String confirmLink;

    @Column(name = "advert_delete")
    private String deleteLink;
}
