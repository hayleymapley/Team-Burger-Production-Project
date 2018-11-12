const state = [
    {id:1, value:"Lettuce", isSoldOut:false, isChecked:false, price:3},
    {id:2, value:"Patty", isSoldOut:true, isChecked:false, price:6},
    {id:3, value:"Tomato", isSoldOut:false, isChecked:false, price:2},
    {id:4, value:"Beetroot", isSoldOut:false, isChecked:false, price:1.5},
    {id:5, value:"Pickles", isSoldOut:false, isChecked:false, price:2}
     //continue with all ingredients
] //state = empty object (object we see printed on right)


const sync = (id, isChecked) => { //sync takes value of tickbox and if its checked or not (true/false)
    state.map(item => {
        if(item.id == id) {
            item.isChecked=isChecked
        }
    })

    renderList() //renders (ie html)
}


const renderList = () => { //loops through all values, sees if true or false. If true, makes an HTML list with the thing inside it
    const list = state.map(item => //map = for each loop
        item.isChecked ? `<li>${item.value}</li>` : null //html list
    )
    .filter(e => e)
    .join('') //Join = puts everything into a string

    console.log(list) //debug thing
    
    document.getElementById('list').innerHTML = list //inserts lists into inner HTML. Join = puts everything into a string
}

const renderOrderForm = () => { //loops over key names and creates a new string li keyname li
    const orderOptions = state.map(item =>`<input 
        id="${item.id}"
        type="checkbox" 
        value="${item.value}" 
        onclick="sync(this.id, this.checked)"
        ${item.isSoldOut ? "disabled" : ""} 
        />${item.value} ${item.price} </br>`).join("")

    document.getElementById('orderForm').innerHTML = orderOptions //string of HTML. If sold out, item will be disabled
}

