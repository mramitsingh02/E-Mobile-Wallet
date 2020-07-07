package com.mmoney.generic.batch.rest;

import com.mmoney.generic.batch.pojo.BatchResponse;
import com.mmoney.generic.batch.service.BatchService;
import com.mmoney.generic.batch.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/batch")
public class GenericBatchRequestController {
    @Autowired
    private BatchService batchService;
    @Autowired
    private FileStorageService fileStorageService;

    public GenericBatchRequestController(BatchService batchService, FileStorageService fileStorageService) {
        this.batchService = batchService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping(name = "/create")
    public BatchResponse createJob(@RequestParam("file") MultipartFile file) {
        final BatchResponse.BatchResponseBuilder builder = BatchResponse.builder ();
        String fileName = this.fileStorageService.storeFile (file);
        String jobId = UUID.randomUUID ().toString ();
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath ()
                .path ("/downloadFile/")
                .path (fileName)
                .toUriString ();

        return builder.fileName (fileName).jobId (jobId).downloadFile (fileDownloadUri).build ();
    }

/*

    @PostMapping(name = "/createJobs", consumes = "application/octet-stream", produces = {"application/json", "application/xml"})
    public BatchResponse createJob(@RequestParam("files") MultipartFile[] files) {
        return null;
    }


    @PutMapping(name = "/update", consumes = "application/octet-stream", produces = {"application/json", "application/xml"})
    public BatchResponse updateJob(@RequestParam("file") MultipartFile file) {
        String jobId = UUID.randomUUID ().toString ();
        return BatchResponse.builder ().jobId (jobId).status ("OK").build ();
    }


    @PostMapping(name = "/delete/{jobId}")
    public BatchResponse deleteJob(@PathVariable String jobId) {
        return BatchResponse.builder ().jobId (jobId).status ("OK").build ();
    }


    @PostMapping(name = "/start/{jobId}")
    public BatchResponse startJob(@PathVariable String jobId) {
        return BatchResponse.builder ().jobId (jobId).status ("OK").build ();
    }


    @PostMapping(name = "/restart/{jobId}")
    public BatchResponse restartJob(@PathVariable String jobId) {
        return BatchResponse.builder ().jobId (jobId).status ("OK").build ();
    }


    @PostMapping(name = "/stop/{jobId}")
    public BatchResponse stopJob(@PathVariable String jobId) {
        return BatchResponse.builder ().jobId (jobId).status ("OK").build ();
    }


    @PostMapping(name = "/cancel/{jobId}")
    public BatchResponse cancelJob(@PathVariable String jobId) {
        return BatchResponse.builder ().jobId (jobId).status ("OK").build ();
    }


    @GetMapping("/get/{jobId}")
    public BatchResponse getJobDetails(@PathVariable String jobId) {
        return BatchResponse.builder ().jobId (jobId).status ("OK").build ();
    }
*/


}
