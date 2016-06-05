/**
 * 
 * 包名路径:app/store
 * 
 * 文件说明:
 * 
 * 作者姓名:yaojj
 * 
 * 创建日期:2016年5月12日 下午4:17:21
 * 
 * 修改日期:
 * 
 */
Ext.define("BXFW.store.LifeInsuranceMenuStore", {
	extend : "Ext.data.TreeStore",
	model : "BXFW.model.MenuModel",
	proxy : {
		type : 'ajax',
		url : _projectName+'/app/data/LifeInsuranceMenuData.json',
		reader : 'json',
		autoLoad : true
	},
	folderSort : false
});