/**
 * 用户信息管理
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
Ext.define('BXFW.controller.DepartmentListCrl',
		{
		extend : 'Ext.app.Controller',
		requires : [ 
		'BXFW.view.system.DepartmentListView',
		'BXFW.view.system.DepartmentEditView',
		'BXFW.util.Base64Util' ],
		refs : [ {
			ref : 'departmentlistview',
			selector : 'departmentlistview'
		}, {
			ref : 'departmenteditview',
			selector : 'departmenteditview'
		} ],
		stores : [ 'DepartmentListStore' ],
		init : function() {
			this.control({
				'departmentlistview' : {
					render : this.initPanel,
					itemdblclick : this.itemdblclickEvent
				},
				'departmentlistview button[action=searchInfo]' : {
					click : this.searchInfo
				},
				'departmentlistview button[action=addDepartment]' : {
					click : this.addDepartment
				},
				'departmenteditview button[action=saveDepartmentInfo]' : {
					click : this.saveDepartmentInfo
				},
				'departmenteditview button[action=saveAddCompanyInfo]' : {
					click : this.saveAddCompanyInfo
				},
				'departmenteditview button[action=cancelSaveDepartment]' : {
					click : this.cancelSaveDepartment
				},
				'departmentlistview actioncolumn' : {
					itemclick : this.itemclick
				}
			});
		},
		/**
		 * 页面初始化
		 * @param {} obj
		 * @param {} eOpts
		 */
		initPanel : function(obj, eOpts) {
			var _self = this;
			_self.gridLoad();
		},
		/**
		 * 初始化页面基础数据
		 * @return {}
		 */
		infoObj : function() {
			var _base64util = Ext.create('BXFW.util.Base64Util');
			var _departmentlistview;
			Ext.getCmp('mainTab').items.each(function(item) {
				if (item.items.length > 0 && item.items.get(0).xtype == 'departmentlistview') {
					_departmentlistview = item.items.get(0);
				}
			});
			return {
				base64util : _base64util,
				departmentlistview:_departmentlistview,
				orgType:_departmentlistview.orgtype
			};
		},
		/**
		 * 弹出添加编辑窗口
		 * @param {} btn
		 */
		addDepartment : function(formData) {
			var _self = this;
			var addDeptInfo = Ext.create('BXFW.view.system.DepartmentEditView');
			addDeptInfo.show();
			_self.infoOrgData();
			if (formData != null && formData.deptName != '') {
				Ext.getCmp('orgCodeId').setValue(formData.orgCode);
				Ext.getCmp('deptCodeId').setValue(formData.deptCode);
				Ext.getCmp('deptNameId').setValue(formData.deptName);
				Ext.getCmp('noteId').setValue(formData.note);
			}
		},
		/**
		 * 查询部门信息
		 * @param {} btn
		 */
		searchInfo:function(btn){
			var _self = this;
			var _parm={};
			btn.ownerCt.items.each(function(item) {
				if (item.name == 'searchOrgName') {
					_parm.searchOrgName=item.getValue();
				}
			});
			_self.gridLoad(_parm);
		},
		/**
		 * 保存
		 * @param {} btn
		 */
		saveDepartmentInfo : function(btn) {
			this.saveDeptInfo(btn, true);
		},
		/**
		 * 保存并继续添加
		 * @param {} btn
		 */
		saveAddCompanyInfo : function(btn) {
			this.saveDeptInfo(btn, false);
		},
		/**
		 * 取消保存
		 * @param {} btn
		 */
		cancelSaveDepartment : function(btn) {
			var win = btn.up("window");
			win.close();
		},
		/**
		 * 双击表格事件处理
		 * @param {} grid
		 * @param {} record
		 */
		itemdblclickEvent : function(grid, record) {
			var _self = this;
			var formData = record.data;
			_self.addDepartment(formData);
		},
		/**
		 * 操作栏按钮事件处理
		 * @param {} column
		 * @param {} grid
		 * @param {} rowIndex
		 * @param {} colIndex
		 * @param {} node
		 * @param {} e
		 * @param {} record
		 */
		itemclick : function(column, grid, rowIndex, colIndex, node, e, record) {
			var _self = this;
			if (node.action == 'gridedit') {
				var _formData = grid.getStore().data.items[rowIndex].data;
				_self.addDepartment(_formData);
			}
			if (node.action == 'griddelete') {
				var _formData = {
					deptCode : grid.getStore().data.items[rowIndex].data.deptCode
				};
				_self.delDeptInfo(_formData);
			}
		},
		/**
		 * 保存部门
		 * @param {} btn
		 * @param {} winstate
		 * @return {Boolean}
		 */
		saveDeptInfo : function(btn, winstate) {
			var _self = this;
			var _win = btn.up("window");
			var _form = _win.getComponent('save-dept');
			var _formData = _form.getValues();
			if (_formData.orgCode == '') {
				Ext.MessageBox.alert('提示', '请选择公司');
				return false;
			}
			if (_formData.deptName == '') {
				Ext.MessageBox.alert('提示', '部门名称不能为空');
				return false;
			}
			var _base64util = _self.infoObj().base64util;
			_formData.orgType=_self.infoObj().orgType;
			_formData.deptCode=_formData.deptCode==''?'0':_formData.deptCode;
			Ext.Ajax.request({
				url : _projectName+'/system/saveDeptInfo/'
						+ _base64util.base64(encodeURIComponent(JSON
								.stringify(_formData))),
				method : 'POST',
				success : function(response, options) {
					_self.gridLoad();
					if (!winstate) {
						Ext.getCmp('orgCodeId').setValue('');
						Ext.getCmp('deptNameId').setValue('');
						Ext.getCmp('noteId').setValue('');
					} else {
						_win.close();
					}
				},
				failure : function(response, options) {
					Ext.MessageBox.alert('提示', '部门信息保存失败 ');
				}
			});
		},
		/**
		 * 删除部门信息 
		 * @param {} formData
		 */
		delDeptInfo : function(formData) {
			var _self = this;
			var _base64util = _self.infoObj().base64util;
			Ext.Ajax.request({
				url : _projectName+'/system/delDeptInfo/'
						+ _base64util.base64(encodeURIComponent(JSON
								.stringify(formData))),
				method : 'DELETE',
				success : function(response, options) {
					_self.gridLoad();
				},
				failure : function(response, options) {
					Ext.MessageBox.alert('提示', '删除部门信息失败 ');
				}
			});
		},
	    /**
	     * 初始化公司选项卡数据
	     */
		infoOrgData:function(){
			var _self = this;
			var _base64util = _self.infoObj().base64util;		
			var _parmData = _base64util.base64(encodeURIComponent(JSON.stringify({
				constanType:_self.infoObj().orgType,
				companyCode:1,
				dataType:2
			})));
			var storeData = new Ext.data.Store({
		        autoLoad: true,
		        model : "BXFW.model.OrgModel",
		        proxy: {
		        	type : 'rest',
		        	method: 'GET',
			        url:_projectName+'/system/getSysConstant/'+_parmData,
			        reader : {
						type : 'json',
						root : 'data'
					}
		        }
		    });
			Ext.getCmp('orgCodeId').store=storeData;
		},
		/**
		 * 加载列表数据
		 */
		gridLoad : function(parms) {
			var _self = this;
			var _base64util = _self.infoObj().base64util;
			var _parmData = _base64util.base64(encodeURIComponent(JSON.stringify({
				orgType:_self.infoObj().orgType,
				orgName : parms==null?'':parms.searchOrgName
			})));
			var _departmentlistStore = this.infoObj().departmentlistview.store;
			_departmentlistStore.getProxy().url = _projectName+"/system/getDeptInfoList/"
					+ _parmData;
			_departmentlistStore.load();
		}
	});