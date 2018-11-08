 <?php
$dbconn3 = pg_connect("host=db.ecs.vuw.ac.nz dbname=yauloui_jdbc user=yauloui password=pass123")
or die("Can't connect to database".pg_last_error());
//connect to a database named "mary" on the host "sheep" with a username and password

$result = pg_query($dbconn3,"SELECT * FROM stock_ingredients");

while ($row = pg_fetch_row($result)) {
    echo "Row: $row[0]";
}

//echo "$result";


?>


<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/bootstrap-theme.css" rel="stylesheet" />
    <title>Delicious Burger!</title>
  </head>
  <body>
  <div class="container">
     <h1>Welcome to Delicious Burger!</h1><br>
     <h2>Start your burger order</h2>
      
     <button type="button" class="btn btn-primary" onclick = >New Customer</button>
     <button type="button" class="btn btn-primary" onclick = >Returning Customer</button>
</div>




    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  </body>
</html>