
 <?php
$dbconn3 = pg_connect("host=db.ecs.vuw.ac.nz dbname=yauloui_jdbc user=yauloui password=pass123")
//$dbconn3 = pg_connect("host=localhost dbname=yauloui")
or die("Can't connect to database".pg_last_error());

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




<p>Total Cost: $<span id="total"></span></p>
<output id = "total1" name=total> test</output><br>

Lettuce Bun: <input type="checkbox" id="Bun_Lettuce"><br>

Standard bun: <input type="checkbox" id="Bun_Standard"><br>

veggie lettuce: <input type="number" id="Vege_Lettuce"><br>

Vege tomato: <input type="number" id="Vege_Tomato"><br>

vege onion: <input type="number" id="Vege_Onion"><br>

vege pickles: <input type="number" id="Vege_Pickles"><br>

vege beetroot: <input type="number" id="Vege_Beetroot"><br>

cheese cheddar: <input type="number" id="Cheese_Cheddar"><br>

cheese vegan: <input type="number" id="Cheese_Vegan"><br>

patty beef: <input type="number" id="Patty_Beef"><br>

patty chicken: <input type="number" id="Patty_Chicken"><br>

patty tofu: <input type="number" id="Patty_Tofu"><br>

sauce tomato: <input type="number" id="Sauce_Tomato"><br>

sauce chilli: <input type="number" id="Sauce_Chilli"><br>

Sauce aioli: <input type="number" id="Sauce_Aioli"><br>

<form action="orderConfirm.php" method="post">
<input type="text" name="name" placeholder="Name" required></input>
<input type="text" name="email" placeholder="Email" required></input>
<button name ="confirm" type="submit">Confirm</button>

</form>

<output id= "total2" >test</output>



<script type="text/javascript">


$total = "1";

document.getElementById("total1").innerHTML = 5 + 6;


var ingredientsArray = <?php echo json_encode($arr);?>;
var i = 0;

//for(i;i<ingredientsArray.length;i++){
//	var current =  ingredientsArray[i];
//	var name = current.name;
//	var quantity = current.quantity;
//	alert(name.toString()+ " "+ quantity.toString());
//}

function myFunction(element) {
	  var id = element;
	  // Get the number
//	  var number = document.getElementById("Bun_Lettuce");
	  //var number = document.getElementById(id);
	  var quantity = document.getElementById(id);
	  // Get the output text
//	  var text = document.getElementById("Bun_Lettuce1");
	  var text = document.getElementById(id + "1");
	  
	  var total = document.getElementById("total1");

	  // If the number is checked, display the output text
// 	  if (number.checked == true){
// 	    text.style.display = "block";
// 	    total.innerHTML = 1;
// 	  } else {
// 	    text.style.display = "none";
// 	  }

		//Deal with input type "number"<input type="number" name="quantity" min="1" max="5">
// 	  if (quantity > 0){
// 		    text.style.display = "block";
// 		    total.innerHTML = 1;
// 		  } else {
// 		    text.style.display = "none";
// 		  }
	}
</script>



