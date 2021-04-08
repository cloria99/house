 function $(id){return document.getElementById(id);}
			function checkReg(){	
				var username=$("myname").value;
				var pwd=$("mypwd1").value;
				var checkright=true;			
				if(username=="" || pwd=="")  //两者中有一个为空
				{
					alert("请确认用户名和密码输入是否正确！！");
					checkright=false;
				}
				else    //不为空，再判断用户名和密码的长度合法性
				{ 
					if(username.length<6)
					{
						alert("用户名长度太短，至少6个字符！！");
						checkright=false;
					}
					if(pwd.length<6)
					{
						alert("密码长度太短，至少6个字符！！");
						checkright=false;
					}	else
         		 {	
         		 	checkright=window.location.href='2.html';} 
         		 	return checkright;
			}
			function clearInfo()
			{
				var flag = confirm("确认要重置数据吗？");
				if(flag==true)
				{
					$("myname").value = "";
					$("mypwd1").value = "";
					$("mypwd2").value = "";
				}
			}
