package com.aurora.springbatch.config;

import com.aurora.springbatch.entity.TimeCheckLog;
import com.aurora.springbatch.repository.TimeCheckLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class TimeCheckJobConfig {


    private final PlatformTransactionManager transactionManager;

    private final TimeCheckLogRepository timeCheckLogRepository;


    @Bean
    public Job timeCheckJob(JobRepository jobRepository) {
        return new JobBuilder("timeCheck", jobRepository)
                .start(timeCheckStep(jobRepository))
                .build();
    }

    @Bean
    public Step timeCheckStep(JobRepository jobRepository) {
        return new StepBuilder("timeCheck", jobRepository)
                .<String, String>chunk(1000,transactionManager)
                .reader(itemReader())
                .writer(itemWriter())
                .build();
    }

//    @Bean
//    public Job getTimeCheckJob(JobRepository jobRepository) {
//        return new JobBuilder("getTimeCheck", jobRepository)
//                .start(getTimeCheckStep(jobRepository))
//                .build();
//    }
//
//    @Bean
//    public Step getTimeCheckStep(JobRepository jobRepository) {
//        return new StepBuilder("getTimeCheck", jobRepository)
//                .<String, String>chunk(1000,transactionManager)
//                .reader(itemReader())
//                .build();
//    }

    @Bean
    @StepScope
    public ItemReader<String> itemReader(){
        return new ItemReader<String>() {
            @Override
            public String read() {
                List<TimeCheckLog> timeCheckLogList = timeCheckLogRepository.findAll();
                System.out.println(timeCheckLogList.size());
                return null;
            }
        };
    }

    @Bean
    @StepScope
    public ItemWriter<String> itemWriter(){
        return new ItemWriter<String>() {
            @Override
            public void write(Chunk<? extends String> chunk) throws Exception {
                LocalDateTime now = LocalDateTime.now();
                String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(now);

                TimeCheckLog timeCheckLog = TimeCheckLog.builder()
                        .timeCheckLog(dateTime)
                        .build();
                timeCheckLogRepository.save(timeCheckLog);
            }
        };
    }

}
