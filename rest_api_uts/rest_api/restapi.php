<?php
    require_once "dbconn/dbconn.php";

    if(function_exists($_GET['function'])){
        $_GET['function']();
    }

    function get_akunpasien()
    {
        global $connect;
        $query = $connect->query("SELECT * FROM tb_akunpasien");
        while($row=mysqli_fetch_object($query))
        {
            $data[] = $row;
        }
        $response=array(
                        'status' => 1,
                        'message' => 'success',
                        'data' => $data
                    );
        header('Content-Type: application/json');
        echo json_encode($response); 
    }           
?>