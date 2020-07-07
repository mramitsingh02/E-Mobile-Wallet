package com.mmoney.generic.batch.service;

import lombok.Builder;

@Builder
public class BatchJobPool {
    private String fileName;
    private String jobId;

    public BatchJobPool(String fileName, String jobId) {
        this.fileName = fileName;
        this.jobId = jobId;
    }

    public BatchJobPool(BatchJobPool jobPool) {
        this.fileName = jobPool.fileName;
        this.jobId = jobPool.jobId;
    }


}
