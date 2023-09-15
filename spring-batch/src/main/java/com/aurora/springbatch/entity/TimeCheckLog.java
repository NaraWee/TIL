package com.aurora.springbatch.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class TimeCheckLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timeCheckLogId;

    private String timeCheckLog;

    @Builder
    public TimeCheckLog(String timeCheckLog) {
        this.timeCheckLog = timeCheckLog;
    }
}
