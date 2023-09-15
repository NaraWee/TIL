package com.aurora.springbatch.schedule;

import com.aurora.springbatch.config.TimeCheckJobConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class TimeCheckScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private TimeCheckJobConfig timeCheckJobConfig;

    @Autowired
    private JobRepository jobRepository;

    @Scheduled(cron = "0 * * * * *")
    public void writeTimeCheckRunJob() {

        LocalDateTime now = LocalDateTime.now();
        String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(now);
        System.out.println("================= Time Check =================");
        System.out.println(dateTime);

        JobParameters jobParameters = new JobParameters();

        // job parameter 설정
//        Map<String, JobParameter> confMap = new HashMap<>();
//        confMap.put("time", parameters);
//        JobParameters jobParameters = new JobParameters(confMap);

        try {
            jobLauncher.run(timeCheckJobConfig.timeCheckJob(jobRepository), jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException | org.springframework.batch.core.repository.JobRestartException e) {

            log.error(e.getMessage());
        }
    }

//    @Scheduled(cron = "30 * * * * *")
//    public void getTimeCheckRunJob() {
//
//        System.out.println("@@@@@@@@@@@@@@ Get Time Check Log @@@@@@@@@@@@@@");
//
//        JobParameters jobParameters = new JobParameters();
//
//        // job parameter 설정
////        Map<String, JobParameter> confMap = new HashMap<>();
////        confMap.put("time", parameters);
////        JobParameters jobParameters = new JobParameters(confMap);
//
//        try {
//            jobLauncher.run(timeCheckJobConfig.getTimeCheckJob(jobRepository), jobParameters);
//        } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException
//                | JobParametersInvalidException | org.springframework.batch.core.repository.JobRestartException e) {
//
//            log.error(e.getMessage());
//        }
//    }
}
