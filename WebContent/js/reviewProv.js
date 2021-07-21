/**
 * 지역별 검색 활성!
 */
var provSelec = document.querySelector("#province");
var rvProvBtn = document.querySelector("#reviewProv");

function searchReviewByProvince(){
	var opt = provSelec.options[provSelec.selectedIndex].text;
	console.log(opt);
	location.href=`review.do?command=searchReviewByProv&province=${opt}&page=1`;
}

rvProvBtn.addEventListener("click",searchReviewByProvince);