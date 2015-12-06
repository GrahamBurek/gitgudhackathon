<?php

require('connect_db.php');

$id = $_GET['id'];

$query = 'SELECT * FROM events WHERE event_id=' . $id;

$result = mysqli_query($dbc , $query);

if ($result) {
	
    
    $rows = array();
    while ( $row = mysqli_fetch_array( $result , MYSQLI_ASSOC ) )
    {
    	$rows[] = $row;
      
    }
    echo json_encode($rows);

} else {
	echo "Error";
}