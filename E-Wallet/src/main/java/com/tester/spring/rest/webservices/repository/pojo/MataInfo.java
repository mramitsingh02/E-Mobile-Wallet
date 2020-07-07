package com.tester.spring.rest.webservices.repository.pojo;

import com.tester.spring.rest.webservices.dto.MataInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Data
@ToString
@Embeddable
public class MataInfo implements Serializable {
    private static final long serialVersionUID = 7288915512081815969L;

    @Column(name = "CREATED_BY", nullable = false, updatable = false, length = 50)
    private String createdBy;

    @Column(name = "CREATED_ON", nullable = false, updatable = false, length = 50)
    private LocalDateTime createdOn;

    @Column(name = "MODIFIED_BY", length = 50)
    private String modifiedBy;

    @Column(name = "MODIFIED_ON")
    private LocalDateTime modifiedOn;

    public MataInfo(MataInfoDTO mataInfoDTO) {
        this.createdBy = mataInfoDTO.getCreatedBy();
        this.createdOn = mataInfoDTO.getCreatedOn().getTime();
        this.modifiedBy = mataInfoDTO.getModifiedBy();
        this.modifiedOn = mataInfoDTO.getModifiedOn().getTime();
    }


    public MataInfoDTO toPojo() {
        return new MataInfoDTO(this.createdBy, TimePeriod.of(this.createdOn), this.modifiedBy, TimePeriod.of(this.modifiedOn));
    }

    public Optional<MataInfoDTO> toDto() {
        return Optional.ofNullable(toPojo());
    }
}
