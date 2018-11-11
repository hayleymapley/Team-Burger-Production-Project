

$total = "1";


document.getElementById("total1").innerHTML = 5 + 6;

var ingredientsArray = <?php echo json_encode($arr);?>;

for(var i=0; i<ingredientsArray.length; i++){
    alert(ingredientsArray[i]);
}

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