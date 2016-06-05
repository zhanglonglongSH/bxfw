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
Ext.define('BXFW.view.system.InsuranceCompanyEditView', {
	extend : 'Ext.window.Window',
	alias : 'widget.insurancecompanyeditview',
	title : "新增",
	width : 400,
	height : 260,
	modal : true,
	layout : "fit",
	initComponent : function() {
		this.items = [ {
			xtype : 'form',
			bodyPadding : 5,
			defaults : {
				anchor : '100%',
				labelWidth : 100
			},
			items : [ {
				xtype : 'fieldset',
				title : '添加公司',
				items : [ {
					fieldLabel : '选择省份',
					xtype : 'combobox',
					labelWidth : 80,
					store : new Ext.data.ArrayStore({
						fields : [ 'id', 'name' ],
						data : [ [ 1, '山西' ], [ 2, '河南' ] ]
					}),
					width : 200,
					queryMode : 'local',
					displayField : 'name',
					valueField : 'abbr'
				}, {
					xtype : 'textfield',
					labelWidth : 80,
					width : 300,
					name : 'companyName',
					fieldLabel : '公司名称',
					allowBlank : false
				}, {
					xtype : 'textarea',
					name : 'note',
					labelWidth : 80,
					width : 300,
					fieldLabel : '备注信息'
				} ]
			} ]
		} ];
		this.dockedItems = [ {
			xtype : 'toolbar',
			dock : 'bottom',
			ui : 'footer',
			layout : {
				pack : 'center'
			},
			items : [ {
				iconCls : 'icon-save',
				action : 'saveCompanyInfo',
				minWidth : 40,
				text : '保存'
			}, {
				iconCls : 'icon-save',
				action : 'saveAddCompanyInfo',
				minWidth : 80,
				text : '保存继续添加'
			}, {
				iconCls : 'icon-reset',
				action : 'cancelSaveCompany',
				minWidth : 40,
				text : '取消'
			} ]
		} ]
		this.callParent();
	}
});