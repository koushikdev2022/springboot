package com.koushik.firstproject.model;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "subscriptions")
public class SubscriptionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(nullable = false, unique = true)
    private Long  user_id;

    @Column(nullable = false, unique = true)
    private Long  plan_id;

    @Column(name = "start_date", nullable = false, unique = false, columnDefinition = "DATETIME COMMENT 'subscription start'")
    private LocalDateTime startDate;


    @Column(name = "end_date", nullable = false, unique = false, columnDefinition = "DATETIME COMMENT 'subscription start'")
    private LocalDateTime endDate;


    @Column(name = "status", nullable = false, columnDefinition = "TINYINT COMMENT '1: subscription active, 2: subscription cancel'")
    private Byte status;


    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;



    public SubscriptionModel(){

    }

    public SubscriptionModel(long user_id,long plan_id,LocalDateTime startDate,LocalDateTime endDate,Byte status){
        this.user_id = user_id;
        this.plan_id = plan_id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;

    }
    public Long getId() {
        return id;
    }
    public void setUserId(long user_id){
            this.user_id = user_id;
    }
    public long getUserId(long user_id){
        return user_id;
    }
}
