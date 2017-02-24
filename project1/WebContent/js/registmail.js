function check(){
	var flag = 0;
	
	if(document.form1.mailadress.value == ""){ 	// パスワードの入力をチェック
		document.getElementById("errormsg_mailadress").innerHTML = "必須です。";
        document.getElementById("error_mailadress").style.display = "block";
        flag = 1;
	}else if(!document.form1.mailadress.value.match(/.+@.+\..+/)){
		document.getElementById("errormsg_mailadress").innerHTML = "メールアドレスが正しくありません。";
        document.getElementById("error_mailadress").style.display = "block";
        flag = 1;
	}

	if(flag){
		return false; // 送信を中止

	}
	else{
		return true; // 送信を実行

	}

}