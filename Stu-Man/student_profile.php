<?php
    session_start();
    if(!isset($_SESSION['username'])) 
    {
        header("location:login.php");
    }
    elseif($_SESSION['usertype']=='admin')
    {
        header("location:login.php");
    }
    $host="localhost";
    $user="root";
    $password="";
    $db="stu_man";
    
    $data=mysqli_connect($host,$user,$password,$db);
    $name=$_SESSION['username'];
    $sql="SELECT * FROM user WHERE username='$name' ";

    $result=mysqli_query($data,$sql);
    $info=mysqli_fetch_assoc($result);

    if(isset($_POST['update_profile']))
    {
        $s_email=$_POST['email'];
        $s_phone=$_POST['phone'];
        $s_password=$_POST['password'];

        $sql2="UPDATE user SET email='$s_email',phone='$s_phone',password='$s_password' WHERE username='$name' ";

        $result2=mysqli_query($data,$sql2);
        if($result2) 
        {
            header('location:student_profile.php');
        }

    }

?>


<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Student Dashboard</title>

	<link rel="stylesheet" type="text/css" href="admin.css">

	<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body>

	<?php

	include 'student_sidebar.php'

	?>


	<div class="content">
		<form action="#" method="POST">
            <div>
                <label>Email</label>
                <input type="email" name="email" value="<?php echo "{$info['email']}"  ?>">
            </div>
            <div>
                <label>Phone</label>
                <input type="number" name="phone" value="<?php echo "{$info['phone']}"  ?>">
            </div>
            <div>
                <label>Password</label>
                <input type="text" name="password" value="<?php echo "{$info['password']}"  ?>">
            </div>
            <div>
                <input type="submit" name="update_profile" value="Update" >
            </div>
        </form>
	</div>

</body>
</html>
