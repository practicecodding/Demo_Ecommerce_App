<?php

header('Content-Type: application/json; charset=utf-8');

$con = mysqli_connect( "localhost","wpkkcuac_root","Mmy_databaseH","wpkkcuac_my_database" );

$sql = " SELECT * FROM carousel_table ORDER BY id DESC" ;

$result = mysqli_query($con,$sql);

$data = array();

foreach($result as $item){
	
	$carousels['id'] = (int) $item['id'];
	$carousels['image_url'] = $item['image_url'];
	$carousels['caption'] = $item['caption'];
	array_push($data,$carousels);
	
}

echo json_encode($data);

?>