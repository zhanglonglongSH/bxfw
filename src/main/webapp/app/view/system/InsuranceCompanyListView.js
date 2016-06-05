/**
 * 
 * 包名路径:app/view
 * 
 * 文件说明:省公司、市公司、营业部、业务部机构列表
 * 
 * 作者姓名:yaojj
 * 
 * 创建日期:2016年4月10日 下午10:04:08
 * 
 * 修改日期:
 * 
 */
Ext.define('BXFW.view.system.InsuranceCompanyListView', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.insurancecompanylistview',
	requires : [ 'Ext.data.*', 'Ext.grid.*', 'Ext.util.*',
			'Ext.toolbar.Paging', 'Ext.ux.ProgressBarPager' ],
	xtype : 'progress-bar-pager',
	stripeRows : true,
	height : 533,
	bodyPadding : 5,
	initComponent : function() {
		this.store = 'InsuranceCompanyListStore';
		this.columns = [ {
			text : '省份名称',
			sortable : true,
			dataIndex : 'id'
		}, {
			text : '公司代码',
			sortable : true,
			dataIndex : 'id'
		}, {
			text : '公司名称',
			sortable : true,
			dataIndex : 'name'
		}, {
			text : '备注信息',
			sortable : true,
			dataIndex : 'state'
		}, {
			text : '操作',
			width : 200,
			dataIndex : 'id',
			renderer : function() {
				return '';
			}
		} ];
		this.tbar = [ {
			text : '新增',
			xtype : 'button',
			iconCls : 'icon-add',
			action : "addComapny"
		}, '-', {
			xtype : 'combobox',
			store : new Ext.data.ArrayStore({
				fields : [ 'id', 'name' ],
				data : [ [ 1, '团员' ], [ 2, '党员' ], [ 3, '其他' ] ]
			}),
			width : 100,
			queryMode : 'local',
			displayField : 'name',
			valueField : 'abbr'
		}, {
			text : '查询',
			xtype : 'button',
			action : "searchInfo"
		} ];
		this.callParent(arguments);
	}
});