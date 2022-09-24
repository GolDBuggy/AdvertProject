package com.java.advertproject.Repository;

import com.java.advertproject.Model.Advert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertRepo extends JpaRepository<Advert,Long> {
}
