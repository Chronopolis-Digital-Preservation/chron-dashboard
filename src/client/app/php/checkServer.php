<?php
/*
 * Determine the status of a set of servers.
 *
 * Input: an array of server objects containing a node name.
 *
 * Output: array of ServerStatus objects describing the status for each server.
 */

$serverStatus = array();

// allow access from any origin
if (isset($_SERVER['HTTP_ORIGIN'])) {
    header("Access-Control-Allow-Origin: {$_SERVER['HTTP_ORIGIN']}");
    header('Access-Control-Allow-Credentials: true');
    header('Access-Control-Max-Age: 86400');    // cache for 1 day
}

// Access-Control headers are received during OPTIONS requests
if ($_SERVER['REQUEST_METHOD'] == 'OPTIONS') {

    if (isset($_SERVER['HTTP_ACCESS_CONTROL_REQUEST_METHOD']))
        header("Access-Control-Allow-Methods: GET, POST, OPTIONS");

    if (isset($_SERVER['HTTP_ACCESS_CONTROL_REQUEST_HEADERS']))
        header("Access-Control-Allow-Headers: {$_SERVER['HTTP_ACCESS_CONTROL_REQUEST_HEADERS']}");

    exit(0);
}
header('Content-Type: application/json');

/*
 * ServerStatus object definition. This script returns an array of these objects.
 */
class ServerStatus {
    public $node;
    public $status;
    public $message;

    public function __construct($node, $status, $message) {
        $this->node = $node;
        $this->status = $status;
        $this->message = $message;
    }
};

/*
 * Check whether a website is online.
 *
 * Input:
 *    url - host name or IP address
 *
 * Output:
 *    true/false - indicates whether the website is online
 */
function checkServer($url) {
    $agent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:38.0) Gecko/20100101 Firefox/38.0";

    // check whather a valid url was provided
    if (!filter_var($url, FILTER_VALIDATE_URL)) {
        return false;
    } // if

    // initialize the curl session
    $ch = curl_init($url);

    // set the content of the User-Agent header
    curl_setopt($ch, CURLOPT_USERAGENT, $agent);

    // return the transfer as a string
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);

    // disable output verbose information
    curl_setopt($ch,CURLOPT_VERBOSE,false);

    // max number of seconds to allow cURL function to execute
    curl_setopt($ch, CURLOPT_TIMEOUT, 3);

    curl_exec($ch);

    // get HTTP response code
    $httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);

    curl_close($ch);

    if ($httpCode >= 200 && $httpCode < 300) {
        return true;
    } else {
        return false;
    } // if
} // checkServer()

// read the input, convert it to a string, then into JSON
$servers = json_decode(file_get_contents("php://input"));
//print_r($servers . "\n");

/*
 * pretend to do a DB lookup, but really just reset $servers. All of the JSON
 * elements in the array must use double quotes.
 */
$servers = json_decode('[
        {"node": "UCAR", "url": "https://chronopolis.ucar.edu/ace-am/"},
        {"node": "UCSD", "url": "https://chron-monitor.umiacs.umd.edu/ace-am/"},
        {"node": "UMIACS", "url": "https://chron.ucsd.edu/ace-am/"},
        {"node": "MadeItUp", "url": "https://www.MadeItUp.com"}
    ]');
//print_r("servers=" . $servers . "\n");

// check the status for each server
for ($i = 0, $len = sizeof($servers); $i < $len; $i++) {
    array_push($serverStatus,
        new ServerStatus($servers[$i]->node, checkServer($servers[$i]->url), ""));
} // for

// return a JSON array
echo json_encode($serverStatus);
?>