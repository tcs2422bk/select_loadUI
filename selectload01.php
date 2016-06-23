
<?php
include 'leo_API.php';
//$sql = "SELECT * FROM stat"; //在test資料表中選擇所有欄位
$sql="SELECT * FROM stat ORDER BY id DESC LIMIT 1";

$result = mysqli_query($link,$sql); // 執行SQL查詢
$row = mysqli_fetch_assoc($result);
echo $row[humid];   
echo $row[temp];  
echo $row[weather];
?>