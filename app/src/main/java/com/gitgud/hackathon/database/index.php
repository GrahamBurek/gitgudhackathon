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
		}
	}

	if ($operation == "login") {
		
	}
}
 ?>