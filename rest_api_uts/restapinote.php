<?php
require_once "dbconn/dbconn.php";

if (function_exists($_GET['function'])) {
    $_GET['function']();
}

function get_coffeenotes()
{
    global $connect;
    $query = $connect->query("SELECT * FROM coffee");
    while ($row = mysqli_fetch_object($query)) {
        $data[] = $row;
    }
    $response = array(
        'status' => 1,
        'message' => 'success',
        'data' => $data
    );
    header('Content-Type: application/json');
    echo json_encode($response);
}

function insert_coffeenotes()
{
    global $connect;
    $check = array('nama' => '', 'kategori' => '', 'deskripsi' => '', 'ukuran' => '');
    $check_match = count(array_intersect_key($_POST, $check));
    if ($check_match == count($check)) {
        $result = mysqli_query($connect, "INSERT INTO coffee  SET
            nama = '$_POST[nama]',
            kategori = '$_POST[kategori]',
            deskripsi = '$_POST[deskripsi]',
            ukuran = '$_POST[ukuran]'");

        if ($result) {
            $response = array(
                'status' => 1,
                'message' => 'Insert Success'
            );
        } else {
            $response = array(
                'status' => 0,
                'message' => 'Insert Failed.'
            );
        }
    } else {
        $response = array(
            'status' => 0,
            'message' => 'Wrong Parameter'
        );
    }
    header('Content-Type: application/json');
    echo json_encode($response);
}

function update_coffeenotes()
{
    global $connect;
    if (!empty($_GET["id"])) {
        $id = $_GET["id"];
    }
    $check = array('nama' => '', 'kategori' => '', 'deskripsi' => '', 'ukuran' => '');
    $check_match = count(array_intersect_key($_POST, $check));
    if ($check_match == count($check)) {

        $result = mysqli_query($connect, "UPDATE coffee SET               
            nama = '$_POST[nama]',
            kategori = '$_POST[kategori]',
            deskripsi = '$_POST[deskripsi]',
            ukuran = '$_POST[ukuran]' WHERE id = $id");

        if ($result) {
            $response = array(
                'status' => 1,
                'message' => 'Update Success'
            );
        } else {
            $response = array(
                'status' => 0,
                'message' => 'Update Failed'
            );
        }
    } else {
        $response = array(
            'status' => 0,
            'message' => 'Wrong Parameter',
            'data' => $id
        );
    }
    header('Content-Type: application/json');
    echo json_encode($response);
}

function delete_coffeenotes()
{
    global $connect;
    $id = $_GET['id'];
    $query = "DELETE FROM coffee WHERE id=" . $id;
    if (mysqli_query($connect, $query)) {
        $response = array(
            'status' => 1,
            'message' => 'Delete Success'
        );
    } else {
        $response = array(
            'status' => 0,
            'message' => 'Delete Fail.'
        );
    }
    header('Content-Type: application/json');
    echo json_encode($response);
}
