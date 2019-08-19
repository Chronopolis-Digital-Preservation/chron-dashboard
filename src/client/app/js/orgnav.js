if (typeof hideNSF === 'undefined') {
    const hideNSF = false;
}

if (typeof contactLink === 'undefined') {
    const contactLink = 'mailto:webmaster@ucar.edu';
}

if (typeof hideOrgNav === 'undefined') {
    const hideOrgNav = false;
}

if (typeof hideFooter === 'undefined') {
    const hideFooter = false;
}

/*
if (!hideFooter) { // Must be before page load, I.E. outside of (document).ready
    document.write('<div id="orgFooter"></div>');
}
*/

jQuery(document).ready(function ($) {
    const year = new Date().getFullYear();

    const body = 'body';
    const orgFooterId = '#orgFooter';
    const menuIDs = '#ucarOrg, #ncarOrg, #peopleOrg, #contactOrg';

    $('<link/>', {
        rel: 'stylesheet',
        type: 'text/css',
        href: 'https://orgnav.ucar.edu/orgnav.css'
    }).appendTo('head');

 //   if (!hideFooter) {
        const ncarHTML = '' +
            '<p>&copy; ' + year + ' UCAR | ' +
            '<a href="http://www.ucar.edu/privacy-policy" target="_blank">Privacy Policy</a> | ' +
            '<a href="http://www.ucar.edu/terms-of-use" target="_blank">Terms of Use</a> | ' +
            '<a href="https://www.ucar.edu/notification-copyright-infringement-digital-millenium-copyright-act" target="_blank">Copyright Issues</a> | ' +
            '<a href="http://www.nsf.gov" target="_blank">Sponsored by NSF</a> | ' +
            '<a href="http://www.ucar.edu" target="_blank">Managed by UCAR</a>' +
            '<br>Postal Address: P.O. Box 3000, Boulder, CO 80307-3000 &bull; ' +
            'Shipping Address: 3090 Center Green Drive, Boulder, CO 80301</p>';

        const nsfStatement = '<p>The National Center for Atmospheric Research is sponsored by the National Science Foundation. Any opinions, findings and conclusions or recommendations expressed in this material do not necessarily reflect the views of the National Science Foundation.</p>';

        $(orgFooterId).append(ncarHTML);

     //   if (!hideNSF) {
            $(orgFooterId).append(nsfStatement);
     //   }
 //   }

  //  if (!hideOrgNav) {
        const orgHtml = '<div id="orgNavV1">' +
            '<div id="ucarOrg"> <ul> <li><a href="http://www.ucar.edu" title="">UCAR</a> <ul> <li><a href="http://www.ucar.edu" title="">University Corporation for Atmospheric Research</a></li> <li><a href="https://www.ucar.edu/community-programs" title="">UCAR Community Programs</a> <ul> <li><a href="http://www.comet.ucar.edu" title="The COMET Program">COMET</a></li> <li><a href="http://www.cosmic.ucar.edu" title="Constellation Observing System for Meteorology, Ionosphere &amp; Climate">COSMIC</a> </li> <li><a href="http://cpaess.ucar.edu" title="">CPAESS</a></li> <li><a href="https://www.globe.gov" title="Global Learning &amp; Observations to Benefit the Environment">GLOBE</a></li> <li><a href="http://scied.ucar.edu" title="">UCAR Center for Science Education</a></li> <li><a href="http://www.unidata.ucar.edu" title="Unidata Program Center">Unidata</a></li> <li><a href="http://www.ucar.edu/who-we-are/community-programs/ucp-directors-office" title="">UCP Director&#039;s Office</a></li> <li><a href="http://www.internal-ucp.ucar.edu" title="">UCP Budget &amp; Administration</a></li> </ul> </li> <li><a href="http://internal.ucar.edu" title="">UCAR Internal Content</a> <ul> <li><a href="https://internal.ucar.edu/presidents-page" title="Presidents Page">Presidents Page</a></li> <li><a href="https://internal.ucar.edu/counsel" title="Office of General Counsel">Office of General Counsel</a></li> <li><a href="https://internal.ucar.edu/communications" title="Communications and Media Relations">Communications &amp; Media Relations</a></li> <li><a href="https://internal.ucar.edu/president/diversity-inclusion" title="Diversity and Inclusion">Diversity &amp; Inclusion</a></li> <li><a href="https://internal.ucar.edu/oo" title="Ombuds Office">Ombuds Office</a></li> </ul> </li> <li><a href="https://operations.ucar.edu" title="UCAR Operations">UCAR Operations</a> <ul> <li><a href="https://operations.ucar.edu/fms" title="Facilities Management, Safety and Sustainability">Facilities Management, Safety and Sustainability</a></li> <li><a href="https://operations.ucar.edu/hr" title="Human Resources">Human Resources</a></li> <li><a href="https://operations.ucar.edu/it" title="Information Technology">Information Technology</a></li> <li><a href="https://operations.ucar.edu/ia" title="Internal Audit">Internal Audit</a></li> </ul> </li> </ul> </li> </ul> </div>' + ' <div id="ncarOrg"> <ul> <li><a href="http://ncar.ucar.edu/" title="">NCAR</a> <ul> <li><a href="http://ncar.ucar.edu/" title="">National Center for Atmospheric Research</a></li> <li><a href="http://www2.acom.ucar.edu" title="">Atmospheric Chemistry Observations &amp; Modeling Lab</a></li> <li><a href="http://www2.cgd.ucar.edu" title="">Climate and Global Dynamics Lab</a></li> <li><a href="http://www.cisl.ucar.edu/" title="">Computational &amp; Information Systems Lab</a></li> <li><a href="http://www.eol.ucar.edu/" title="">Earth Observing Lab</a></li> <li><a href="http://www2.hao.ucar.edu" title="">High Altitude Observatory</a></li> <li><a href="http://www.mmm.ucar.edu" title="">Mesoscale &amp; Microscale Meteorology Lab</a></li> <li><a href="http://www.ral.ucar.edu/" title="">Research Applications Lab</a></li> <li><a href="http://www.asp.ucar.edu/" title="">Advanced Study Program</a></li> <li><a href="http://library.ucar.edu" title="">NCAR Library</a></li> </ul> </li> </ul> </div>' + ' <div id="peopleOrg"> <ul> <li><a href="http://staff.ucar.edu" title="">Find People</a> <ul> <li><a href="http://staff.ucar.edu" title="">Staff Directory</a></li> <li><a href="http://staff.ucar.edu/browse/visitors" title="">Scientific Visitors</a></li> </ul> </li> </ul> </div>' + ' <div id="contactOrg"> <ul> <li><a href="http://www.ucar.edu/who-we-are/contact-us" title="">Locations/Directions</a> <ul> <li><a href="http://www.ucar.edu/who-we-are/contact-us" title="">Maps, Directions, Help</a></li> <li><a href="http://scied.ucar.edu/visit/ncar-public-tours" title="">Public &amp; School Tours</a></li> </ul> </li> </ul> </div>' + ' <div id="emergencyOrg"> <ul> <li><a href="http://www.ucar.edu/alerts" title="">Closures/Emergencies</a></li> </ul>' + '</div>';

        $(body).prepend(orgHtml);

        $(menuIDs).hover(function () {
            let menuId = $(this).attr('id');
            $('#' + menuId).find('ul ul').show();
        }, function () {
            let menuId = $(this).attr('id');
            $('#' + menuId).find('ul ul').hide();
        });

 //   }
});