package org.chronopolis.model;


public class DepositorSummary {

    private String name = "";

    private long bags = 0;

    private long files = 0;

    private long bytes = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBags() {
        return bags;
    }

    public void setBags(long bags) {
        this.bags = bags;
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
