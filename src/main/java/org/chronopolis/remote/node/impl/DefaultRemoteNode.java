package org.chronopolis.remote.node.impl;

import org.chronopolis.model.*;
import org.chronopolis.remote.node.RemoteNode;
import org.chronopolis.remote.node.impl.jackson.ace.status.AceSummaryStatus;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class DefaultRemoteNode implements RemoteNode {

    protected String nodeName;
    protected String environment;

    protected String aceHostUri;
    protected String aceHostJsonUri;
    protected RestTemplate restTemplate;

    public DefaultRemoteNode(String nodeName, String environment, String aceHostUri, RestTemplate restTemplate) {

        this.nodeName = nodeName;
        this.environment = environment;
        this.aceHostUri = aceHostUri + "ace-am";
        this.aceHostJsonUri = aceHostUri + "ace-am/Status?count=20000&json=true";
        this.restTemplate = restTemplate;
    }

    @Override
    public String getNodeName() {
        return nodeName;
    }

    @Override
    public String getEnvironment() {
        return environment;
    }

    @Override
    public NodeOnlineStatus getNodeOnlineStatus() {

        NodeOnlineStatus nodeOnlineStatus = new NodeOnlineStatus();

        nodeOnlineStatus.setNode(this.nodeName);
        nodeOnlineStatus.setAceStatus(Boolean.FALSE.toString());
        nodeOnlineStatus.setUrl(this.aceHostUri);

        try {

            this.restTemplate.getForObject(this.aceHostJsonUri, String.class);
            nodeOnlineStatus.setAceStatus(Boolean.TRUE.toString());

        } catch (Exception e) {

            nodeOnlineStatus.setMessage(e.getMessage());
        }

        return nodeOnlineStatus;
    }

    private AceSummaryStatus getAceSummaryStatusFromRemoteAce() {

        return this.restTemplate.getForObject(this.aceHostJsonUri, AceSummaryStatus.class);
    }

    @Override
    public AceBagSummary getAceSummaryStatus() {

        AceBagSummary aceBagSummary = new AceBagSummary();

        aceBagSummary.setNode(this.nodeName);

        try {

            // Note: The JSON generated the class named "Collection"
            AceSummaryStatus aceSummaryStatus = this.getAceSummaryStatusFromRemoteAce();

            List<org.chronopolis.remote.node.impl.jackson.ace.status.Collection> aceCollections = excludeDeletedCollections(aceSummaryStatus.getCollections());

            aceBagSummary.setBags(String.valueOf(aceCollections.size()));

            Long totalFiles = 0L;
            Long totalBytes = 0L;

            for (org.chronopolis.remote.node.impl.jackson.ace.status.Collection aceCollection : aceCollections) {

                totalFiles += aceCollection.getTotalFiles();
                totalBytes += aceCollection.getTotalSize();
            }

            aceBagSummary.setFiles(totalFiles.toString());
            aceBagSummary.setBytes(totalBytes.toString());

        } catch (Exception e) {

            aceBagSummary.setMessage(e.getMessage());
        }

        return aceBagSummary;
    }

    /*
     * Exclude deleted ace collections from the collection list
     * @param aceCollections
     * @return
     */
    private List<org.chronopolis.remote.node.impl.jackson.ace.status.Collection> excludeDeletedCollections(
            List<org.chronopolis.remote.node.impl.jackson.ace.status.Collection> aceCollections) {

        List<org.chronopolis.remote.node.impl.jackson.ace.status.Collection> results = new ArrayList<>();
        for (org.chronopolis.remote.node.impl.jackson.ace.status.Collection aceCollection : aceCollections) {
            if (aceCollection.getState() != null && !"R".equalsIgnoreCase(aceCollection.getState())) {
                results.add(aceCollection);
            }
        }
        return results;
    }

    public AceDepositorSummary getAceDepositorSummaryStatus() {

        AceDepositorSummary aceDepositorSummary = new AceDepositorSummary();
        aceDepositorSummary.setNode(this.nodeName);

        try {

            Map<String, DepositorSummary> depositor2summary = new TreeMap<>();

            AceSummaryStatus aceSummaryStatus = this.getAceSummaryStatusFromRemoteAce();
            aceSummaryStatus.setCollections(excludeDeletedCollections(aceSummaryStatus.getCollections()));

            for (org.chronopolis.remote.node.impl.jackson.ace.status.Collection aceCollection : aceSummaryStatus.getCollections()) {

                DepositorSummary depositorSummary = depositor2summary.get(aceCollection.getGroup());

                if (depositorSummary == null) {
                    depositorSummary = new DepositorSummary();
                    depositorSummary.setName(aceCollection.getGroup());
                }

                depositorSummary.setBags(depositorSummary.getBags() + 1);
                depositorSummary.setFiles(depositorSummary.getFiles() + aceCollection.getTotalFiles());
                depositorSummary.setBytes(depositorSummary.getBytes() + aceCollection.getTotalSize());

                depositor2summary.put(aceCollection.getGroup(), depositorSummary);
            }

            aceDepositorSummary.setDepositors(new ArrayList<>(depositor2summary.values()));

        } catch (Exception e) {

            aceDepositorSummary.setMessage(e.getMessage());
        }

        return aceDepositorSummary;
    }

    public AceDepositorBagSummary getAceDepositorBagSummaryStatus(String depositor) {

        AceDepositorBagSummary aceDepositorBagSummary = new AceDepositorBagSummary();
        aceDepositorBagSummary.setNode(this.nodeName);
        aceDepositorBagSummary.setDepositor(depositor);

        try {

            List<DepositorBagSummary> depositorBagSummaries = new ArrayList<>();

            AceSummaryStatus aceSummaryStatus = this.getAceSummaryStatusFromRemoteAce();

            for (org.chronopolis.remote.node.impl.jackson.ace.status.Collection aceCollection : aceSummaryStatus.getCollections()) {

                if (aceCollection.getGroup().equals(depositor)) {
                    DepositorBagSummary depositorBagSummary = new DepositorBagSummary();
                    depositorBagSummary.setBagName(aceCollection.getName());
                    depositorBagSummary.setFiles(aceCollection.getTotalFiles());
                    depositorBagSummary.setBytes(aceCollection.getTotalSize());
                    depositorBagSummaries.add(depositorBagSummary);
                }
            }

            aceDepositorBagSummary.setBagSummaries(depositorBagSummaries);

        } catch (Exception e) {

            aceDepositorBagSummary.setMessage(e.getMessage());
        }

        return aceDepositorBagSummary;
    }

    public boolean isIngestNode() {

        return false;
    }

    public Collection<IngestBagSummary> getIngestBagStatuses(IngestBagStatusType ingestBagStatusType) {

        throw new UnsupportedOperationException("Invalid operation for Default Remote Node.");
    }

    public NodeOnlineStatus getIngestOnlineStatus() {

        throw new UnsupportedOperationException("Invalid operation for Default Remote Node.");
    }
}

