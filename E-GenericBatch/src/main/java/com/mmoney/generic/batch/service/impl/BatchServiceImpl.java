package com.mmoney.generic.batch.service.impl;

import com.mmoney.generic.batch.pojo.BatchDetails;
import com.mmoney.generic.batch.pojo.BatchResponse;
import com.mmoney.generic.batch.service.BatchService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BatchServiceImpl implements BatchService {
    @Override
    public BatchDetails createJob(MultipartFile file) {
        return null;
    }

    @Override
    public BatchDetails createJob(MultipartFile[] files) {
        return null;
    }

    @Override
    public BatchDetails updateJob(MultipartFile file) {
        return null;
    }

    @Override
    public BatchDetails deleteJob(String jobId) {
        return null;
    }

    @Override
    public BatchDetails startJob(String jobId) {
        return null;
    }

    @Override
    public BatchDetails restartJob(String jobId) {
        return null;
    }

    @Override
    public BatchDetails stopJob(String jobId) {
        return null;
    }

    @Override
    public BatchDetails cancelJob(String jobId) {
        return null;
    }

    @Override
    public BatchResponse getJobDetails(String jobId) {
        return null;
    }
}
