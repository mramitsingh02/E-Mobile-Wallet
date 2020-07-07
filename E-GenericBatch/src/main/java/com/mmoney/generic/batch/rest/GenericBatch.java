package com.mmoney.generic.batch.rest;

import com.mmoney.generic.batch.pojo.BatchResponse;
import org.springframework.web.multipart.MultipartFile;


public interface GenericBatch {
    BatchResponse createJob(MultipartFile file);

    BatchResponse createJob(MultipartFile[] files);

    BatchResponse updateJob(MultipartFile file);

    BatchResponse deleteJob(String jobId);

    BatchResponse startJob(String jobId);

    BatchResponse restartJob(String jobId);

    BatchResponse stopJob(String jobId);

    BatchResponse cancelJob(String jobId);

    BatchResponse getJobDetails(String jobId);


}
