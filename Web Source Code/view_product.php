<?php

header('Content-Type: application/json; charset=utf-8');

$con = mysqli_connect( "localhost","wpkkcuac_root","Mmy_databaseH","wpkkcuac_my_database" );

$sql = " SELECT * FROM product_table ORDER BY id DESC" ;

$result = mysqli_query($con,$sql);

$data = array();

foreach($result as $item){
	
	$products['id'] = $item['id'];
	$products['id'] = $item['id'];
	$products['id'] = $item['id'];
	$products['id'] = $item['id'];
	$products['id'] = $item['id'];
	$products['id'] = $item['id'];
	$products['id'] = $item['id'];
	array_push($data,$products);
	
}

echo json_encode($data);

?>