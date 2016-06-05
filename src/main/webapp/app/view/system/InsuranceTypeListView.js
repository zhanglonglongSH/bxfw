/**
 * 
 * 包名路径:app/view
 * 
 * 文件说明:险种类别列表
 * 
 * 作者姓名:yaojj
 * 
 * 创建日期:2016年4月10日 下午10:04:08
 * 
 * 修改日期:
 * 
 */
Ext.define('BXFW.view.system.InsuranceTypeListView', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.insurancetypelistview',
	requires : [ 'Ext.data.*', 'Ext.grid.*', 'Ext.util.*',
			'Ext.toolbar.Paging', 'Ext.ux.ProgressBarPager' ],
	xtype : 'progress-bar-pager',
	stripeRows : true,
	height : 533,
	bodyPadding : 5,
	initComponent : function() {
		this.store = 'PositionListStore';
		this.columns = [ {
			text : '公司名称',
			sortable : true,
			dataIndex : 'id'
		}, {
			text : '部门',
			sortable : true,
			dataIndex : 'name'
		}, {
			text : '职位',
			sortable : true,
			dataIndex : 'name'
		}, {
			text : '备注',
			sortable : true,
			dataIndex : 'note'
		}, {
			text : '操作',
			dataIndex : 'id',
			renderer : function() {
				return '<a href="#"">权限设置</a>';
			}
		} ];
		this.tbar = [ {
			xtype : 'combobox',
			store : new Ext.data.ArrayStore({
				fields : [ 'id', 'name' ],
				data : [ [ 1, '团员' ], [ 2, '党员' ], [ 3, '其他' ] ]
			}),
			width : 100,
			queryMode : 'local',
			displayField : 'name',
			emptyText : '省公司',
			margin : '0 6 0 0',
			valueField : 'abbr'
		}, {
			xtype : 'combobox',
			store : new Ext.data.ArrayStore({
				fields : [ 'id', 'name' ],
				data : [ [ 1, '团员' ], [ 2, '党员' ], [ 3, '其他' ] ]
			}),
			width : 100,
			queryMode : 'local',
			displayField : 'name',
			emptyText : '市公司',
			margin : '0 6 0 0',
			valueField : 'abbr'
		}, {
			xtype : 'combobox',
			store : new Ext.data.ArrayStore({
				fields : [ 'id', 'name' ],
				data : [ [ 1, '团员' ], [ 2, '党员' ], [ 3, '其他' ] ]
			}),
			width : 100,
			queryMode : 'local',
			displayField : 'name',
			margin : '0 6 0 0',
			emptyText : '营业部',
			valueField : 'abbr'
		}, {
			text : '查询',
			xtype : 'button',
			action : "searchInfo"
		} ];
		this.callParent(arguments);
	}
});