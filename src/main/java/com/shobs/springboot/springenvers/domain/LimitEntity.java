package com.shobs.springboot.springenvers.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "limit_entity")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Audited
@EntityListeners({AuditingEntityListener.class})
public class LimitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Audited
    @Column(name = "limit_key")
    private String limitKey;

    @Audited
    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "created_date")
    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "updated_date")
    private Date updatedDate;
}
