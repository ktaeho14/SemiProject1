/**
 * 지역별 검색 활성!
 */
var provSelec = document.querySelector("#province");

var provBtn = document.querySelector("#review-prov");


function searchByProvince(){
	var opt = provSelec.options[provSelec.selectedIndex].text;
	console.log(opt);
	location.href=`review.do?command=searchReviewByProv&province=${opt}&page=1`;
}


provBtn.addEventListener("click",searchByProvince);