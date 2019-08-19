package org.chronopolis.remote.node.impl;

import org.chronopolis.model.*;
import org.chronopolis.remote.node.RemoteNode;

import java.util.Collection;

public class DefaultRemoteNodeStub implements RemoteNode {

    @Override
    public String getNodeName() {
        return null;
    }

    @Override
    public String getEnvironment() {
        return "production";
    }

    @Override
    public NodeOnlineStatus getNodeOnlineStatus() {
        return null;
    }

    @Override
    public AceBagSummary getAceSummaryStatus() {
        return null;
    }

    @Override
    public AceDepositorSummary getAceDepositorSummaryStatus() {
        return null;
    }

    @Override
    public AceDepositorBagSummary getAceDepositorBagSummaryStatus(String depositor) {
        return null;
    }

    @Override
    public boolean isIngestNode() {

        return false;
    }

    @Override
    public Collection<IngestBagSummary> getIngestBagStatuses(IngestBagStatusType ingestBagStatusType) {
        return null;
    }

    @Override
    public NodeOnlineStatus getIngestOnlineStatus() {
        return null;
    }
}
