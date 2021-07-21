/**
 * 
 */
var tel = document.getElementsByName("tel")[0];

function checkValidTel(){
	var hyphen1 = tel.value.charAt(3);
	var hyphen2 = tel.value.charAt(8);
	var hyphen3 = tel.value.charAt(7);
	
	if(hyphen1==='-' && hyphen2==='-'){
		alert(`알맞은 전화번호 형식입니다`);
		return true;
	}else if(hyphen1 === '-' && hyphen3==='-'){
		alert(`알맞은 전화번호 형식입니다`);
		return true;
	}else{
		alert(`잘못된 전화번호 형식입니다`);
		tel.value="";
		return false;
	}
}

tel.addEventListener("change",checkValidTel);