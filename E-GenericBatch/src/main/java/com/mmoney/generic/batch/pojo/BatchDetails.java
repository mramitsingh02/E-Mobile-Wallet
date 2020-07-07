package com.mmoney.generic.batch.pojo;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class BatchDetails {
    private String fileName;
    private String downloadFile;
    private String jobId;

}
