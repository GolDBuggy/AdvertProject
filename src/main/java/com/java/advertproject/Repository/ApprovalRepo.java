package com.java.advertproject.Repository;

import com.java.advertproject.Model.Advert;
import com.java.advertproject.Model.Approval;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApprovalRepo extends JpaRepository<Approval,String> {

    List<Approval> findApprovalsByAdvert_Status(String status);


    void deleteByAdvert_Id(long id);

}
