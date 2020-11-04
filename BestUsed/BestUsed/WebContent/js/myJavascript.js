let errors = [];
let itemsNum = [];

document.addEventListener('DOMContentLoaded', function() {
	let checkboxes = document.querySelectorAll('[id^="checkboxEvent"]');
	console.log(checkboxes);
	for(let checkbox of checkboxes){
		checkbox.addEventListener('change', function(event){
			let index = checkbox.id.charAt(checkbox.id.length-1);
			
			let input = document.getElementById(index+'-'+'show');
			if(event.target.checked){
				input.readOnly = false;
			} else{
				input.readOnly = true;
			}
		})
	}
}, false);

function checkQuantity(e){
	let index = e.path[0].id.charAt(0)-'0';
	let i;
	let N = e.target.value.length;
	let alert1 = document.getElementById('alert-'+e.path[0].id.charAt(0));
	let alert2 = document.getElementById('alert--'+e.path[0].id.charAt(0));
	for(i=0; i<N; i++){
		let ch = e.target.value.charAt(i);
		
		if(ch<'0'||ch>'9'){
			alert2.style="display:none";
			alert1.style="display: display; color: red";
			errors[index] = true;
			itemsNum[index]=0;
			break;
		}
	}
	if(i==N){
		alert1.style="display:none";
		errors[index]=false;
		itemsNum[index]=0;
	}
	
	if(!errors[index]){
		if(e.target.value==null||e.target.value==""){
			return;
		}
		const Http = new XMLHttpRequest();
		const url = new URL("http://localhost:9080/BestUsed/purchase/checkQuantity");
		url.searchParams.append('id', e.path[0].id.charAt(0));
		url.searchParams.append('requiredQuantity', e.target.value);
		
		Http.open("GET", url);
		Http.send();
		
		Http.onreadystatechange=()=>{
			if(Http.responseText.length>0){
				alert2.innerHTML=Http.responseText;
				alert2.style="display: display; color: red";
				errors[index] = true;
				itemsNum[index]=0;
			} else{
				alert2.style="display:none";
				errors[index]=false;
				itemsNum[index]=e.target.value;
			}
		}
	}
	
}

function checkform() {
	for(let error of errors){
		if(error){
			return;
		}
	}
	console.log();
	for(let num of itemsNum){
		if(num>0){
			document.getElementById("orderForm").submit();
		}
	}
}

function checkPaymentInfo(){
	let form = document.getElementById('payInfoForm');
	if(form["creditCardNumber"].value.length==0||form["expirationDate"].value.length==0||form["cvvCode"].value.length==0||form["cardHolderName"].value.length==0){
		document.getElementById('payAlert').style="display: display; color:red";
		return;
	}
	document.getElementById('payAlert').style="display:none";
	form.submit();
}

function checkShippingInfo(){
	let form = document.getElementById('shipInfoForm');
	if(form["name"].value.length==0||form["addressLine1"].value.length==0||form["city"].value.length==0||form["state"].value.length==0||form["zip"].value.length==0){
		document.getElementById('shipAlert').style="display: display; color:red";
		return;
	}
	document.getElementById('shipAlert').style="display:none";
	form.submit();
}