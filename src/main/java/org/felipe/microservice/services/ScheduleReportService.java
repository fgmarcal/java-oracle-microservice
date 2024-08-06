package org.felipe.microservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleReportService {

    @Autowired
    private FileOSService fileOSService;

    @Autowired
    private EmailService emailService;

    @Value("#{'${email.list}'.split(',')}")
    private List<String> emailList;

//    private final Integer SEVEN_DAYS_IN_MILLISECONS = 604800000;

    private final int THIRDY_SECONDS_IN_MS_TEST = 3000;

    @Scheduled(fixedRate = THIRDY_SECONDS_IN_MS_TEST)
    public void sendReport(){
        try {
            String report = fileOSService.getReportFileContent("report.html");

            for(String email : emailList){
                emailService.sendReport(report, email);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
