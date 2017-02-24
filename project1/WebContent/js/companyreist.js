function mySubmit()
{	
	var flag = 0;
	var errmsg_Required = "必須です。";
	
	// 会社名チェック
	if(document.form1.company_name.value == ""){
		document.getElementById("errormsg_company_name").innerHTML = errmsg_Required;
        document.getElementById("error_company_name").style.display = "block";
		flag = 1;
	}
	else{
		document.getElementById("error_company_name").style.display = "none";
	}
	
	// 都道府県チェック
	var indx = document.form1.area.selectedIndex;
	if(document.form1.area.options[indx].value == ""){
		document.getElementById("errormsg_area").innerHTML = errmsg_Required;
        document.getElementById("error_area").style.display = "block";
		flag = 1;
	}
	else{
		document.getElementById("error_area").style.display = "none";
	}
	
	// 業種チェック
	var indx = document.form1.business.selectedIndex;
	if(document.form1.business.options[indx].value == ""){
		document.getElementById("errormsg_business").innerHTML = errmsg_Required;
        document.getElementById("error_business").style.display = "block";
		flag = 1;
	}
	else{
		document.getElementById("error_business").style.display = "none";
	}
	
	var chk;
	
	// 特殊チェック
	 for (var i=0; i<document.form1.characteristics.length; i++){
		chk = document.form1.characteristics[i].checked;
		if (chk){
			break;
		}
	}
	
	if(chk){
		document.getElementById("error_characteristic").style.display = "none";
	}else{
		document.getElementById("errormsg_characteristic").innerHTML = errmsg_Required;
		document.getElementById("error_characteristic").style.display = "block";
		flag = 1;
	}
	
	// 時間帯チェック
	 for (var i=0; i<document.form1.timezones.length; i++){
		chk = document.form1.timezones[i].checked;
		if (chk){
			break;
		}
	}
	
	if(chk){
		document.getElementById("error_timezone").style.display = "none";
	}else{
		document.getElementById("errormsg_timezone").innerHTML = errmsg_Required;
		document.getElementById("error_timezone").style.display = "block";
		flag = 1;
	}
		
	
	// 期間チェック
	 for (var i=0; i<document.form1.periods.length; i++){
		chk = document.form1.periods[i].checked;
		if (chk){
			break;
		}
	}
	
	if(chk){
		document.getElementById("error_period").style.display = "none";
	}else{
		document.getElementById("errormsg_period").innerHTML = errmsg_Required;
		document.getElementById("error_period").style.display = "block";
		flag = 1;
	}
	
	// 雇用形態チェック
	 for (var i=0; i<document.form1.employments.length; i++){
		chk = document.form1.employments[i].checked;
		if (chk){
			break;
		}
	}
	
	if(chk){
		document.getElementById("error_employment").style.display = "none";
	}else{
		document.getElementById("errormsg_employment").innerHTML = errmsg_Required;
		document.getElementById("error_employment").style.display = "block";
		flag = 1;
	}
	
	// 給料チェック
	if(document.form1.salary.value == ""){
		document.getElementById("errormsg_salary").innerHTML = errmsg_Required;
		document.getElementById("error_salary").style.display = "block";
		flag = 1;
	}
	else{
		if(document.form1.salary.value.match(/[^0-9]+/)){
			document.getElementById("errormsg_salary").innerHTML = "数値を入力して下さい。";
			document.getElementById("error_salary").style.display = "block";
			flag = 1;
		}else{
			document.getElementById("error_salary").style.display = "none";
		}
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