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
Ext.define('BXFW.view.system.CompanyEditView', {
	extend : 'Ext.window.Window',
	alias : 'widget.companyeditview',
	title : "新增",
	width : 400,
	height : 260,
	modal : true,
	layout : "fit",
	initComponent : function() {
		this.items = [ {
			itemId : 'save-company',
			xtype : 'form',
			bodyPadding : 5,
			defaults : {
				anchor : '100%',
				labelWidth : 100
			},
			items : [ {
				xtype : 'fieldset',
				title : '添加机构',
				items : [ {
					xtype : 'textfield',
					labelWidth : 80,
					width : 300,
					hidden : true,
					id : 'orgIds',
					name : 'orgId'
				}, {
					id : 'orgCodeId1',
					name : 'orgCode',
					fieldLabel : '选择省份',
					xtype : 'combobox',
					labelWidth : 80,
					width : 200,
					queryMode : 'local',
					displayField : 'constanName',
					valueField : 'constantCode',
					emptyText : '请选择'
				}, {
					id : 'orgCodeId2',
					name : 'orgCode',
					fieldLabel : '选    择   市 ',
					xtype : 'combobox',
					labelWidth : 80,
					width : 200,
					hidden : true,
					queryMode : 'local',
					displayField : 'constanName',
					valueField : 'constantCode',
					emptyText : '请选择'
				}, {
					id : 'orgCodeId3',
					name : 'orgCode',
					fieldLabel : '营    业    部 ',
					xtype : 'combobox',
					labelWidth : 80,
					width : 200,
					hidden : true,
					queryMode : 'local',
					displayField : 'constanName',
					valueField : 'constantCode',
					emptyText : '请选择'
				}, {
					xtype : 'textfield',
					labelWidth : 80,
					width : 300,
					id : 'orgNameId',
					name : 'orgName',
					fieldLabel : '机构名称'
				}, {
					xtype : 'textarea',
					id : 'noteId',
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