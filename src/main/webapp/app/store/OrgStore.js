/**
 * 
 * 包名路径:app/view
 * 
 * 文件说明: 省、市、县下拉框数据
 * 
 * 作者姓名:yaojj
 * 
 * 创建日期:2016年4月10日 下午10:04:08
 * 
 * 修改日期:
 * 
 */
Ext.define("BXFW.store.OrgStore", {
	extend : "Ext.data.Store",
	model : "BXFW.model.OrgModel",
	autoLoad : true,
	proxy : {
		type : 'ajax',
		url : '',
		reader : {
			type : 'json',
			root : 'data'
		}
	}
});