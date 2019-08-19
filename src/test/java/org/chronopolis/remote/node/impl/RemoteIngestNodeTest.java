package org.chronopolis.remote.node.impl;

import org.chronopolis.model.IngestBagStatusType;
import org.chronopolis.model.IngestBagSummary;
import org.chronopolis.remote.node.impl.jackson.ingest.bagstatus.Content;
import org.chronopolis.remote.node.impl.jackson.ingest.bagstatus.JsonBagStatus;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class RemoteIngestNodeTest {

    public static final String NODE_NAME = "TEST NODE";
    public static final String ACE_URI = "http://ace.test.com/";
    public static final String INGEST_URI = "http://ingest.test.com/";
    public static final String INGEST_HOST = "chron-ingest.ucsd.edu";
    public static final Integer INGEST_PORT = 8443;

    private RemoteIngestNode createRemoteIngestNode(RestTemplate stubRestTemplate) {
        return new RemoteIngestNode(NODE_NAME, "production", ACE_URI, stubRestTemplate, INGEST_URI, INGEST_HOST, INGEST_PORT);
    }

    @Test
    public void testIsIngestNode() {

        DefaultRemoteNode remoteNode = this.createRemoteIngestNode(null);

        assertThat(remoteNode.isIngestNode(), is(true));
    }

    @Test
    public void testIngestNodeJsonConvertedToBagStatus() {

        RestTemplate stubRestTemplate = new RestTemplate() {
            public <T> T getForObject(String url, Class<T> responseType, Map<String, ?> urlVariables) {

                return responseType.cast(createIngestJsonBagStatus());
            }
        };

        RemoteIngestNode remoteNode = createRemoteIngestNode(stubRestTemplate);

        Collection<IngestBagSummary> ingestBagStatuses = remoteNode.getIngestBagStatuses(IngestBagStatusType.DEPOSITED);

        IngestBagSummary ingestBagSummary = ingestBagStatuses.iterator().next();
        assertThat(ingestBagSummary.getStatus(), is(IngestBagStatusType.DEPOSITED.toString()));
        assertThat(ingestBagSummary.getNode(), is(NODE_NAME));
        assertThat(ingestBagSummary.getIdentifier(), is("bb0102820h"));
        assertThat(ingestBagSummary.getBytes(), is("1867259"));
        assertThat(ingestBagSummary.getFiles(), is("10"));
        assertThat(ingestBagSummary.getOwner(), is("prodtest000"));
    }

    public JsonBagStatus createIngestJsonBagStatus() {

        Content content = new Content();
        content.setName("bb0102820h");
        content.setDepositor("prodtest000");
        content.setLocation("prodtest000/bb0102820h");
        content.setTokenLocation("prodtest000/bb0102820h2015-06-04");
        content.setFixityAlgorithm("SHA-256");
        content.setSize(Long.valueOf(1867259));
        content.setTotalFiles(10L);
        content.setRequiredReplications(3L);
        content.setId(1L);

        List<Content> contentList = new ArrayList<>();
        contentList.add(content);

        JsonBagStatus jsonBagStatus = new JsonBagStatus();

        jsonBagStatus.setContent(contentList);
        jsonBagStatus.setTotalPages(1);
        jsonBagStatus.setTotalElements(1);
        jsonBagStatus.setLast(true);
        jsonBagStatus.setSort(false);
        jsonBagStatus.setNumberOfElements(1);
        jsonBagStatus.setFirst(true);
        jsonBagStatus.setSize(20);
        jsonBagStatus.setNumber(0);

        return jsonBagStatus;
    }

    @Test
    public void testBagStatusTypeEqualToAll() {

        RestTemplate mockRestTemplate = mock(RestTemplate.class);

        when(mockRestTemplate.getForObject(anyString(), eq(JsonBagStatus.class), any(Map.class))).thenReturn(this.createIngestJsonBagStatus());

        RemoteIngestNode remoteNode = createRemoteIngestNode(mockRestTemplate);

        remoteNode.getIngestBagStatuses(IngestBagStatusType.ALL);

        verify(mockRestTemplate).getForObject(INGEST_URI + "api/bags/?status={status}&page_size={page_size}", JsonBagStatus.class, createParameterMap(IngestBagStatusType.DEPOSITED));
        verify(mockRestTemplate).getForObject(INGEST_URI + "api/bags/?status={status}&page_size={page_size}", JsonBagStatus.class, createParameterMap(IngestBagStatusType.INITIALIZED));
        verify(mockRestTemplate).getForObject(INGEST_URI + "api/bags/?status={status}&page_size={page_size}", JsonBagStatus.class, createParameterMap(IngestBagStatusType.TOKENIZED));
        verify(mockRestTemplate).getForObject(INGEST_URI + "api/bags/?status={status}&page_size={page_size}", JsonBagStatus.class, createParameterMap(IngestBagStatusType.REPLICATING));
        verify(mockRestTemplate).getForObject(INGEST_URI + "api/bags/?status={status}&page_size={page_size}", JsonBagStatus.class, createParameterMap(IngestBagStatusType.PRESERVED));
        verify(mockRestTemplate).getForObject(INGEST_URI + "api/bags/?status={status}&page_size={page_size}", JsonBagStatus.class, createParameterMap(IngestBagStatusType.ERROR));
    }

    @Test
    public void testBagStatusTypeOnlyEqualToDeposited() {

        RestTemplate mockRestTemplate = mock(RestTemplate.class);

        when(mockRestTemplate.getForObject(anyString(), eq(JsonBagStatus.class), any(Map.class))).thenReturn(this.createIngestJsonBagStatus());

        RemoteIngestNode remoteNode = createRemoteIngestNode(mockRestTemplate);

        remoteNode.getIngestBagStatuses(IngestBagStatusType.DEPOSITED);

        verify(mockRestTemplate, only()).getForObject(INGEST_URI + "api/bags/?status={status}&page_size={page_size}", JsonBagStatus.class, createParameterMap(IngestBagStatusType.DEPOSITED));
    }

    public Map<String, Object> createParameterMap(IngestBagStatusType ingestBagStatusType) {

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("page_size", "1000000");
        parameterMap.put("status", ingestBagStatusType);

        return parameterMap;
    }

    @Test
    public void testBagStatusCollectionSizeEqualToSix() {

        RestTemplate mockRestTemplate = mock(RestTemplate.class);

        when(mockRestTemplate.getForObject(anyString(), eq(JsonBagStatus.class), any(Map.class))).thenReturn(this.createIngestJsonBagStatus());

        RemoteIngestNode remoteNode = createRemoteIngestNode(mockRestTemplate);

        Collection<IngestBagSummary> ingestBagStatuses = remoteNode.getIngestBagStatuses(IngestBagStatusType.ALL);

        assertThat(ingestBagStatuses.size(), is(6));
    }

    /* Example Json Content:
        "content": [
        {
            "name": "bb0102820h",
            "depositor": "prodtest000",
            "location": "prodtest000/bb0102820h",
            "tokenLocation": "prodtest000/bb0102820h2015-06-04",
            "fixityAlgorithm": "SHA-256",
            "size": 18672592,
            "totalFiles": 10,
            "requiredReplications": 3,
            "id": 1
        },
        {
            "name": "acadis_20150419",
            "depositor": "ncar",
            "location": "ncar/acadis_20150419",
            "tokenLocation": "ncar/acadis_201504192015-06-06",
            "fixityAlgorithm": "SHA-256",
            "size": 1030783586432,
            "totalFiles": 173357,
            "requiredReplications": 3,
            "id": 2
        },
        ...
    ],
    "totalPages": 1,
    "totalElements": 2,
    "last": true,
    "sort": null,
    "numberOfElements": 2,
    "first": true,
    "size": 20,
    "number": 0
     */
}
