package com.java.advertproject.Controller;

import com.java.advertproject.Model.Approval;
import com.java.advertproject.Service.ApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/approval")
@RequiredArgsConstructor
public class ApprovalController {

    private final ApprovalService approvalService;

    @GetMapping("/all")
    public ResponseEntity<List<Approval>> getAll(){
        return ResponseEntity.accepted().body(approvalService.all());
    }


    @GetMapping("/confirm")
    public ResponseEntity<String> confirm(@RequestParam("id") long id){
        approvalService.confirmAdvert(id);
        return ResponseEntity.ok("Confirmed!");
    }

    @GetMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam("id") long id){
        approvalService.deleteAdvert(id);
        return ResponseEntity.ok("Deleted!");
    }


}
