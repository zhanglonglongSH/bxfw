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
Ext.define('BXFW.controller.SysMenuCrl', {
	extend : 'Ext.app.Controller',
	refs : [ {
		ref : 'sysmenuview',
		selector : 'sysmenuview'
	} ],
	stores : [ 'SysMenuStore' ],
	init : function() {
		this.control({
			'sysmenuview' : {
				itemclick : this.onItemclick
			}
		});
	},
	onItemclick : function(record, node) {
		this.addTab(node);
	},
	addTab : function(node) {
		var _nodeInfo = node.data;
		var _parentNodeInfo = node.parentNode.data;
		var _parentNodeText = _parentNodeInfo.text == "Root" ? ""
				: _parentNodeInfo.text + "-";
		var _selectTab = Ext.getCmp(_nodeInfo.id);
		var _tabpanel = Ext.getCmp('mainTab');
		if (_tabpanel.items.getCount() > 1) {
			_tabpanel.remove(_tabpanel.getActiveTab());
		}
		if (_selectTab == null && _nodeInfo.leaf == true) {
			var _createTab = _tabpanel.add({
				id : _nodeInfo.id,
				title : _parentNodeText + _nodeInfo.text,
				closable : true,
				items : [ {
					itemId : 'user-pl-' + _nodeInfo.id,
					orgtype:_parentNodeInfo.companytype,
					xtype : _nodeInfo.viewname
				} ]
			});
			_tabpanel.setActiveTab(_createTab);
		} else {
			_tabpanel.setActiveTab(_selectTab);
		}
	}
});