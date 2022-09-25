package com.java.advertproject.Repository;

import com.java.advertproject.Model.Advert;
import com.java.advertproject.Model.Approval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalRepo extends JpaRepository<Approval,String> {

    Approval findByAdvert_Id(long id);
    void deleteByAdvert_Id(long id);

}
