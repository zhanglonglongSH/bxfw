/**
 * 
 * 包名路径:app/view
 * 
 * 文件说明:
 * 
 * 作者姓名:yaojj
 * 
 * 创建日期:2016年4月10日 下午10:04:08
 * 
 * 修改日期:
 * 
 */
Ext.define("BXFW.store.UserStore", {
	extend : "Ext.data.Store",
	model : "BXFW.model.UserModel",
	autoLoad : true,
	proxy : {
		type : 'ajax',
		url : _projectName + '/app/data/UserData.json',
		reader : {
			type : 'json',
			root : 'users'
		}
	}
});