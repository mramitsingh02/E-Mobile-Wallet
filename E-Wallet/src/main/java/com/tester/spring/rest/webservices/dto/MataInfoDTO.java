package com.tester.spring.rest.webservices.dto;

import com.tester.spring.rest.webservices.repository.pojo.MataInfo;
import com.tester.spring.rest.webservices.repository.pojo.TimePeriod;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MataInfoDTO {
    private String createdBy;
    private TimePeriod createdOn;
    private String modifiedBy;
    private TimePeriod modifiedOn;

    public MataInfoDTO() {
    }

    public MataInfoDTO(MataInfo mataInfo) {
        this.createdBy = mataInfo.getCreatedBy();
        this.createdOn = TimePeriod.of(mataInfo.getCreatedOn());
        this.modifiedBy = mataInfo.getModifiedBy();
        this.modifiedOn = TimePeriod.of(mataInfo.getModifiedOn());
    }

    public MataInfo toPojo() {
        return new MataInfo(this.createdBy, this.createdOn.getTime(), this.modifiedBy, this.modifiedOn.getTime());
    }
}
