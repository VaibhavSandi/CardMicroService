package com.example.card.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntitiy {

@CreatedDate
@Column(name = "CREATED_AT",updatable = false)
    private LocalDateTime craetaedAt;

     @CreatedBy
    @Column(name = "CREATED_BY",updatable = false)
private String createdBy;

     @LastModifiedDate
     @Column(name = "UPDATED_AT",insertable = false)
 private LocalDateTime updatedAt;

 @LastModifiedBy
 @Column(name = "UPDATED_BY",insertable = false)
 private String updatedBy;

}
