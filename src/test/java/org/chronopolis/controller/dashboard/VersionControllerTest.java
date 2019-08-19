package org.chronopolis.controller.dashboard;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class VersionControllerTest {

    @Test
    public void versionResponseWithCorrectVersionReturned() {

        VersionController versionController = new VersionController("version_number");

        VersionResponse versionResponse = versionController.getVersion();

        assertThat(versionResponse.getVersion(), is("version_number"));
    }
}
