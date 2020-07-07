package com.mmoney.generic.batch.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
@Builder
public class BatchResponse {
    private String jobId;
    private String status;
    private String fileName;
    private String downloadFile;


}
