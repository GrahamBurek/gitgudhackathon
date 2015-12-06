<?php

require('connect_db.php');

$query = 'SELECT event_name FROM events';

$result = mysqli_query($dbc , $query);

if ($result) {
	
    # For each row result, generate a table row with ID number
    while ( $row = mysqli_fetch_array( $result , MYSQLI_ASSOC ) )
    {
      echo $row['event_name'] . " ";
      
    }
} else {
	echo "Error";
}

