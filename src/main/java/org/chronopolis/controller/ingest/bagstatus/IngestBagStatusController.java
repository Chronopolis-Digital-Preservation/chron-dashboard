package org.chronopolis.controller.ingest.bagstatus;

import org.chronopolis.model.IngestBagStatusType;
import org.chronopolis.model.IngestBagSummary;
import org.chronopolis.remote.node.RemoteNode;
import org.chronopolis.repository.RemoteNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class IngestBagStatusController {

    private RemoteNodeRepository remoteNodeRepository;

    @Autowired
    public IngestBagStatusController(RemoteNodeRepository remoteNodeRepository) {
        this.remoteNodeRepository = remoteNodeRepository;
    }

    @RequestMapping(value = "/api/v1/getIngestBagSummary.json")
    @ResponseBody
    public Collection<IngestBagSummary> getIngestBagStatuses(@RequestBody IngestBagStatusRequest ingestBagStatusRequest) {

        Collection<IngestBagSummary> ingestBagStatuses = new ArrayList<>();

        IngestBagStatusType statusType = IngestBagStatusType.valueOf(ingestBagStatusRequest.getStatus());

        RemoteNode remoteNode = this.remoteNodeRepository.getByName(ingestBagStatusRequest.getNode());
        ingestBagStatuses.addAll(remoteNode.getIngestBagStatuses(statusType));

        return ingestBagStatuses;
    }
}
