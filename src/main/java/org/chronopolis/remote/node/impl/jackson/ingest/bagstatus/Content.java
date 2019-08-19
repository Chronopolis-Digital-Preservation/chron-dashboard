
package org.chronopolis.remote.node.impl.jackson.ingest.bagstatus;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "id",
        "createdAt",
        "updatedAt",
        "name",
        "creator",
        "depositor",
        "location",
        "tokenLocation",
        "status",
        "fixityAlgorithm",
        "size",
        "totalFiles",
        "requiredReplications",
        "replicatingNodes"
})
public class Content {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("updatedAt")
    private String updatedAt;
    @JsonProperty("name")
    private String name;
    @JsonProperty("creator")
    private String creator;
    @JsonProperty("depositor")
    private String depositor;
    @JsonProperty("location")
    private String location;
    @JsonProperty("tokenLocation")
    private String tokenLocation;
    @JsonProperty("status")
    private String status;
    @JsonProperty("fixityAlgorithm")
    private String fixityAlgorithm;
    @JsonProperty("size")
    private Long size;
    @JsonProperty("totalFiles")
    private Long totalFiles;
    @JsonProperty("requiredReplications")
    private Long requiredReplications;
    @JsonProperty("replicatingNodes")
    private List<Object> replicatingNodes = new ArrayList<Object>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The id
     */
    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The createdAt
     */
    @JsonProperty("createdAt")
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The createdAt
     */
    @JsonProperty("createdAt")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     * The updatedAt
     */
    @JsonProperty("updatedAt")
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt
     * The updatedAt
     */
    @JsonProperty("updatedAt")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     *
     * @return
     * The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The creator
     */
    @JsonProperty("creator")
    public String getCreator() {
        return creator;
    }

    /**
     *
     * @param creator
     * The creator
     */
    @JsonProperty("creator")
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     *
     * @return
     * The depositor
     */
    @JsonProperty("depositor")
    public String getDepositor() {
        return depositor;
    }

    /**
     *
     * @param depositor
     * The depositor
     */
    @JsonProperty("depositor")
    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    /**
     *
     * @return
     * The location
     */
    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param location
     * The location
     */
    @JsonProperty("location")
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     *
     * @return
     * The tokenLocation
     */
    @JsonProperty("tokenLocation")
    public String getTokenLocation() {
        return tokenLocation;
    }

    /**
     *
     * @param tokenLocation
     * The tokenLocation
     */
    @JsonProperty("tokenLocation")
    public void setTokenLocation(String tokenLocation) {
        this.tokenLocation = tokenLocation;
    }

    /**
     *
     * @return
     * The status
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The fixityAlgorithm
     */
    @JsonProperty("fixityAlgorithm")
    public String getFixityAlgorithm() {
        return fixityAlgorithm;
    }

    /**
     *
     * @param fixityAlgorithm
     * The fixityAlgorithm
     */
    @JsonProperty("fixityAlgorithm")
    public void setFixityAlgorithm(String fixityAlgorithm) {
        this.fixityAlgorithm = fixityAlgorithm;
    }

    /**
     *
     * @return
     * The size
     */
    @JsonProperty("size")
    public Long getSize() {
        return size;
    }

    /**
     *
     * @param size
     * The size
     */
    @JsonProperty("size")
    public void setSize(Long size) {
        this.size = size;
    }

    /**
     *
     * @return
     * The totalFiles
     */
    @JsonProperty("totalFiles")
    public Long getTotalFiles() {
        return totalFiles;
    }

    /**
     *
     * @param totalFiles
     * The totalFiles
     */
    @JsonProperty("totalFiles")
    public void setTotalFiles(Long totalFiles) {
        this.totalFiles = totalFiles;
    }

    /**
     *
     * @return
     * The requiredReplications
     */
    @JsonProperty("requiredReplications")
    public Long getRequiredReplications() {
        return requiredReplications;
    }

    /**
     *
     * @param requiredReplications
     * The requiredReplications
     */
    @JsonProperty("requiredReplications")
    public void setRequiredReplications(Long requiredReplications) {
        this.requiredReplications = requiredReplications;
    }

    /**
     *
     * @return
     * The replicatingNodes
     */
    @JsonProperty("replicatingNodes")
    public List<Object> getReplicatingNodes() {
        return replicatingNodes;
    }

    /**
     *
     * @param replicatingNodes
     * The replicatingNodes
     */
    @JsonProperty("replicatingNodes")
    public void setReplicatingNodes(List<Object> replicatingNodes) {
        this.replicatingNodes = replicatingNodes;
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