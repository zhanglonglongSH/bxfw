var demo = Ext.onReady(function() {
	var userLoginPanel = Ext.create('Ext.panel.Panel', {
		border : false,
		layout : "fit",
		bodyStyle : {
			background : 'url(../styles/images/login/login.jpg)no-repeat #1A94E6',
			backgroundSize : '100%',
			padding : '80 0 0 0' },
		items : [ {
			xtype : 'form',
			id : 'loginForm',
			defaults : {
				width : 260,
				margin : '20 0 0 70' },
			defaultType : 'textfield',
			labelWidth : 40,
			items : [ {
				fieldLabel : '用户名',
				cls : 'user',
				name : 'username',
				labelAlign : 'right',
				labelWidth : 65,
				maxLength : 30,
				emptyText : '请在这里填写用户名',
				maxLengthText : '账号的最大长度为30个字符',
				blankText : "用户名不能为空，请填写！",// 错误提示信息，默认为This
				// field is
				// required!
				allowBlank : false }, {
				fieldLabel : '密   码',
				cls : 'key',
				name : 'userpassword',
				inputType : "password",
				labelWidth : 65,
				labelAlign : 'right',
				emptyText : '请在这里填写密码',
				maxLengthText : '密码长度不能超过20',
				maxLength : 20,
				blankText : "密码不能为空，请填写！",// 错误提示信息，默认为This
				// field is
				// required!
				allowBlank : false }, {
				id : "verifyCode",
				fieldLabel : '验证码',
				cls : 'verifyCode',
				name : 'verifyCode',
				labelAlign : 'right',
				labelWidth : 65,
				width : 200,
				maxLength : 30,
				emptyText : '请在这里输入验证码',
				blankText : "验证码不能为空，请填写！",// 错误提示信息，默认为Thi
				// field is
				// required!
				allowBlank : true } ] } ] });
	Ext.create('Ext.window.Window', {
		title : '北京中联寿险运营系统',
		width : 440,
		height : 300,
		layout : 'fit',
		plain : true,
		modal : true,
		maximizable : false,
		draggable : false,
		closable : false,
		resizable : false,
		items : userLoginPanel,
		renderTo : Ext.getBody(),
		// 重置 和 登录 按钮.
		buttons : [ {
			text : '重置',
			iconCls : 'Wrench',
			handler : function() {
				userLoginPanel.getComponent('loginForm').getForm().reset();
			} }, {
			text : '登录',
			iconCls : 'User',
			handler : function() {
				var bb = Ext.widget('base64util');
				var form = userLoginPanel.getComponent('loginForm').getValues();
				if (userLoginPanel.getComponent('loginForm').getForm().isValid()) {
					form = {
						username : form.username,
						password : form.userpassword,
						verifyCode : form.verifyCode };
					Ext.Ajax.request({
						method : 'POST',
						url : '../system/login/' + bb.base64(encodeURIComponent(JSON.stringify(form))),
						success : function(response, options) {
							// Ext.DomHelper.append(Ext.query(".verifyCode"),
							// "<h1>ss</h1>");
							var retCode = response.responseText.code;
							if (retCode == 1009) {
								Ext.MessageBox.alert('提示', '用户名或密码错误！ ');
							} else if (retCode == 1011) {
								Ext.MessageBox.alert('提示', '验证码输入错误！ ');
							} else if (retCode == 1010) {
								// 获取验证码
								Ext.Ajax.request({
									method : 'GET',
									url : '../system/getRestPasValidateCode/' + bb.base64(encodeURIComponent(JSON.stringify(form))),
									success : function(response, options) {
										// Ext.select(".verifyCode").;
										Ext.DomHelper.append(Ext.query(".verifyCode"), "<h1>ss</h1>");
										$('.simu_login_validateCode img').attr("src", "data:image/png;base64," + result.data);
									},
									failure : function(response, options) {
										Ext.MessageBox.alert('提示', '获取验证码失败失败 ');
									} });
							}
						},
						failure : function(response, options) {
							Ext.MessageBox.alert('提示', '登录失败！ ');
						} });
				} else {
					Ext.MessageBox.alert('提示', '信息填写有误 ');
				}
				// window.location.href =
				// '../system/login/0';
			} } ],
		ajaxMethod : function(url, ObjectParams) {

		}, }).show();
});