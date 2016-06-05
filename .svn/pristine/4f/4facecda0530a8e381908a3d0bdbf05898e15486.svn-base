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
Ext.define('BXFW.controller.InsuranceTypeListCrl', {
	extend : 'Ext.app.Controller',
	requires : [ 'BXFW.view.system.InsuranceTypeListView' ],
	refs : [ {
		ref : 'insurancetypelistview',
		selector : 'insurancetypelistview'
	} ],
	stores : [ 'InsuranceCompanyListStore' ],
	init : function() {
		this.control({
			'insurancetypelistview' : {
				render : this.initPanel
			}
		});
	},
	initPanel : function(obj, eOpts) {
		// console.log(obj.itemId);
	}
});