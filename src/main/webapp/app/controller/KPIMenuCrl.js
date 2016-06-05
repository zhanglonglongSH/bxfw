/**
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
Ext.define('BXFW.controller.KPIMenuCrl', {
	extend : 'Ext.app.Controller',
	requires : [ 'BXFW.view.system.UserListView' ],
	refs : [ {
		ref : 'kpimenuview',
		selector : 'kpimenuview'
	} ],
	stores : [ 'KPIMenuStore' ],
	init : function() {
		this.control({
			'kpimenuview' : {
				itemclick : this.onItemclick
			}
		});
	},
	onItemclick : function(record, node) {
		this.addTab(node.data);
	},
	addTab : function(tabData) {
		var _selectTab = Ext.getCmp(tabData.id);
		var _tabpanel = Ext.getCmp('mainTab');
	}
});