<?php 

	include "connection.php";
	header('Content-Type: application/json');
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: POST');
	header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type: application/json,Access-Control-Allow-Methods, Authorization, X-Requested-With');

	$data = json_decode(file_get_contents("php://input"), true);
	$success = false;
	foreach($data as $item){
		$med_id = $item['billId'];
		$med_quantity = $item['billQty'];
		$sqlFind = "SELECT med_quantity FROM medicine_tbl WHERE med_id = '{$med_id}'";
		$resultFind = mysqli_query($conn,$sqlFind) or die (mysqli_error($conn));
		if(mysqli_num_rows($resultFind) > 0){
			$output = mysqli_fetch_assoc($resultFind);
			$dbQty = $output['med_quantity'];
			$dedQty = $dbQty - $med_quantity;

			$sqlUpdate = "UPDATE medicine_tbl SET med_quantity = '{$dedQty}' WHERE med_id = '{$med_id}'";
			if(mysqli_query($conn, $sqlUpdate)){
				$success = true;
			}else{
				$success = false;				
			}
		}
	}
	if($success == true){
		echo json_encode(array('message'  => 'Bill Calculated', 'status' => true));
	}else if($success == false){
		echo json_encode(array('message'  => 'Bill Not calculated', 'status' => true));
	}

	
?>