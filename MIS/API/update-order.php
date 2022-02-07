<?php 
	
	include "connection.php";

	header('Content-Type: application/json');
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: POST');
	header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type: application/json,Access-Control-Allow-Methods, Authorization, X-Requested-With');

	$data = json_decode(file_get_contents("php://input"), true);

	$orderId = $data['orderId'];
	$orderStatus = $data['orderStatus'];

	$sqlUpdate = "UPDATE order_tbl SET order_status = '{$orderStatus}' WHERE order_id = '{$orderId}' ";

	if(mysqli_query($conn, $sqlUpdate)){
		echo json_encode(array('message'  => 'Record Updated', 'status' => true));
	}else{
		echo json_encode(array('message'  => 'Could not update Record', 'status' => false));		
		}

?>