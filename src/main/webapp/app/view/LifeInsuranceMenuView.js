/**
 * 
 * 包名路径:app/view
 * 
 * 文件说明: 人身险模块
 * 
 * 作者姓名:yaojj
 * 
 * 创建日期:2016年5月12日 下午4:16:07
 * 
 * 修改日期:
 * 
 */
Ext.define('BXFW.view.LifeInsuranceMenuView', {
	extend : 'Ext.tree.Panel',
	alias : 'widget.lifeinsurancemenuview',
	initComponent : function() {
		this.useArrows = true;
		this.rootVisible = false;
		this.store = 'LifeInsuranceMenuStore';
		this.callParent();
	}
});