<?php
/*
 * Get the list of bag statuses.
 *
 * Input: none.
 *
 * Output: array of bag status objects.
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
 * Bag status object definition. This script returns an array of these objects.
 */
class bagStatus {
    public $status;

    public function __construct($status) {
        $this->status = $status;
    }
};

$bagStatusList = array();

// pretend to do a DB query, but really just populate an array
array_push($bagStatusList, new bagStatus("ALL"));
array_push($bagStatusList, new bagStatus("STAGED"));
array_push($bagStatusList, new bagStatus("TOKENIZED"));
array_push($bagStatusList, new bagStatus("REPLICATED"));
array_push($bagStatusList, new bagStatus("REPLICATING"));
array_push($bagStatusList, new bagStatus("ERROR"));

//print_r($bagStatusList);

// return a JSON array
echo json_encode($bagStatusList);
?>