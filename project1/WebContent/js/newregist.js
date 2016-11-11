function mySubmit()
{	
	var flag = 0;
	var errmsg_Required = "必須です。";
	
	if(document.form1.userid.value == ""){ 	// ユーザーIDの入力をチェック
		document.getElementById("errormsg_userid").innerHTML = errmsg_Required;
        document.getElementById("error_userid").style.display = "block";
		flag = 1;
	}
	else{
		document.getElementById("error_userid").style.display = "none";
	}
	
	if(document.form1.pass.value == ""){ 	// パスワードの入力をチェック
		document.getElementById("errormsg_pass").innerHTML = errmsg_Required;
        document.getElementById("error_pass").style.display = "block";
		flag = 1;
	}
	else{
		document.getElementById("error_pass").style.display = "none";
	}

	if(document.form1.username.value == ""){ 	// ユーザー名の入力をチェック
		document.getElementById("errormsg_username").innerHTML = errmsg_Required;
        document.getElementById("error_username").style.display = "block";
		flag = 1;
	}
	else{
		document.getElementById("error_username").style.display = "none";
	}
	
	if(document.form1.pass.value != document.form1.passchk.value){
		document.getElementById("errormsg_chk").innerHTML = "パスワードが不一致です。";
		document.getElementById("error_chk").style.display = "block";
		flag = 1;
	}
	else{
		document.getElementById("error_chk").style.display = "none";
	}
	
	// 設定終了
	if(flag){
		return false; // 送信を中止
	}
	else{
		  // サブミットするフォームを取得
		  var f = document.forms['form1'];
		  f.submit(); // submit する
		  return true;
	}
}