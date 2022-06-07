package com.shobs.springboot.springenvers.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "sequence_number")
public class SequenceNumber implements Persistable<Long> {
    @Id
    @GeneratedValue
    @Column(name = "sequence_number")
    private Long sequenceNumber;

    @Column(name = "create_date")
    @CreatedDate
    private Timestamp createDate;

    @Override
    public Long getId() {
        return null;
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        return false;
    }
}