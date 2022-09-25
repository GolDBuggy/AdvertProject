package com.java.advertproject.Repository;

import com.java.advertproject.Model.Advert;
import com.java.advertproject.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertRepo extends JpaRepository<Advert,Long> {

    List<Advert> findAdvertsByUser(User user);
}
