<?php

require('connect_db.php');

if($_SERVER['REQUEST_METHOD'] == 'GET'){
	$operation = $_GET['operation'];

	if ($operation == "register") {
		$username = $_GET['username'];
		$firstName = $_GET['firstName'];
		$lastName = $_GET['lastName'];
		$email = $_GET['email'];
		$password = $_GET['password'];
		$passwordRepeat = $_GET['passwordRepeat'];

		if (empty($username) || empty($firstName) || empty($lastName) || empty($email) || empty($password) || empty($passwordRepeat)) {
			echo "Make sure all fields are filled out.";
		} else if (!($password == $passwordRepeat)) {
			echo "Your passwords don't match. Try entering again.";
		} else {
			$query = 'INSERT INTO users (username, first_name, last_name, email, password) VALUES (
				"' . $username . '",'
				. '"' . $firstName . '",'
				. '"' . $lastName . '",'
				. '"' . $email . '",'
				. '"' . $password . '"'
				. ')';
			$result = mysqli_query($dbc , $query);

			if ($result) {
				echo "Registration Successful!";
			} else {
				echo "Registration Error.";
			}

			mysqli_free_result($result);
		}
	}

	if ($operation == "login") {

		$username = $_GET['username'];
		$password = $_GET['password'];

		if (empty($username) || (empty($password))) {
			echo "Make sure to fill in all fields.";
		} else {
			$query='SELECT username, password, user_id FROM users WHERE username="' . $username . '" AND password="' . $password . '"';

			$result = mysqli_query($dbc , $query);

			if (mysqli_num_rows( $result ) == 0 ) {
				echo "Login failed, check your username and password.";
		} else {
			$row = mysqli_fetch_array($result, MYSQLI_ASSOC);
			echo "Login successful " . $row['user_id'];

		}
		mysqli_free_result($result);

		}
	}

	if ($operation == "changeFollowStatus") {

		$username = $_GET['username'];
		$eventID = $_GET['eventID'];

		$query = 'SELECT * FROM follows JOIN users on follows.user_id=users.user_id WHERE users.user_id = follows.user_id AND follows.event_id=' . $eventID;


		$result = mysqli_query($dbc , $query);

			if (mysqli_num_rows( $result ) == 0 ){
				$getID = 'SELECT user_id FROM users WHERE username = ' . $username;
				$result2 = mysqli_query($dbc, $getID);
				$row = mysqli_fetch_array($result2, MYSQLI_ASSOC);
				$ID = $row['user_id'];
				mysqli_free_result($result2);

				$createQuery = 'INSERT INTO follows VALUES (' . $ID .',' . $eventID .')';
				$result3 = mysqli_query($dbc,$createQuery);
				mysqli_free_result($result3);
				echo "Now following!";
			} else {
				$getID = 'SELECT user_id FROM users WHERE username = "' . $username . '"';
				$result2 = mysqli_query($dbc, $getID);
				$row = mysqli_fetch_array($result2, MYSQLI_ASSOC);
				$ID = $row['user_id'];
				mysqli_free_result($result2);
				$query = "DELETE FROM follows WHERE user_id=" . $ID . " AND event_id=" . $eventID;
				echo $query;
				mysqli_query($dbc, $query);
				echo "No longer following.";
				
			}


	}
}
 ?>