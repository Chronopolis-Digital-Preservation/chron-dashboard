package org.chronopolis.remote.node;

import org.chronopolis.model.*;

import java.util.Collection;

public interface RemoteNode {

    String getNodeName();

    String getEnvironment();

    NodeOnlineStatus getNodeOnlineStatus();

    AceBagSummary getAceSummaryStatus();

    AceDepositorSummary getAceDepositorSummaryStatus();

    AceDepositorBagSummary getAceDepositorBagSummaryStatus(String depositor);

    boolean isIngestNode();

    Collection<IngestBagSummary> getIngestBagStatuses(IngestBagStatusType ingestBagStatusType);

    NodeOnlineStatus getIngestOnlineStatus();
}
