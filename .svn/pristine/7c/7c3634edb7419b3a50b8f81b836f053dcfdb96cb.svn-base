Ext.onReady(function() {  
    var userLoginPanel = Ext.create('Ext.panel.Panel', {   
        border : false, 
        layout : "fit",
        bodyStyle : {
			background : 'url(../styles/images/login/login.jpg)no-repeat #1A94E6',
			backgroundSize : '100%',
			padding : '80 0 0 0'
		},
        items:[
        { 
            xtype : 'form',  
            id : 'loginForm',  
            defaults : {  
                width : 260,  
                margin: '20 0 0 70'  
            },  
            defaultType : 'textfield',  
            labelWidth : 40,  
            items: [{  
                fieldLabel: '用户名',  
                cls : 'user',  
                name: 'username',  
                labelAlign:'right',  
                labelWidth:65,  
                maxLength : 30,  
                emptyText:'请在这里填写用户名',  
                maxLengthText : '账号的最大长度为30个字符',  
                blankText:"用户名不能为空，请填写！",//错误提示信息，默认为This field is required!  
                allowBlank: false  
            },{  
                fieldLabel: '密   码',  
                cls : 'key',  
                name: 'password',  
                inputType:"password",  
                labelWidth:65,  
                labelAlign:'right',  
                emptyText:'请在这里填写密码',  
                maxLengthText :'密码长度不能超过20',  
                maxLength : 20,  
                blankText:"密码不能为空，请填写！",//错误提示信息，默认为This field is required!  
                allowBlank: false  
            }]  
           }
        ]
    });  
      
    Ext.create('Ext.window.Window', {  
        title : '北京中联寿险运营系统',  
        width : 440,  
        height : 300,  
        layout: 'fit',  
        plain : true,  
        modal : true,  
        maximizable : false,  
        draggable : false,  
        closable : false,  
        resizable : false,  
        items: userLoginPanel,  
        // 重置 和 登录 按钮.  
        buttons: [{  
            text: '重置',  
            iconCls : 'Wrench',  
            handler: function() {  
            }  
        }, {  
            text: '登录',  
            iconCls : 'User',  
            handler: function() { 
            	window.location.href='../system/login/0';
            }  
        }]  
    }).show();  
});  