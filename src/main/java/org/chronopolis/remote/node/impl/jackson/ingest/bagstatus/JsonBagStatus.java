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
        "content",
        "totalPages",
        "totalElements",
        "last",
        "sort",
        "numberOfElements",
        "first",
        "size",
        "number"
})
public class JsonBagStatus {

    @JsonProperty("content")
    private List<Content> content = new ArrayList<Content>();
    @JsonProperty("totalPages")
    private Integer totalPages;
    @JsonProperty("totalElements")
    private Integer totalElements;
    @JsonProperty("last")
    private Boolean last;
    @JsonProperty("sort")
    private Object sort;
    @JsonProperty("numberOfElements")
    private Integer numberOfElements;
    @JsonProperty("first")
    private Boolean first;
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("number")
    private Integer number;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The content
     */
    @JsonProperty("content")
    public List<Content> getContent() {
        return content;
    }

    /**
     * @param content The content
     */
    @JsonProperty("content")
    public void setContent(List<Content> content) {
        this.content = content;
    }

    /**
     * @return The totalPages
     */
    @JsonProperty("totalPages")
    public Integer getTotalPages() {
        return totalPages;
    }

    /**
     * @param totalPages The totalPages
     */
    @JsonProperty("totalPages")
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * @return The totalElements
     */
    @JsonProperty("totalElements")
    public Integer getTotalElements() {
        return totalElements;
    }

    /**
     * @param totalElements The totalElements
     */
    @JsonProperty("totalElements")
    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    /**
     * @return The last
     */
    @JsonProperty("last")
    public Boolean getLast() {
        return last;
    }

    /**
     * @param last The last
     */
    @JsonProperty("last")
    public void setLast(Boolean last) {
        this.last = last;
    }

    /**
     * @return The sort
     */
    @JsonProperty("sort")
    public Object getSort() {
        return sort;
    }

    /**
     * @param sort The sort
     */
    @JsonProperty("sort")
    public void setSort(Object sort) {
        this.sort = sort;
    }

    /**
     * @return The numberOfElements
     */
    @JsonProperty("numberOfElements")
    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    /**
     * @param numberOfElements The numberOfElements
     */
    @JsonProperty("numberOfElements")
    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    /**
     * @return The first
     */
    @JsonProperty("first")
    public Boolean getFirst() {
        return first;
    }

    /**
     * @param first The first
     */
    @JsonProperty("first")
    public void setFirst(Boolean first) {
        this.first = first;
    }

    /**
     * @return The size
     */
    @JsonProperty("size")
    public Integer getSize() {
        return size;
    }

    /**
     * @param size The size
     */
    @JsonProperty("size")
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * @return The number
     */
    @JsonProperty("number")
    public Integer getNumber() {
        return number;
    }

    /**
     * @param number The number
     */
    @JsonProperty("number")
    public void setNumber(Integer number) {
        this.number = number;
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
