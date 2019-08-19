package org.chronopolis.remote.node.impl.jackson.ace.status;

// Generated from: http://www.jsonschema2pojo.org/

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "id",
        "name",
        "group",
        "directory",
        "lastSync",
        "storage",
        "checkPeriod",
        "proxyData",
        "auditTokens",
        "state",
        "totalFiles",
        "totalSize",
        "fileAuditRunning",
        "tokenAuditRunning",
        "totalErrors",
        "missingTokens",
        "missingFiles",
        "activeFiles",
        "corruptFiles",
        "invalidDigests",
        "remoteMissing",
        "remoteCorrupt"
})
public class Collection {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("group")
    private String group;
    @JsonProperty("directory")
    private String directory;
    @JsonProperty("lastSync")
    private String lastSync;
    @JsonProperty("storage")
    private String storage;
    @JsonProperty("checkPeriod")
    private String checkPeriod;
    @JsonProperty("proxyData")
    private String proxyData;
    @JsonProperty("auditTokens")
    private String auditTokens;
    @JsonProperty("state")
    private String state;
    @JsonProperty("totalFiles")
    private Long totalFiles;
    @JsonProperty("totalSize")
    private Long totalSize;
    @JsonProperty("fileAuditRunning")
    private Boolean fileAuditRunning;
    @JsonProperty("tokenAuditRunning")
    private Boolean tokenAuditRunning;
    @JsonProperty("totalErrors")
    private Long totalErrors;
    @JsonProperty("missingTokens")
    private Long missingTokens;
    @JsonProperty("missingFiles")
    private Long missingFiles;
    @JsonProperty("activeFiles")
    private Long activeFiles;
    @JsonProperty("corruptFiles")
    private Long corruptFiles;
    @JsonProperty("invalidDigests")
    private Long invalidDigests;
    @JsonProperty("remoteMissing")
    private Long remoteMissing;
    @JsonProperty("remoteCorrupt")
    private Long remoteCorrupt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The id
     */
    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    /**
     * @param id The id
     */
    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The group
     */
    @JsonProperty("group")
    public String getGroup() {
        return group;
    }

    /**
     * @param group The group
     */
    @JsonProperty("group")
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * @return The directory
     */
    @JsonProperty("directory")
    public String getDirectory() {
        return directory;
    }

    /**
     * @param directory The directory
     */
    @JsonProperty("directory")
    public void setDirectory(String directory) {
        this.directory = directory;
    }

    /**
     * @return The lastSync
     */
    @JsonProperty("lastSync")
    public String getLastSync() {
        return lastSync;
    }

    /**
     * @param lastSync The lastSync
     */
    @JsonProperty("lastSync")
    public void setLastSync(String lastSync) {
        this.lastSync = lastSync;
    }

    /**
     * @return The storage
     */
    @JsonProperty("storage")
    public String getStorage() {
        return storage;
    }

    /**
     * @param storage The storage
     */
    @JsonProperty("storage")
    public void setStorage(String storage) {
        this.storage = storage;
    }

    /**
     * @return The checkPeriod
     */
    @JsonProperty("checkPeriod")
    public String getCheckPeriod() {
        return checkPeriod;
    }

    /**
     * @param checkPeriod The checkPeriod
     */
    @JsonProperty("checkPeriod")
    public void setCheckPeriod(String checkPeriod) {
        this.checkPeriod = checkPeriod;
    }

    /**
     * @return The proxyData
     */
    @JsonProperty("proxyData")
    public String getProxyData() {
        return proxyData;
    }

    /**
     * @param proxyData The proxyData
     */
    @JsonProperty("proxyData")
    public void setProxyData(String proxyData) {
        this.proxyData = proxyData;
    }

    /**
     * @return The auditTokens
     */
    @JsonProperty("auditTokens")
    public String getAuditTokens() {
        return auditTokens;
    }

    /**
     * @param auditTokens The auditTokens
     */
    @JsonProperty("auditTokens")
    public void setAuditTokens(String auditTokens) {
        this.auditTokens = auditTokens;
    }

    /**
     * @return The state
     */
    @JsonProperty("state")
    public String getState() {
        return state;
    }

    /**
     * @param state The state
     */
    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return The totalFiles
     */
    @JsonProperty("totalFiles")
    public Long getTotalFiles() {
        return totalFiles;
    }

    /**
     * @param totalFiles The totalFiles
     */
    @JsonProperty("totalFiles")
    public void setTotalFiles(Long totalFiles) {
        this.totalFiles = totalFiles;
    }

    /**
     * @return The totalSize
     */
    @JsonProperty("totalSize")
    public Long getTotalSize() {
        return totalSize;
    }

    /**
     * @param totalSize The totalSize
     */
    @JsonProperty("totalSize")
    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }

    /**
     * @return The fileAuditRunning
     */
    @JsonProperty("fileAuditRunning")
    public Boolean getFileAuditRunning() {
        return fileAuditRunning;
    }

    /**
     * @param fileAuditRunning The fileAuditRunning
     */
    @JsonProperty("fileAuditRunning")
    public void setFileAuditRunning(Boolean fileAuditRunning) {
        this.fileAuditRunning = fileAuditRunning;
    }

    /**
     * @return The tokenAuditRunning
     */
    @JsonProperty("tokenAuditRunning")
    public Boolean getTokenAuditRunning() {
        return tokenAuditRunning;
    }

    /**
     * @param tokenAuditRunning The tokenAuditRunning
     */
    @JsonProperty("tokenAuditRunning")
    public void setTokenAuditRunning(Boolean tokenAuditRunning) {
        this.tokenAuditRunning = tokenAuditRunning;
    }

    /**
     * @return The totalErrors
     */
    @JsonProperty("totalErrors")
    public Long getTotalErrors() {
        return totalErrors;
    }

    /**
     * @param totalErrors The totalErrors
     */
    @JsonProperty("totalErrors")
    public void setTotalErrors(Long totalErrors) {
        this.totalErrors = totalErrors;
    }

    /**
     * @return The missingTokens
     */
    @JsonProperty("missingTokens")
    public Long getMissingTokens() {
        return missingTokens;
    }

    /**
     * @param missingTokens The missingTokens
     */
    @JsonProperty("missingTokens")
    public void setMissingTokens(Long missingTokens) {
        this.missingTokens = missingTokens;
    }

    /**
     * @return The missingFiles
     */
    @JsonProperty("missingFiles")
    public Long getMissingFiles() {
        return missingFiles;
    }

    /**
     * @param missingFiles The missingFiles
     */
    @JsonProperty("missingFiles")
    public void setMissingFiles(Long missingFiles) {
        this.missingFiles = missingFiles;
    }

    /**
     * @return The activeFiles
     */
    @JsonProperty("activeFiles")
    public Long getActiveFiles() {
        return activeFiles;
    }

    /**
     * @param activeFiles The activeFiles
     */
    @JsonProperty("activeFiles")
    public void setActiveFiles(Long activeFiles) {
        this.activeFiles = activeFiles;
    }

    /**
     * @return The corruptFiles
     */
    @JsonProperty("corruptFiles")
    public Long getCorruptFiles() {
        return corruptFiles;
    }

    /**
     * @param corruptFiles The corruptFiles
     */
    @JsonProperty("corruptFiles")
    public void setCorruptFiles(Long corruptFiles) {
        this.corruptFiles = corruptFiles;
    }

    /**
     * @return The invalidDigests
     */
    @JsonProperty("invalidDigests")
    public Long getInvalidDigests() {
        return invalidDigests;
    }

    /**
     * @param invalidDigests The invalidDigests
     */
    @JsonProperty("invalidDigests")
    public void setInvalidDigests(Long invalidDigests) {
        this.invalidDigests = invalidDigests;
    }

    /**
     * @return The remoteMissing
     */
    @JsonProperty("remoteMissing")
    public Long getRemoteMissing() {
        return remoteMissing;
    }

    /**
     * @param remoteMissing The remoteMissing
     */
    @JsonProperty("remoteMissing")
    public void setRemoteMissing(Long remoteMissing) {
        this.remoteMissing = remoteMissing;
    }

    /**
     * @return The remoteCorrupt
     */
    @JsonProperty("remoteCorrupt")
    public Long getRemoteCorrupt() {
        return remoteCorrupt;
    }

    /**
     * @param remoteCorrupt The remoteCorrupt
     */
    @JsonProperty("remoteCorrupt")
    public void setRemoteCorrupt(Long remoteCorrupt) {
        this.remoteCorrupt = remoteCorrupt;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
