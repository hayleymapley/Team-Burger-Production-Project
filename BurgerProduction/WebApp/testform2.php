
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

    //Add new customer row and print just ID
    //$sql = pg_query($dbconn3,"INSERT INTO customers (name,email) VALUES('john', 'john@example.com')");
    $query = "SELECT customer_ID FROM customers where email = 'bob@example.com'";
    $result2 = pg_query($query);
    if (!$result2) {
        echo "Problem with query " . $query . "<br/>";
        echo pg_last_error();
        exit();
    }
    
    $myrow = pg_fetch_assoc($result2);
    $customerID = $myrow['customer_id'];
    //$value2 = $myrow[numofratings];
    
    echo "customerID =" . "$customerID <br/>";
?>







<!DOCTYPE html>
<html>
<!--  <script type="text/javascript" src="main2.js"></script>-->




<p>Total Cost: $<span id="total"></span></p>
<output id = "total1" name=total> test</output><br>

Lettuce Bun: <input type="checkbox" id="Bun_Lettuce" onclick="myFunction(this.id)" value="5">
<p id="Bun_Lettuce1" style="display:none">you selected a lettuce bun</p><br>

standard bun: <input type="checkbox" id="Bun_Standard" onclick="myFunction(this.id)" value="5">
<p id="Bun_Standard1" style="display:none">you selected a patty</p><br>

veggie lettuce: <input type="checkbox" id="Vege_Lettuce" onclick="myFunction(this.id)" value="5">
<p id="Vege_Lettuce1" style="display:none">you selected a patty</p><br>

Vege tomato: <input type="checkbox" id="Vege_Tomato" onclick="myFunction(this.id)" value="5">
<p id="Vege_Tomato1" style="display:none">you selected a patty</p><br>

vege onion: <input type="checkbox" id="Vege_Onion" onclick="myFunction(this.id)" value="5">
<p id="Vege_Onion1" style="display:none">you selected a patty</p><br>

vege pickles: <input type="checkbox" id="Vege_Pickles" onclick="myFunction(this.id)" value="5">
<p id="Vege_Pickles1" style="display:none">you selected a patty</p><br>

vege beetroot: <input type="checkbox" id="Vege_Beetroot" onclick="myFunction(this.id)" value="5">
<p id="Vege_Beetroot1" style="display:none">you selected a patty</p><br>

cheese cheddar: <input type="checkbox" id="Cheese_Cheddar" onclick="myFunction(this.id)" value="5">
<p id="Cheese_Cheddar1" style="display:none">you selected a patty</p><br>

cheese vegan: <input type="checkbox" id="Cheese_Vegan" onclick="myFunction(this.id)" value="5">
<p id="Cheese_Vegan1" style="display:none">you selected a patty</p><br>

patty beef: <input type="checkbox" id="Patty_Beef" onclick="myFunction(this.id)" value="5">
<p id="Patty_Beef1" style="display:none">you selected a patty</p><br>

patty chicken: <input type="checkbox" id="Patty_Chicken" onclick="myFunction(this.id)" value="5">
<p id="Patty_Chicken1" style="display:none">you selected a patty</p><br>

patty tofu: <input type="checkbox" id="Patty_Tofu" onclick="myFunction(this.id)" value="5">
<p id="Patty_Tofu1" style="display:none">you selected a patty</p><br>

sauce tomato: <input type="checkbox" id="Sauce_Tomato" onclick="myFunction(this.id)" value="5">
<p id="Sauce_Tomato1" style="display:none">you selected a patty</p><br>

sauce chilli: <input type="checkbox" id="Sauce_Chilli" onclick="myFunction(this.id)" value="5">
<p id="Sauce_Chilli1" style="display:none">you selected a patty</p><br>

Sauce aioli: <input type="checkbox" id="Sauce_Aioli" onclick="myFunction(this.id)" value="5">
<p id="Sauce_Aioli1" style="display:none">you selected a patty</p><br>

<form action="">
<input type="text" id="name" required></input>
<input type="text" id="email" required></input>
<button type="submit">Confirm</button>
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



alert(ingredientsArray2.join('\n'));




function myFunction(element) {
	  var id = element;
	  // Get the checkbox
//	  var checkBox = document.getElementById("Bun_Lettuce");
	  var checkBox = document.getElementById(id);
	  // Get the output text
//	  var text = document.getElementById("Bun_Lettuce1");
	  var text = document.getElementById(id + "1");
	  
	  var total = document.getElementById("total1");

	  // If the checkbox is checked, display the output text
	  if (checkBox.checked == true){
	    text.style.display = "block";
	    total.innerHTML = 1;
	  } else {
	    text.style.display = "none";
	  }
	}
</script>



