package org.chronopolis.remote.node.impl.jackson.ace.status;

// Generated from: http://www.jsonschema2pojo.org/

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "startup_complete",
        "paused",
        "collections"
})
public class AceSummaryStatus {

    @JsonProperty("startup_complete")
    private Boolean startupComplete;
    @JsonProperty("paused")
    private Boolean paused;
    @JsonProperty("collections")
    private List<Collection> collections = new ArrayList<Collection>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The startupComplete
     */
    @JsonProperty("startup_complete")
    public Boolean getStartupComplete() {
        return startupComplete;
    }

    /**
     * @param startupComplete The startup_complete
     */
    @JsonProperty("startup_complete")
    public void setStartupComplete(Boolean startupComplete) {
        this.startupComplete = startupComplete;
    }

    /**
     * @return The paused
     */
    @JsonProperty("paused")
    public Boolean getPaused() {
        return paused;
    }

    /**
     * @param paused The paused
     */
    @JsonProperty("paused")
    public void setPaused(Boolean paused) {
        this.paused = paused;
    }

    /**
     * @return The collections
     */
    @JsonProperty("collections")
    public List<Collection> getCollections() {
        return collections;
    }

    /**
     * @param collections The collections
     */
    @JsonProperty("collections")
    public void setCollections(List<Collection> collections) {
        this.collections = collections;
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
