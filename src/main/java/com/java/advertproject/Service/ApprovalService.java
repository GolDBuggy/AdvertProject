package com.java.advertproject.Service;

import com.java.advertproject.Configuration.MessageConfig;
import com.java.advertproject.Model.Advert;
import com.java.advertproject.Model.Approval;
import com.java.advertproject.Repository.AdvertRepo;
import com.java.advertproject.Repository.ApprovalRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ApprovalService {

    private final ApprovalRepo approvalRepo;
    private final AdvertRepo advertRepo;
    private final RabbitTemplate rabbitTemplate;

    private static Logger logger=Logger.getLogger(ApprovalService.class.getName());

    private static String LINK="http://localhost:8080/approval/";
    private static String STATUS_CONFIRM="Active";


    public List<Approval> all(){
        return approvalRepo.findApprovalsByAdvert_Status("Passive");
    }

    public void save(Approval approval){
        approvalRepo.save(setLink(approval));
    }

    private Approval setLink(Approval approval){
        approval.setConfirmLink(LINK+"confirm?id="+approval.getAdvert().getId());
        approval.setDeleteLink(LINK+"delete?id="+approval.getAdvert().getId());
        return approval;
    }


    public void confirmAdvert(long id){
        Advert advert=advertRepo.findById(id).get();
        advert.setStatus(STATUS_CONFIRM);
        advertRepo.save(advert);
        rabbitTemplate.convertAndSend(MessageConfig.EXCHANGE,MessageConfig.ROUTING_KEY,advert);
    }

    @Transactional
    public void deleteAdvert(long id){
        approvalRepo.deleteByAdvert_Id(id);
        advertRepo.deleteById(id);
    }

    @RabbitListener(queues = "advert_queue")
    public void listener(Advert advert){
        logger.info(advert.getId()+"  "+advert.getDescription() );
    }


}
