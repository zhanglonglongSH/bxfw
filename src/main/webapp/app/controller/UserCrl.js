/**
 * 用户信息管理
 * 
 * 包名路径:app/view
 * 
 * 文件说明:左侧菜单事件控制
 * 
 * 作者姓名:yaojj
 * 
 * 创建日期:2016年4月10日 下午10:04:08
 * 
 * 修改日期:
 * 
 */
Ext.define('BXFW.controller.UserCrl', {
	extend : 'Ext.app.Controller',
	requires : [ 
	'BXFW.view.system.UserListView', 
	'BXFW.view.system.EditUserView',
	'BXFW.view.commonview.UploadFilesView', 
	'BXFW.view.system.UserAuthorizeListView' ],
	refs : [ {
		ref : 'userlistview',
		selector : 'userlistview'
	}, {
		ref : 'edituserview',
		selector : 'edituserview'
	}, {
		ref : 'uploadfilesview',
		selector : 'uploadfilesview'
	}, {
		ref : 'userauthorizelistview',
		selector : 'userauthorizelistview'
	} ],
	stores : [ 'UserStore' ],
	init : function() {
		this.control({
			'userlistview' : {
				render : this.initPanel
			},
			'userlistview button[action=addUser]' : {
				click : this.addUser
			},
			'edituserview button[action=saveUserInfo]' : {
				click : this.saveUserInfo
			},
			'edituserview button[action=cancelSaveUser]' : {
				click : this.cancelSaveUser
			},
			'edituserview button[action=uploadFiles]' : {
				click : this.uploadFiles
			},
			'uploadfilesview button[action=uploadFilesSave]' : {
				click : this.uploadFilesSave
			},
			'uploadfilesview button[action=cancelUploadFiles]' : {
				click : this.cancelUploadFiles
			}
		});
	},
	initPanel : function(obj, eOpts) {
		// console.log(obj.itemId);
	},
	addUser : function() {
		var self = this;
		var addUser = Ext.create('BXFW.view.system.EditUserView');
		addUser.show();
	},
	saveUserInfo : function(btn) {
		console.log("保存数据");
		var win = btn.up("window");
		win.close();
		// console.log("取消数据");
	},
	cancelSaveUser : function(btn) {
		var win = btn.up("window");
		win.close();
		// console.log("取消数据");
	},
	uploadFiles : function() {
		var self = this;
		var addUser = Ext.create('BXFW.view.commonview.UploadFilesView');
		addUser.show();
	},
	uploadFilesSave : function(btn) {
		var win = btn.up("window");
		var form = win.getComponent('uploadForm');
		if (form.isValid()) {
			form.submit({
				method : 'post',
				url : _projectName+'/system/uploadFile',
				waitMsg : '正在上传请稍后...',
				success : function(fp, o) {

				}
			});
		}
	},
	cancelUploadFiles : function(btn) {
		var win = btn.up("window");
		win.close();
	}
});