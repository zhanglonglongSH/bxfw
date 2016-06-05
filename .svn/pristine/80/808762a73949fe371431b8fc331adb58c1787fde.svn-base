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
Ext.define('BXFW.controller.InsuranceCompanyListCrl', {
	extend : 'Ext.app.Controller',
	requires : [ 
	'BXFW.view.system.InsuranceCompanyListView',
	'BXFW.view.system.InsuranceCompanyEditView' ],
	refs : [ {
		ref : 'insurancecompanylistview',
		selector : 'insurancecompanylistview'
	}, {
		ref : 'insurancecompanyeditview',
		selector : 'insurancecompanyeditview'
	} ],
	stores : [ 'InsuranceCompanyListStore' ],
	init : function() {
		this.control({
			'insurancecompanylistview' : {
				render : this.initPanel
			},
			'insurancecompanylistview button[action=addComapny]' : {
				click : this.addCompany
			},
			'insurancecompanyeditview button[action=saveCompanyInfo]' : {
				click : this.saveCompanyInfo
			},
			'insurancecompanyeditview button[action=saveAddCompanyInfo]' : {
				click : this.saveAddCompanyInfo
			},
			'insurancecompanyeditview button[action=cancelSaveCompany]' : {
				click : this.cancelSaveCompany
			}
		});
	},
	initPanel : function(obj, eOpts) {
		// console.log(obj.itemId);
	},
	addCompany : function() {
		var self = this;
		var addCompany = Ext.create('BXFW.view.system.CompanyEditView');
		addCompany.show();
	},
	saveCompanyInfo : function(btn) {
		console.log("保存数据");
		var win = btn.up("window");
		win.close();
		// console.log("取消数据");
	},
	saveAddCompanyInfo : function(btn) {
		console.log("保存数据");
		var win = btn.up("window");
		win.close();
		// console.log("取消数据");
	},
	cancelSaveCompany : function(btn) {
		var win = btn.up("window");
		win.close();
		// console.log("取消数据");
	}
});