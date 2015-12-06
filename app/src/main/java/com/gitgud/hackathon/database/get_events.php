<?php

require('connect_db.php');

$query = 'SELECT * FROM events';

$result = mysqli_query($dbc , $query);

if ($result) {
	
    # For each row result, generate a table row with ID number
    $rows = array();
    while ( $row = mysqli_fetch_array( $result , MYSQLI_ASSOC ) )
    {
    	$rows[] = $row;
      // echo $row['event_id'] . "VRIRV"
      //  . $row['event_name'] . "VRIRV"
      //  . $row['location'] . "VRIRV"
      //  . $row['start_date'] . "VRIRV"
      //  . $row['end_date'] . "VRIRV"
      //  . $row['repeat_interval'] . "VRIRV"
      //  . $row['time'] . "VRIRV";
      
    }
    echo json_encode($rows);
} else {
	echo "Error";
}

