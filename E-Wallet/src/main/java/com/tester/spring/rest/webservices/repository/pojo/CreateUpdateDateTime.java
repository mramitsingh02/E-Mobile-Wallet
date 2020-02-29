package com.tester.spring.rest.webservices.repository.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class CreateUpdateDateTime {
    @Column(name = "create_time", updatable = false)
    private LocalDateTime createTime;
    @Column(name = "update_time")
    private LocalDateTime updateTime;
    @Column(name = "delete_time", updatable = false)
    private LocalDateTime deleteTime;


}
