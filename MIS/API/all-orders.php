<?php 
	
	header('Content-Type: application/json');
	header('Access-Control-Allow-Origin: *');

	include "connection.php";


	$sql = "SELECT *FROM order_tbl";
	$result = mysqli_query($conn,$sql) or die (mysqli_error($conn));

	if(mysqli_num_rows($result) > 0){
		$output = mysqli_fetch_all($result, MYSQLI_ASSOC);
		echo json_encode($output);
	}else{
		echo json_encode(array('message'  => 'No Record Found.', 'status' => False));
	}
?>