package org.chronopolis.model;

public class DepositorBagSummary {

    private String bagName = "";

    private long files = 0;

    private long bytes = 0;

    public String getBagName() {
        return bagName;
    }

    public void setBagName(String bagName) {
        this.bagName = bagName;
    }

    public long getFiles() {
        return files;
    }

    public void setFiles(long files) {
        this.files = files;
    }

    public long getBytes() {
        return bytes;
    }

    public void setBytes(long bytes) {
        this.bytes = bytes;
    }

}
