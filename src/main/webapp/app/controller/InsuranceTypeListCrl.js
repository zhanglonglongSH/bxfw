/**
 * 险种分类信息管理
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
Ext
		.define(
				'BXFW.controller.InsuranceTypeListCrl',
				{
					extend : 'Ext.app.Controller',
					requires : [ 'BXFW.view.system.InsuranceTypeListView',
							'BXFW.view.system.InsuranceTypeEditView',
							'BXFW.util.Base64Util' ],
					refs : [ {
						ref : 'insuranceTypeListView',
						selector : 'insuranceTypeListView'
					}, {
						ref : 'insuranceTypeEditView',
						selector : 'insuranceTypeEditView'
					} ],
					stores : [ 'InsuranceTypeListStore' ],
					init : function() {
						this
								.control({
									'insuranceTypeListView' : {
										render : this.initPanel,
										itemdblclick : this.itemdblclickEvent
									},
									'insuranceTypeListView button[action=searchInfo]' : {
										click : this.searchInfo
									},
									'insuranceTypeListView button[action=addInsuranceType]' : {
										click : this.addInsuranceType
									},
									'insuranceTypeEditView button[action=saveInsuranceType]' : {
										click : this.saveInsuranceType
									},
									'insuranceTypeEditView button[action=saveAddInsuranceType]' : {
										click : this.saveAddInsuranceType
									},
									'insuranceTypeEditView button[action=cancelSaveInsuranceType]' : {
										click : this.cancelSaveInsuranceType
									},
									'insuranceTypeListView actioncolumn' : {
										itemclick : this.itemclick
									}
								});
					},
					/**
					 * 处理事件方法
					 */
					initPanel : function(obj, eOpts) {
						var _self = this;
						_self.gridLoad();
					},
					/**
					 * 初始化页面基础数据
					 * 
					 * @return {}
					 */
					infoObj : function() {
						var _base64util = Ext.widget('base64util');
						var _insuranceTypeListView;
						Ext.getCmp('mainTab').items
								.each(function(item) {
									if (item.items.length > 0
											&& item.items.get(0).xtype == 'insuranceTypeListView') {
										_insuranceTypeListView = item.items
												.get(0);
									}
								});

						return {
							base64util : _base64util,
							insuranceTypeListView : _insuranceTypeListView
						};
					},
					/**
					 * 添加险种
					 * 
					 * @param {}
					 *            formData
					 */
					addInsuranceType : function(btn) {
						var _self = this;
						var _addDeptInfo = Ext.widget('insuranceTypeEditView');
						_addDeptInfo.show();
						_self.infoOrgData();
					},
					/**
					 * 修改险种信息
					 * 
					 * @param {}
					 *            formData
					 */
					updateInsuranceType : function(formData) {
						var _self = this;
						var _addDeptInfo = Ext.widget('insuranceTypeEditView');
						if (formData != null && formData.typeName != '') {
							_addDeptInfo.height = 220;
							Ext.getCmp('typeCode').setValue(formData.typeCode);
							Ext.getCmp('typeName').setValue(formData.typeName);
							Ext.getCmp('description').setValue(
									formData.description);
						}
						_addDeptInfo.show();
					},
					/**
					 * 自定义查询险种信息
					 */
					searchInfo : function(btn) {
						var _self = this;
						var _parm = {};
						btn.ownerCt.items.each(function(item) {
							if (item.name == 'searchTypeName') {
								_parm.searchOrgName = item.getValue();
							}
						});
						_self.gridLoad(_parm);
					},
					/**
					 * 保存险种
					 * 
					 * @param {}
					 *            btn
					 */
					saveInsuranceType : function(btn) {
						this.ajaxInsuranceTypeInfo(btn, true);
					},
					/**
					 * 保存并继续添加
					 * 
					 * @param {}
					 *            btn
					 */
					saveAddInsuranceType : function(btn) {
						this.ajaxInsuranceTypeInfo(btn, false);
					},
					/**
					 * 取消保存
					 * 
					 * @param {}
					 *            btn
					 */
					cancelSaveInsuranceType : function(btn) {
						var _win = btn.up("window");
						_win.close();
					},
					/**
					 * 双击grid事件
					 * 
					 * @param {}
					 *            grid
					 * @param {}
					 *            record
					 */
					itemdblclickEvent : function(grid, record) {
						var _self = this;
						var formData = record.data;
						_self.updateInsuranceType(formData);
					},
					/**
					 * 操作栏中按钮事件
					 * 
					 * @param {}
					 *            column
					 * @param {}
					 *            grid
					 * @param {}
					 *            rowIndex
					 * @param {}
					 *            colIndex
					 * @param {}
					 *            node
					 * @param {}
					 *            e
					 * @param {}
					 *            record
					 */
					itemclick : function(column, grid, rowIndex, colIndex,
							node, e, record) {
						var _self = this;
						if (node.action == 'gridedit') {
							var _formData = grid.getStore().data.items[rowIndex].data;
							_self.updateInsuranceType(_formData);
						}
						if (node.action == 'griddelete') {
							var _formData = {
								typeCode : grid.getStore().data.items[rowIndex].data.typeCode
							};
							_self.delInsuranceType(_formData);
						}
					},
					/**
					 * 删除险种
					 */
					delInsuranceType : function(formData) {
						var _self = this;
						var _base64util = _self.infoObj().base64util;
						Ext.Ajax.request({
							url : _projectName
									+ '/productManage/delInsuranceType/'
									+ _base64util
											.base64(encodeURIComponent(JSON
													.stringify(formData))),
							method : 'DELETE',
							success : function(response, options) {
								_self.gridLoad();
							},
							failure : function(response, options) {
								Ext.MessageBox.alert('提示', '删除险种信息失败 ');
							}
						});
					},
					/**
					 * 保存险种
					 */
					ajaxInsuranceTypeInfo : function(btn, winstate) {
						var _self = this;
						var _win = btn.up("window");
						var _form = _win.getComponent('save-insuranceType');
						var _formData = _form.getValues();
						var _typeCode = _self.infoObj().typeCode;

						if (_formData.typeName == '') {
							Ext.MessageBox.alert('提示', '险种名称不能为空');
							return;
						}
						_formData.typeCode = Ext.getCmp(
								'typeCode' + (_typeCode == 4 ? 3 : _typeCode))
								.getValue();
						_formData.typeCode = _typeCode;
						var _base64util = _self.infoObj().base64util;
						Ext.Ajax.request({
							url : _projectName
									+ '/productManage/saveInsuranceType/'
									+ _base64util
											.base64(encodeURIComponent(JSON
													.stringify(_formData))),
							method : 'POST',
							success : function(response, options) {
								_self.gridLoad();
								if (!winstate) {
									Ext.getCmp('typeCode').setValue('');
									Ext.getCmp('typeName').setValue('');
									Ext.getCmp('description').setValue('');
								} else {
									_win.close();
								}
							},
							failure : function(response, options) {
								Ext.MessageBox.alert('提示', '险种分类信息保存失败 ');
							}
						});
					},
					/**
					 * 公用查询，查询险种基本信息
					 */
					gridLoad : function(parms) {
						var _self = this;
						var _base64util = _self.infoObj().base64util;
						var _parmData = _base64util
								.base64(encodeURIComponent(JSON.stringify({

								})));
						var _insuranceTypeListViewStore = this.infoObj().insuranceTypeListView.store;
						_insuranceTypeListViewStore.getProxy().url = _projectName
								+ "/productManage/getInsuranceTypeList/"
								+ _parmData;
						_insuranceTypeListViewStore.load();
					}
				});