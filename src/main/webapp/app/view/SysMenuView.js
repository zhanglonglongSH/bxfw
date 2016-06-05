/**
 * 
 * 包名路径:app/view
 * 
 * 文件说明: 系统管理模块
 * 
 * 作者姓名:yaojj
 * 
 * 创建日期:2016年4月10日 下午10:04:08
 * 
 * 修改日期:
 * 
 */
Ext.define('BXFW.view.SysMenuView', {
	extend : 'Ext.tree.Panel',
	alias : 'widget.sysmenuview',
	initComponent : function() {
		this.useArrows = true;
		this.rootVisible = false;
		this.width = 185;
		this.store = 'SysMenuStore';
		this.callParent();
	}
});