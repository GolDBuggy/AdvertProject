package com.java.advertproject.Service;

import com.java.advertproject.Dto.AdvertDto;
import com.java.advertproject.Dto.AdvertSendDto;
import com.java.advertproject.Dto.ReportDto;
import com.java.advertproject.Model.Advert;
import com.java.advertproject.Model.Report;
import com.java.advertproject.Repository.ReportRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepo reportRepo;
    private final ModelMapper modelMapper;
    private  static Logger logger=Logger.getLogger(ReportService.class.getName());

    @RabbitListener(queues = "advert_queue" )
    public void update(AdvertSendDto advertSendDto){
        Report report;
        if(advertSendDto.getReport()!=null)
            report=advertSendDto.getReport();
        else
          report=new Report();

        long dayExtract =new Date().getTime()-advertSendDto.getCreatedTime().getTime();
        long dayDiff = dayExtract / (60 * 60 * 1000 * 24);
        report.setMessage("Advert created "+ dayDiff+ " ago by "+advertSendDto.getUser().getEmail()+".Advert has been viewed "+advertSendDto.getUrl().getClickCount() +" times");

        report.setAdvert(modelMapper.map(advertSendDto,Advert.class));
        reportRepo.save(report);
    }


    public List<ReportDto> all() {
        return reportRepo.findAll().stream().map(r -> modelMapper.map(r,ReportDto.class)).collect(Collectors.toList());
    }
}
