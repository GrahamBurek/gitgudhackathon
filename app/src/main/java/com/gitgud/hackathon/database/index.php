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
			$query='SELECT username, password FROM users WHERE username="' . $username . '" AND password="' . $password . '"';

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
}
 ?>