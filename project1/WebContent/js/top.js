 function func(MyCommand){ 
	
	var flag = 0;
	
	// 削除
	if (MyCommand == 'Delete'){
		 var chk;
		 for (var i=0; i<document.MyForm.deletechks.length; i++){
			chk = document.MyForm.deletechks[i].checked;
			 
			if (chk){
				break;
			}
		}
		
		if(chk){
			document.getElementById("error_chk").style.display = "none";
		}else{
			document.getElementById("errormsg_chk").innerHTML = "未選択です。";
			document.getElementById("error_chk").style.display = "block";
			flag = 1;
		}
	}

	// 設定終了
	if(flag){
		return false; // 送信を中止
	}else{
		document.MyForm.MySubmit.value=MyCommand;
		document.MyForm.submit(); 
		return true;
	}
 } 