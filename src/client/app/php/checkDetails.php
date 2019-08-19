<?php
/*
 * Get the storage info of a set of servers.
 *
 * Input: an object containing a node name and bag status.
 *
 * Output: array of IngestStatus objects describing the storage for each server.
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
 * IngestStatus object definition. This script returns an array of these objects.
 */
class IngestStatus {
    public $node;                   // server node
    public $identifier;             // bag identifier (name)
    public $bytes;                  // bag size in bytes
    public $files;                  // file count
    public $status;                 // collection status
    public $owner;                  // data provider owner

    public function __construct($node, $identifier, $bytes, $files, $status, $owner) {
        $this->node = $node;
        $this->identifier = $identifier;
        $this->bytes = $bytes;
        $this->files = $files;
        $this->status = $status;
        $this->owner = $owner;
    }
};

// read the input, convert it to a string, then into JSON
$select = json_decode(file_get_contents("php://input"));
//print_r($select . "\n");

/*
 * pretend to do a DB lookup, but really just reset $storage. All of the JSON
 * elements in the array must use double quotes.
 */
$storage = json_decode('[
        {"node": "UCSD", "identifier": "1001", "bytes": "12034789206", "files": "4615", "status": "STAGED", "owner": "Sanders"},
        {"node": "UCSD", "identifier": "1002", "bytes": "8671019364849", "files": "98671", "status": "TOKENIZED", "owner": "Thompson"},
        {"node": "NCAR", "identifier": "1003", "bytes": "12700406", "files": "210096", "status": "ERROR", "owner": "Isaac"}
    ]');
//print_r("storage=" . $storage . "\n");

// get the storage info for each server
for ($i = 0, $len = sizeof($storage); $i < $len; $i++) {
    array_push($storageStatus,
        new IngestStatus($storage[$i]->node,
                          $storage[$i]->identifier,
                          $storage[$i]->bytes,
                          $storage[$i]->files,
                          $storage[$i]->status,
                          $storage[$i]->owner
        )
    );
} // for

// return a JSON array
echo json_encode($storageStatus);
?>