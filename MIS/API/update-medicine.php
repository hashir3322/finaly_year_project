<?php 
	
	include "connection.php";

	header('Content-Type: application/json');
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: POST');
	header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type: application/json,Access-Control-Allow-Methods, Authorization, X-Requested-With');

	$data = json_decode(file_get_contents("php://input"), true);

	$name = $data['med_name'];
	$id = $data['med_id'];
	$price = $data['med_price'];
	$netPrice = $data['med_net_price'];
	$manufacturer = $data['med_manufacturer'];
	$quantity = $data['med_quantity'];

	$found = false;

	$sqlUpdate = "UPDATE medicine_tbl SET med_name = '{$name}', med_price = '{$price}', med_net_price = '{$netPrice}',med_manufacturer = '{$manufacturer}',med_quantity = '{$quantity}' WHERE med_id = '{$id}'";
		if(mysqli_query($conn, $sqlUpdate)){
		echo json_encode(array('message'  => 'Record Updated', 'status' => true));
		}else{
		echo json_encode(array('message'  => 'Could not update Record', 'status' => false));		
		}	


?>