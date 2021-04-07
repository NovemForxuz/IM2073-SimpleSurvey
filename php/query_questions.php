<?php
include 'DatabaseConfig.php';

//Create connection
$link=mysqli_connect($db_host,$db_user,$db_pass,$db_database,$db_port);

//Check connection
if(mysqli_connect_errno())
{
	echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

//Select all of our android from table 'questions'
$sql = "SELECT * from questions";

//Confirm there are results
if ($result = mysqli_query($link, $sql)) 
{
	//We have results, create an array to hold the results
	//and an array to hold the data
	$resultArray = array();
	$tempArray = array();

	//loop through each result
	while($row = $result->fetch_object())
	{
		//Add each result into the results array
		$tempArray = $row;
		array_push($resultArray, $tempArray);
	}

	//Encode the array to JSON and output the results
	echo json_encode(($resultArray));
}

//Close connections
mysqli_close($link);
?>