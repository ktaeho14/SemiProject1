/**
 * 
 */
function showMine(val){
	if(val==="" ||val===null||val===undefined){
		//비회원인 경우
		alert(`회원가입 및 로그인을 부탁드립니다`);
		return false;
	}else{
		location.href=`lost.do?command=showLostMine&writer=${val}&page=1`;
		return true;
	}
}