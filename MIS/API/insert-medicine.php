<?php 
	
	include "connection.php";

	header('Content-Type: application/json');
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: POST');
	header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type: application/json,Access-Control-Allow-Methods, Authorization, X-Requested-With');

	$data = json_decode(file_get_contents("php://input"), true);

	$name = $data['med_name'];
	$price = $data['med_price'];
	$netPrice = $data['med_net_price'];
	$manufacturer = $data['med_manufacturer'];
	$quantity = $data['med_quantity'];

	$found = false;

	$sqlFind = "SELECT med_name FROM medicine_tbl";
	$findResult = mysqli_query($conn,$sqlFind) or die (mysqli_error($conn));
	if(mysqli_num_rows($findResult) > 0){
		while($row = mysqli_fetch_assoc($findResult)){
			if($row['med_name'] == $name){
				$found = true;
				echo json_encode(array('message' => 'Medicine already exists', 'status' => false));
				break;
			}else{
				continue;
			}
		}
		if($found == false){
			insertMedicine($name,$price,$netPrice,$manufacturer,$quantity,$conn);
		}
	}

	function insertMedicine($name,$price,$netPrice,$manufacturer,$quantity,$conn){

		$sqlInsert = "INSERT INTO medicine_tbl (med_name,med_price,med_net_price,med_manufacturer,med_quantity) VALUES ('{$name}','{$price}','{$netPrice}','{$manufacturer}','{$quantity}')";
		if(mysqli_query($conn, $sqlInsert)){
			echo json_encode(array('message' => 'Record was added successfully', 'status' => true));
		}else{
			echo json_encode(array('message' => 'Could not add record', 'status' => false));
		}
	}


?>