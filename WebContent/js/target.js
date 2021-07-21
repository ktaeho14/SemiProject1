/**
 * 
 */
//최외곽
//var outer = document.querySelector(".attach-apply-content");
var target = document.querySelector(".register-content");
//대댓글 틀이 복제될 위치를 위함
//var base= document.querySelector(".reply-content-container");
//대댓글 처리
var regRpToThis = document.getElementsByClassName(".register-rp-to-this");
//복사할 컨테이너
//var targetContainer = document.querySelector(".reply-content");
var focusing = "FOCUSING";
var ord = document.getElementsByName("reparent-order")[0];
	//몇 번째 버튼인지 확인하기
var btn = document.querySelectorAll(".register-rp-to-this");
var idx = 0;
var mode = document.querySelector("#changeMode");
var upBtn= document.querySelectorAll(".update-on-this");
var delBtn =document.querySelectorAll(".delete-on-this"); 
var id    = document.querySelector(".id");
//var submit= document.querySelector("input[type=submit]");

function regReply(e){
	
	if(id.value!==null||id.value.length!==0){
		target.classList.toggle(focusing);
	if(target.classList.contains(focusing)){
		target.focus();
		target.style.boxShadow="0 0 10px dodgerblue";
		mode.value="reReplyLostAnimal";
		return true;
	}
  }else{
	  //로그인정보가 없는 경우
	  alert("회원가입 및 로그인 처리를 하셔야 사용하실 수 있습니다!");
	  return false;
  }
}

function cancelCk(){
	target.classList.remove(focusing);
	target.style.boxShadow="none";
}

function handlerCheck(e){
	//console.log(btn.length);
	for(var i = 0 ; i < btn.length; i++){
		if(e===btn[i]){
			ord.value=i;
			//alert(`${ord.value}`);
		//	console.log(`ord: ${ord.value}, i: ${i}`);
			return;
		}
	}
}

function handleUpdate(e){
	for(var i = 0 ; i < upBtn.length; i++){
		if(e===upBtn[i]){
			ord.value=i;
			//alert(`${ord.value}`);
			//console.log(`ord: ${ord.value}, i: ${i}`);
			return;
		}
	}
}

function updateContent(e,compId, writer,content){
	mode.value="updateReplyOnRegLost";
	if(id.value===null || id.value.length===0){
		alert("회원가입 및 로그인을 부탁드립니다!");
		return false;
	}else if(compId!==writer){
		//일치하지 않는 사용자
		alert("잘못된 접근입니다");
		return false;
	}else{
		alert("내용을 수정해주세요!");
		id.value=writer;
		id.readOnly=true;
		id.style.backgroundColor="lightgray";
		target.value=content;
		target.focus();
		return true;
	}
}

function handleDelete(e){
	for(var i = 0 ; i < delBtn.length; i++){
		if(e===delBtn[i]){
			ord.value=i;
			//alert(`${ord.value}`);
			//console.log(`ord: ${ord.value}, i: ${i}`);
			return;
		}
	}
}

function deleteContent(e,compId, reqId,boardId, num){
	//mode.value="";
	if(id.value===0||id.value.length===0){
		alert("회원가입 및 로그인을 부탁드립니다");
		return false;
	}else if(compId!==reqId){
		alert("잘못된 접근입니다");
		return false;
	}else{
		location.href=`lost.do?command=deleteReplyOnReg&boardId=${boardId}&num=${num}&ordIdx=${ord.value}`;
		return true;
	}
}

function replyChk(){
	if(id.value===null || id.value.length===0){
		alert('로그인 및 회원가입 시 이용하실 수 있습니다');
		return false;
	}
	return true;
}

function validCheckUpdate(e,num,compId,sessionId){
	if(compId===null||sessionId===null){
		//비교대상 중 하나가 값이 없으면 유효하지 않음
		alert('잘못된 접근입니다');
		return false;
	}
	
	if(compId===sessionId){
		//아이디가 일치한다면 유효한 것!
		alert('잠시만 기다려주세요');
		location.href=`lost.do?command=updateLostArticle&num=${num}`;
		return true;
	}
	
	if(compId!==sessionId){
		//일치하지 않는 아이디가 접근하려 한다면 로케이션 변동이 없어야!
		alert('잘못된 접근입니다');
		return false;
	}
	
}

function validCheckDelete(e,num,compId,sessionId){
	if(compId===null||sessionId===null){
		//비교대상 중 하나가 값이 없으면 유효하지 않음
		alert('잘못된 접근입니다');
		return false;
	}
	
	if(compId===sessionId){
		//아이디가 일치한다면 유효한 것!
		alert('잠시만 기다려주세요');
		location.href=`lost.do?command=deleteLostArticle&num=${num}`;
		return true;
	}
	
	if(compId!==sessionId){
		//일치하지 않는 아이디가 접근하려 한다면 로케이션 변동이 없어야!
		alert('잘못된 접근입니다');
		return false;
	}
	
}

//function validation(e,val){
//	var regBan = document.getElementsByClassName("regBan");
//	
//	 if(val===1){
//         for(var i = 0 ; i < regBan.length; i++){
//             regBan[i].disabled="disabled";
//          }
//      }
//
//}

//	ord.value=val;
	//console.log(val);

//regRpToThis.addEventListener("click",regReply);
target.addEventListener("click",cancelCk);
//submit.addEventListener("click",replyChk);