<?php

require('connect_db.php');

if($_SERVER['REQUEST_METHOD'] == 'GET'){
	$operation = $_GET['operation'];

	if($operation == "follower"){

	$username = $_GET['username'];

	$query = 'SELECT events.event_id, event_name, location, start_date, end_date, repeat_interval, time FROM follows JOIN users ON follows.user_id = users.user_id JOIN events ON follows.event_id = events.event_id WHERE users.username ="' . $username .'"';

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
	    mysqli_free_result($result);
	} else {
		echo "Error";
}

} else if ($operation == "owner") {
	$username = $_GET['username'];

	$query = 'SELECT event_id,event_name, location, start_date, end_date, repeat_interval, time FROM users JOIN events ON user_id=events.owner_id WHERE username="' . $username . '"';


	$result = mysqli_query($dbc , $query);

	if ($result) {
		
	    # For each row result, generate a table row with ID number
	    $rows = array();
	    while ( $row = mysqli_fetch_array( $result , MYSQLI_ASSOC ) )
	    {
	    	$rows[] = $row;
    	}
		    echo json_encode($rows);
		    mysqli_free_result($result);
		} else {
		echo "Error";
}
}
}



//SELECT event_name FROM follows JOIN users ON follows.userID = users.userID JOIN events ON follows.eventID = events.eventID WHERE userID ="1"

