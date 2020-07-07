package com.mmoney.generic.batch.rest;

import com.mmoney.generic.batch.pojo.BatchDetails;
import com.mmoney.generic.batch.pojo.BatchResponse;
import org.springframework.web.multipart.MultipartFile;

public interface GenericBatchService {
    BatchDetails createJob(MultipartFile file);

    BatchDetails createJob(MultipartFile[] files);

    BatchDetails updateJob(MultipartFile file);

    BatchDetails deleteJob(String jobId);

    BatchDetails startJob(String jobId);

    BatchDetails restartJob(String jobId);

    BatchDetails stopJob(String jobId);

    BatchDetails cancelJob(String jobId);

    BatchResponse getJobDetails(String jobId);
}
