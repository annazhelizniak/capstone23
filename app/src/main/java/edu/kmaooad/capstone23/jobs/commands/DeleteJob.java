package edu.kmaooad.capstone23.jobs.commands;

import org.bson.types.ObjectId;

import java.util.Objects;

public class DeleteJob {
    private ObjectId jobId;

    public DeleteJob(ObjectId jobId) {
        this.jobId = jobId;
    }

    public DeleteJob(){};

    public ObjectId getJobId() {
        return jobId;
    }

    public void setJobId(ObjectId jobId) {
        this.jobId = jobId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteJob deleteJob = (DeleteJob) o;
        return jobId.equals(deleteJob.jobId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId);
    }
}
