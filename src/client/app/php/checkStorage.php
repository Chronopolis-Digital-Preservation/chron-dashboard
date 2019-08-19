<?php
/*
 * Get the storage info of a set of servers.
 *
 * Input: an array of server objects containing a node name and url.
 *
 * Output: array of StorageStatus objects describing the storage for each server.
 */

$storageStatus = array();

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
 * StorageStatus object definition. This script returns an array of these objects.
 */
class StorageStatus {
    public $node;
    public $bytes;
    public $bags;
    public $files;

    public function __construct($node, $bytes, $bags, $files) {
        $this->node = $node;
        $this->bytes = $bytes;
        $this->bags = $bags;
        $this->files = $files;
    }
};

// read the input, convert it to a string, then into JSON
$servers = json_decode(file_get_contents("php://input"));
//print_r($servers . "\n");

/*
 * pretend to do a DB lookup, but really just reset $servers. All of the JSON
 * elements in the array must use double quotes.
 */
$storage = json_decode('[
        {"node": "NCAR", "bytes": "12034789206", "bags": "1327", "files": "4615"},
        {"node": "UCSD", "bytes": "8671019364849", "bags": "", "files": "98671"},
        {"node": "UMIACS", "bytes": "12700406", "bags": "87341", "files": "210096"}
    ]');
//print_r("storage=" . $storage . "\n");

// get the storage info for each server
for ($i = 0, $len = sizeof($storage); $i < $len; $i++) {
    array_push($storageStatus,
        new StorageStatus($storage[$i]->node,
                          $storage[$i]->bytes,
                          $storage[$i]->bags,
                          $storage[$i]->files
        )
    );
} // for

// return a JSON array
echo json_encode($storageStatus);
?>