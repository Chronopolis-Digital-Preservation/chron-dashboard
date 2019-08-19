package org.chronopolis.controller.ace.onlinestatus;

import org.chronopolis.model.NodeOnlineStatus;
import org.chronopolis.remote.node.RemoteNode;
import org.chronopolis.repository.RemoteNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Deprecated
@Controller
public class IngestOnlineStatusController {

    private RemoteNodeRepository remoteNodeRepository;

    @Autowired
    public IngestOnlineStatusController(RemoteNodeRepository remoteNodeRepository) {
        this.remoteNodeRepository = remoteNodeRepository;
    }

    @RequestMapping(value = "/api/v1/getIngestOnlineStatus.json")
    @ResponseBody
    public List<NodeOnlineStatus> getOnlineStatuses(@RequestBody List<AceOnlineStatusRequest> aceOnlineStatusRequests) {

        List<NodeOnlineStatus> ingestOnlineStatuses = new ArrayList<>();

        for (AceOnlineStatusRequest aceOnlineStatusRequest : aceOnlineStatusRequests) {

            RemoteNode remoteNode = this.remoteNodeRepository.getByName(aceOnlineStatusRequest.getNode());
            //  aceOnlineStatuses.add(remoteNode.getNodeOnlineStatus());
            ingestOnlineStatuses.add(remoteNode.getIngestOnlineStatus());
        }

        return ingestOnlineStatuses;
    }

    @RequestMapping(value = "/api/v1/node/{node}/ingest.json", method = RequestMethod.GET)
    @ResponseBody
    public NodeOnlineStatus getIngestOnlineStatus(@PathVariable(value = "node") RemoteNode remoteNode) {

        return remoteNode.getIngestOnlineStatus();
    }

}
