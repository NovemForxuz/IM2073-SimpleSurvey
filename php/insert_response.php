<?php
include 'DatabaseConfig.php';

//Create connection
$link=mysqli_connect($db_host,$db_user,$db_pass,$db_database,$db_port);

//Check connection
if(mysqli_connect_errno())
{
	echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

//getting data form android using key-value pair
$response_qnNo = urldecode($_POST['questionNO']);
$response_choice = urldecode($_POST['choice']);
$response_answer = urldecode($_POST['answer']);
$response_user = urldecode($_POST['username']);
$response_comments = urldecode($_POST['comments']);

//sql query statement
$sql_insert = "insert into responses values (".$response_qnNo.",'". $response_choice."','".$response_answer."','".$response_user."','".$response_comments."' )"  ;

if(mysqli_query($link,$sql_insert)){
	echo "Data Submit Successfully";
	echo "\n".$sql_insert;
}else{
	echo "Try again";
	echo "\n". $sql_insert;
}
mysqli_close($link);

?>