package org.chronopolis.controller.ingest.bagstatus;

public class IngestBagStatusRequest {

    private String node;

    private String status;

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
