<?php
    error_reporting(0);
    session_start();
    if(!isset($_SESSION['username'])) 
    {
        header("location:login.php");
    }
    elseif($_SESSION['usertype']=='student')
    {
        header("location:login.php");
    }

    $host="localhost";
    $user="root";
    $password="";
    $db="stu_man";
    $data=mysqli_connect($host,$user,$password,$db);

    $sql="SELECT * FROM user WHERE usertype='student' ";

    $result=mysqli_query($data,$sql);
?>


<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Admin Dashboard</title>

	<link rel="stylesheet" type="text/css" href="admin.css">

	<!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <style>
        .table_th
        {
            padding: 20px;
            font-size=20px;
        }
        .table_td 
        {
            padding:20px;
            background-color : skyblue;
        }
    </style>

</head>
<body>

	<header class="header">
		
		<a href="adminhome.php">Admin Dashboard</a>

		<div class="logout">
			
			<a href="logout.php" class="btn btn-primary">Logout</a>

		</div>

	</header>


	<aside>
		
		<ul>
			<li>
				<a href="add_student.php">Add Student</a>
			</li>

			<li>
				<a href="view_student.php">View Student</a>
			</li>
		</ul>


	</aside>


	<div class="content">
		<center>
		<h1>Student Data</h1> 
        <?php
            if($_SESSION['message'])
            {
                echo $_SESSION['message'];
            }
            unset($_SESSION['message']);
        ?>
        
        <br>
        <table border="1px">
            <tr>
                <th class="table_th">Username</th>
                <th class="table_th">Email</th>
                <th class="table_th">Phone</th>
                <th class="table_th">Password</th>
                <th class="table_th">Delete</th>
            </tr>

            <?php
            while($info=$result->fetch_assoc())
            {

            ?>


            <tr>
                <td class="table_td">
                    <?php
                    echo "{$info['username']}";
                    ?>
                </td>
                <td class="table_td">
                <?php
                    echo "{$info['email']}";
                    ?>
                </td>
                <td class="table_td">
                <?php
                    echo "{$info['phone']}";
                    ?>
                </td>
                <td class="table_td">
                <?php
                    echo "{$info['password']}";
                    ?>
                </td>
                <td class="table_td">
                <?php
                    echo "<a onClick=\" javascript:return confirm('Are you sure to delete this');\" href='delete.php?student_id={$info['id']}'>Delete </a>";
                    ?>
                </td>
            </tr>

            <?php
            
            }

            ?>
        </table>
        </center>
	</div>

</body>
</html>
