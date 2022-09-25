package com.java.advertproject.Repository;

import com.java.advertproject.Model.Advert;
import com.java.advertproject.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdvertRepo extends JpaRepository<Advert,Long> {

    List<Advert> findAdvertsByUser(User user);

    @Query("select u from Advert u where u.status='Active'")
    List<Advert> getAll(Pageable pageable);
}
