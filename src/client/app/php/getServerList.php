<?php
/*
 * Get the list of servers.
 *
 * Input: none.
 *
 * Output: array of Server objects.
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
 * Server object definition. This script returns an array of these objects.
 */
class Server {
    public $node;

    public function __construct($node) {
        $this->node = $node;
    }
};

$serverList = array();

// pretend to do a DB query, but really just populate an array
array_push($serverList, new Server("NCAR"));
array_push($serverList, new Server("UCSD"));
array_push($serverList, new Server("UMIACS"));
array_push($serverList, new Server("MadeItUp"));

//print_r($serverList);

// return a JSON array
echo json_encode($serverList);
?>