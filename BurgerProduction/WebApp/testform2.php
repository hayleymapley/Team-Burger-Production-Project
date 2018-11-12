
 <?php
$dbconn3 = pg_connect("host=db.ecs.vuw.ac.nz dbname=yauloui_jdbc user=yauloui password=pass123")
//$dbconn3 = pg_connect("host=localhost dbname=yauloui")
or die("Can't connect to database".pg_last_error());

//Need logic about displaying out of stock ingredients
$query = "SELECT name,quantity FROM stock_ingredients where quantity > 20 ORDER BY orderthiscolumnby asc";
$result = pg_query($query);
if (!$result) {
    echo "Problem with query " . $query . "<br/>";
    echo pg_last_error();
    exit();
}
$i = "hi";
$arr = pg_fetch_all($result);
foreach ($arr as &$value) {
print_r($value);
}
?>

<!DOCTYPE html>
<html>
<!--  <script type="text/javascript" src="main2.js"></script>-->




<p>Total Cost: $<span name="total"></span></p>
<output name = "total1" name=total> test</output><br>

<form action="orderConfirm.php" method="post">

Lettuce Bun: <input type="number" name="Bun_Lettuce" min="0" max="1"><br>

Standard bun: <input type="number" name="Bun_Standard" min="0" max="1"><br>

veggie lettuce: <input type="number" name="Vege_Lettuce"><br>

Vege tomato: <input type="number" name="Vege_Tomato"><br>

vege onion: <input type="number" name="Vege_Onion"><br>

vege pickles: <input type="number" name="Vege_Pickles"><br>

vege beetroot: <input type="number" name="Vege_Beetroot"><br>

cheese cheddar: <input type="number" name="Cheese_Cheddar"><br>

cheese vegan: <input type="number" name="Cheese_Vegan"><br>

patty beef: <input type="number" name="Patty_Beef"><br>

patty chicken: <input type="number" name="Patty_Chicken"><br>

patty tofu: <input type="number" name="Patty_Tofu"><br>

sauce tomato: <input type="number" name="Sauce_Tomato"><br>

sauce chilli: <input type="number" name="Sauce_Chilli"><br>

Sauce aioli: <input type="number" name="Sauce_Aioli"><br>


<input type="text" name="name" placeholder="Name" required></input>
<input type="text" name="email" placeholder="Email" required></input>
<button name ="confirm" type="submit">Confirm</button>

</form>

<output id= "total2" >test</output>

<script type="text/javascript">

var ingredientsArray = <?php echo json_encode($arr);?>;

</script>



