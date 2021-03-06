/** 
* @公司名称: xxxxxxxxxxxx
* @版权信息: 版权归属15202125130@163.com
* @包          名: com.lifeinsurancesystem.util
* @描          述: 文件描述 Excel解析工具类
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年5月10日 上午11:02:48 
* @修改日期: 2016年5月10日 上午11:02:48
* @版权序号: V0.0.1
*/
package com.lifeinsurancesystem.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
* @类          名: ExcelUtil 
* @描          述: TODO 解析保险公司提供的费率excel文件
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年5月10日 上午11:03:32 
* @修改日期: 2016年5月10日 上午11:03:32
 */
public class ExcelUtil {
	    private Logger log = LoggerFactory.getLogger(ExcelUtil.class);
		public ExcelUtil() {
			// TODO Auto-generated constructor stub
		}
		/** 总行数 */
		private int totalRows = 0;
		/** 总列数 */

		private int totalCells = 0;
		/** 错误信息 */
		private String errorInfo;
		public int getTotalRows()
		{
			return totalRows;
		}
		public int getTotalCells()
		{
			return totalCells;
		}
		public String getErrorInfo()
		{
			return errorInfo;
		}

		//验证excel文件
		public boolean validateExcel(String filePath)
		{
			/** 检查文件名是否为空或者是否是Excel格式的文件 */

			if (filePath == null || !(WDWUtil.isExcel2003(filePath) || WDWUtil.isExcel2007(filePath)))
			{
				errorInfo = "文件名不是excel格式";
				return false;
			}
			/** 检查文件是否存在 */
			File file = new File(filePath);
			if (file == null || !file.exists())
			{
				errorInfo = "文件不存在";
				return false;
			}
			return true;
		}

		//根据文件名读取excel文件
		public List<List<String>> read(String filePath)
		{
			List<List<String>> dataLst = new ArrayList<List<String>>();
			InputStream is = null;
			try
			{
				/** 验证文件是否合法 */
				if (!validateExcel(filePath))
				{
					log.error("费率文件不合法",errorInfo);
					return null;
				}
				/** 判断文件的类型，是2003还是2007 */
				boolean isExcel2003 = true;
				if (WDWUtil.isExcel2007(filePath))
				{
					isExcel2003 = false;
				}
				/** 调用本类提供的根据流读取的方法 */
				File file = new File(filePath);
				is = new FileInputStream(file);
				dataLst = read(is, isExcel2003);
				is.close();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				if (is != null)
				{
					try
					{
						is.close();
					}
					catch (IOException e)
					{
						is = null;
						e.printStackTrace();
					}
				}
			}
			/** 返回最后读取的结果 */
			return dataLst;
		}

		//根据流读取Excel文件
		public List<List<String>> read(InputStream inputStream, boolean isExcel2003)
		{
			List<List<String>> dataLst = null;
			try
			{
				/** 根据版本选择创建Workbook的方式 */
				Workbook wb = null;
				if (isExcel2003)
				{
					wb = new HSSFWorkbook(inputStream);
				}
				else
				{
					wb = new XSSFWorkbook(inputStream);
				}
				dataLst = read(wb);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			return dataLst;
		}

		//读取数据
		private List<List<String>> read(Workbook wb)
		{
			List<List<String>> dataLst = new ArrayList<List<String>>();
			/** 得到第一个shell */
			Sheet sheet = wb.getSheetAt(0);
			/** 得到Excel的行数 */
			this.totalRows = sheet.getPhysicalNumberOfRows();
			/** 得到Excel的列数 */
			if (this.totalRows >= 1 && sheet.getRow(0) != null)
			{
				this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
			}
			/** 循环Excel的行 */
			for (int r = 0; r < this.totalRows; r++)
			{
				Row row = sheet.getRow(r);
				if (row == null)
				{
					continue;
				}
				String upCellValue="";
				List<String> rowLst = new ArrayList<String>();
				/** 循环Excel的列 */
				
				for (int c = 1; c < this.getTotalCells(); c++)
				{
					Cell cell = row.getCell(c);
					String cellValue = "";
					
					if (null != cell)
					{
						// 以下是判断数据的类型
						switch (cell.getCellType())
						{
						case HSSFCell.CELL_TYPE_NUMERIC: // 数字
							cellValue = cell.getNumericCellValue() + "";
							break;
						case HSSFCell.CELL_TYPE_STRING: // 字符串
							cellValue = cell.getStringCellValue();
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
							cellValue = cell.getBooleanCellValue() + "";
							break;
						case HSSFCell.CELL_TYPE_FORMULA: // 公式
							cellValue = cell.getCellFormula() + "";
							break;
						case HSSFCell.CELL_TYPE_BLANK: // 空值
							cellValue = "";
							break;
						case HSSFCell.CELL_TYPE_ERROR: // 故障
							cellValue = "非法字符";
							break;
						default:
							cellValue = "未知类型";
							break;
						}
					}
					if(cellValue == ""){
						rowLst.add(upCellValue);//如果等于空取上一个值
					}else{
						upCellValue=cellValue;
						rowLst.add(cellValue);
					}
				}
				/** 保存第r行的第c列 */
				dataLst.add(rowLst);
			}
			return dataLst;

		}

		
		public List<Map<String,Object>> getCellData(String fileName){
			
			List<List<String>> list=this.read(fileName);
			//解析数据
			int cellsize=list.get(0).size();
			String[][] arr = new String[list.size()][cellsize]; 
			for(int i=0;i<list.size();i++){
				for(int j=0;j<cellsize;j++){
					arr[i][j]=list.get(i).get(j);
				}
			}
			List<Map<String,Object>> celllist=new ArrayList<Map<String,Object>>();
			Map<String,Object> row=new HashMap<String,Object>();
			JSONArray fee=new JSONArray();
			for(int d=0;d<cellsize;d++){
				for(int b=0;b<arr.length;b++){
					if(b==0){
						row.put("insurancePeriod", arr[b][d]);
					}if(b==1){
						row.put("paymentPeriod", arr[b][d]);
					}if(b==2){
						row.put("sex", arr[b][d]);
					}if(b > 2 && b<arr.length){
						fee.add(arr[b][d]);
					}if(b > 2 && b == arr.length-1){
						row.put("feeArray", fee);
					}
				}
			  celllist.add(row);
		    }
			log.debug("解析结果:"+celllist.size()+"条记录");
			return celllist;
		}
		
		
		public static void main(String[] args) throws Exception
		{
			ExcelUtil poi = new ExcelUtil();
			poi.getCellData("f://123.xls");
		}
	}

	/**
	 * 
	 * @描述：工具类
	 * 
	 * @作者：建宁
	 * 
	 * @时间：2012-08-29 下午16:30:40
	 */

	class WDWUtil
	{
		public static boolean isExcel2003(String filePath)
		{
			return filePath.matches("^.+\\.(?i)(xls)$");
		}
		public static boolean isExcel2007(String filePath)
		{
			return filePath.matches("^.+\\.(?i)(xlsx)$");
		}
}

