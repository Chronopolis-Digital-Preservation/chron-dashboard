package org.chronopolis.remote.node.impl;

import org.chronopolis.model.IngestBagStatusType;
import org.chronopolis.model.IngestBagSummary;
import org.chronopolis.model.NodeOnlineStatus;
import org.chronopolis.remote.node.impl.jackson.ingest.bagstatus.Content;
import org.chronopolis.remote.node.impl.jackson.ingest.bagstatus.JsonBagStatus;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class RemoteIngestNode extends DefaultRemoteNode {

    private String ingestUri;
    private String ingestHost;
    private Integer ingestPort;

    public RemoteIngestNode(String nodeName, String environment, String aceUri, RestTemplate restTemplate,
                            String ingestUri, String ingestHost, Integer ingestPort) {

        super(nodeName, environment, aceUri, restTemplate);
        this.ingestUri = ingestUri;
        this.ingestHost = ingestHost;
        this.ingestPort = ingestPort;
    }

    public String getIngestUri() {
        return ingestUri;
    }

    public String getIngestHost() {
        return ingestHost;
    }

    public Integer getIngestPort() {
        return ingestPort;
    }

    public boolean isIngestNode() {

        return true;
    }

    // FIXME It would be nice to break this method down into small pieces.
    // I feel as though a strategy class that would loop though smaller strategies (one for all, one for active
    // and one for everything else would be the way to move forward.
    // Each one of those classes would then make the appropriate call or calls to the remote ingest server.
    // However, the difficulty is what to do with the duplicate code that would come from the
    // getIngestBagStatusesInternal() method?  Does this type of code get broken out to its own class?  Not sure at
    // this time.
    public Collection<IngestBagSummary> getIngestBagStatuses(IngestBagStatusType ingestBagStatusType) {

        Collection<IngestBagSummary> ingestBagStatuses = new HashSet<>();

        if (ingestBagStatusType.equals(IngestBagStatusType.ALL)) {

            ingestBagStatuses.addAll(this.getIngestBagStatusesInternal(IngestBagStatusType.DEPOSITED));
            ingestBagStatuses.addAll(this.getIngestBagStatusesInternal(IngestBagStatusType.INITIALIZED));
            ingestBagStatuses.addAll(this.getIngestBagStatusesInternal(IngestBagStatusType.TOKENIZED));
            ingestBagStatuses.addAll(this.getIngestBagStatusesInternal(IngestBagStatusType.REPLICATING));
            ingestBagStatuses.addAll(this.getIngestBagStatusesInternal(IngestBagStatusType.PRESERVED));
            ingestBagStatuses.addAll(this.getIngestBagStatusesInternal(IngestBagStatusType.ERROR));

        } else if (ingestBagStatusType.equals(IngestBagStatusType.ACTIVE)) {

            ingestBagStatuses.addAll(this.getIngestBagStatusesInternal(IngestBagStatusType.DEPOSITED));
            ingestBagStatuses.addAll(this.getIngestBagStatusesInternal(IngestBagStatusType.INITIALIZED));
            ingestBagStatuses.addAll(this.getIngestBagStatusesInternal(IngestBagStatusType.TOKENIZED));
            ingestBagStatuses.addAll(this.getIngestBagStatusesInternal(IngestBagStatusType.REPLICATING));

        } else {

            ingestBagStatuses.addAll(this.getIngestBagStatusesInternal(ingestBagStatusType));
        }

        return ingestBagStatuses;
    }

    private Collection<IngestBagSummary> getIngestBagStatusesInternal(IngestBagStatusType ingestBagStatusType) {

        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("page_size", "1000000");
        queryParameters.put("status", ingestBagStatusType);

        JsonBagStatus jsonBagStatus = this.restTemplate.getForObject(this.ingestUri + "api/bags/?status={status}&page_size={page_size}", JsonBagStatus.class, queryParameters);

        return this.convertJson(ingestBagStatusType, jsonBagStatus);
    }

    private Collection<IngestBagSummary> convertJson(IngestBagStatusType ingestBagStatusType, JsonBagStatus jsonBagStatus) {

        Collection<IngestBagSummary> ingestBagSummaryCollection = new HashSet<>();

        for (Content content : jsonBagStatus.getContent()) {

            IngestBagSummary ingestBagSummary = new IngestBagSummary();
            ingestBagSummary.setNode(this.nodeName);
            ingestBagSummary.setStatus(ingestBagStatusType.toString());
            ingestBagSummary.setIdentifier(content.getName());
            ingestBagSummary.setBytes(content.getSize().toString());
            ingestBagSummary.setFiles(content.getTotalFiles().toString());
            ingestBagSummary.setOwner(content.getDepositor());
            ingestBagSummary.setDateCreated(content.getCreatedAt());
            ingestBagSummary.setDateUpdated(content.getUpdatedAt());

            ingestBagSummaryCollection.add(ingestBagSummary);
        }

        return ingestBagSummaryCollection;
    }

    @Override
    public NodeOnlineStatus getNodeOnlineStatus() {

        NodeOnlineStatus nodeOnlineStatus = super.getNodeOnlineStatus();

        String ingestHostJsonUri = this.ingestUri + "api/bags/?status=ERROR&page_size=1";

        nodeOnlineStatus.setIngestStatus(Boolean.FALSE.toString());
        nodeOnlineStatus.setIngestUrl(ingestUri);

        try {

            this.restTemplate.getForObject(ingestHostJsonUri, JsonBagStatus.class);

            nodeOnlineStatus.setIngestStatus(Boolean.TRUE.toString());

        } catch (Exception e) {

            nodeOnlineStatus.setMessage(e.getMessage());
        }

        return nodeOnlineStatus;
    }

}

