/**
 * 지역별 검색 활성!
 */
var provSelec = document.querySelector("#province");

var provBtn = document.querySelector("#prov-search");


function searchByProvince(){
	var opt = provSelec.options[provSelec.selectedIndex].text;
	console.log(opt);
	location.href=`lost.do?command=searchByProv&province=${opt}&page=1`;
}


provBtn.addEventListener("click",searchByProvince);