/** 
* @公司名称: xxxxxxxxxxxx
* @版权信息: 版权归属15202125130@163.com
* @包          名: com.lifeinsurancesystem.util.CommonUtil
* @描          述: 工具类
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年4月10日 下午9:54:38 
* @修改日期: 2016年4月10日 下午9:54:38
* @版权序号: V0.0.1
*/
package com.lifeinsurancesystem.util;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import com.sun.imageio.plugins.jpeg.JPEGImageWriter;
/**
* @类          名: CompressPic 
* @描          述: TODO 图片压缩
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年5月24日 上午11:36:25 
* @修改日期: 2016年5月24日 上午11:36:25
 */
public class CompressPic {
	  private File file = null; // 文件对象 
      private String inputDir; // 输入图路径
      private String outputDir; // 输出图路径
      private String inputFileName; // 输入图文件名
      private String outputFileName; // 输出图文件名
      private int outputWidth = 100; // 默认输出图片宽
      private int outputHeight = 100; // 默认输出图片高
      private boolean proportion = true; // 是否等比缩放标记(默认为等比缩放)
      public CompressPic() { // 初始化变量
              inputDir = ""; 
              outputDir = ""; 
              inputFileName = ""; 
              outputFileName = ""; 
              outputWidth = 100; 
              outputHeight = 100; 
      } 
      public void setInputDir(String inputDir) { 
              this.inputDir = inputDir; 
      } 
      public void setOutputDir(String outputDir) { 
              this.outputDir = outputDir; 
      } 
      public void setInputFileName(String inputFileName) { 
              this.inputFileName = inputFileName;
      } 
      public void setOutputFileName(String outputFileName) { 
              this.outputFileName = outputFileName; 
      } 
      public void setOutputWidth(int outputWidth) {
              this.outputWidth = outputWidth; 
      } 
      public void setOutputHeight(int outputHeight) { 
              this.outputHeight = outputHeight; 
      } 
      public void setWidthAndHeight(int width, int height) { 
              this.outputWidth = width;
              this.outputHeight = height; 
      } 
      /* 
       * 获得图片大小 
       * 传入参数 String path ：图片路径 
       */ 
      public long getPicSize(String path) { 
              file = new File(path); 
              return file.length(); 
      }
      // 图片处理 
      public String compressPic() { 
              try { 
                      //获得源文件 
                      file = new File(inputDir + inputFileName); 
                      FileInputStream file01=new FileInputStream(inputDir + inputFileName);
                      if (!file.exists()) { 
                              return ""; 
                      } 
//                      Image img = ImageIO.read(file); 
                      Image img = ImageIO.read(file01);
                      // 判断图片格式是否正确 
                      if (img.getWidth(null) == -1) {
                              System.out.println(" can't read,retry!" + "<BR>"); 
                              return "no"; 
                      } else { 
                              int newWidth; int newHeight; 
                              // 判断是否是等比缩放 
                              if (this.proportion == true) { 
                                      // 为等比缩放计算输出的图片宽度及高度 
                                      double rate1 = ((double) img.getWidth(null)) / (double) outputWidth + 0.1; 
                                      double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.1; 
                                      // 根据缩放比率大的进行缩放控制 
                                      double rate = rate1 > rate2 ? rate1 : rate2; 
                                      newWidth = (int) (((double) img.getWidth(null)) / rate); 
                                      newHeight = (int) (((double) img.getHeight(null)) / rate); 
                              } else { 
                                      newWidth = img.getWidth(null); // 输出的图片宽度 
                                      newHeight = img.getHeight(null); // 输出的图片高度 
                              } 
                              BufferedImage tag1 = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB); 
                              //解决图片背景变黑问题
                              int type = tag1.getType() == 0 ? BufferedImage.TRANSLUCENT
                                      : tag1.getType();
                              BufferedImage tag = null;
                              if (tag1.isAlphaPremultiplied()) {
                            	  tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TRANSLUCENT);
                              } else {
                            	  tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);
                              }
                              /*
                              * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的
                              * 优先级比速度高 生成的图片质量比较好 但速度慢
                              */ 
                              tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0,Color.white, null);
                              FileOutputStream out = new FileOutputStream(outputDir + outputFileName);
                              int dpi = 300;//分辨率
                              saveAsJPEG(dpi, tag, 0.7f, out);
                              // JPEGImageEncoder可适用于其他图片类型的转换 
//                              JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out); 
//                              encoder.encode(tag); 
                              out.close(); 
                      } 
              } catch (IOException ex) { 
                      ex.printStackTrace(); 
              } 
              return "ok"; 
     } 
      /**
       * 以JPEG编码保存图片
       * 
       * @param dpi
       *            分辨率
       * @param image_to_save
       *            要处理的图像图片
       * @param JPEGcompression
       *            压缩比
       * @param fos
       *            文件输出流
       * @throws IOException
       */
      public static void saveAsJPEG(Integer dpi, BufferedImage image_to_save,
              float JPEGcompression, FileOutputStream fos) throws IOException {
    	  JPEGImageWriter imageWriter = (JPEGImageWriter) ImageIO
                  .getImageWritersBySuffix("jpg").next();
          ImageOutputStream ios = ImageIO.createImageOutputStream(fos);
          imageWriter.setOutput(ios);
          // and metadata
          IIOMetadata imageMetaData = imageWriter.getDefaultImageMetadata(
                  new ImageTypeSpecifier(image_to_save), null);
          if (JPEGcompression >= 0 && JPEGcompression <= 1f) {
              JPEGImageWriteParam jpegParams = (JPEGImageWriteParam) imageWriter
                      .getDefaultWriteParam();
              jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
              jpegParams.setCompressionQuality(JPEGcompression);
   
          }
          imageWriter.write(imageMetaData,
                  new IIOImage(image_to_save, null, null), null);
          ios.close();
          imageWriter.dispose();
   
      }
      public String compressPic (String inputDir, String outputDir, String inputFileName, String outputFileName) { 
              // 输入图路径 
              this.inputDir = inputDir; 
              // 输出图路径 
              this.outputDir = outputDir; 
              // 输入图文件名 
              this.inputFileName = inputFileName; 
              // 输出图文件名
              this.outputFileName = outputFileName; 
              return compressPic(); 
      } 
      public String compressPic(String inputDir, String outputDir, String inputFileName, String outputFileName, int width, int height, boolean gp) { 
              // 输入图路径 
              this.inputDir = inputDir; 
              // 输出图路径 
              this.outputDir = outputDir; 
              // 输入图文件名 
              this.inputFileName = inputFileName; 
              // 输出图文件名 
              this.outputFileName = outputFileName; 
              // 设置图片长宽
              setWidthAndHeight(width, height); 
              // 是否是等比缩放 标记 
              this.proportion = gp; 
              return compressPic(); 
      } 

      // main测试 
      // compressPic(大图片路径,生成小图片路径,大图片文件名,生成小图片文名,生成小图片宽度,生成小图片高度,是否等比缩放(默认为true))
      public static void main(String[] arg) throws IOException, IOException { 
              CompressPic mypic = new CompressPic(); 
             mypic.compressPic("d:\\", "d:\\", "ff.jpg", "r1.jpg", 100, 100, true); 

      } 
      public  void mianMethod(String url,String tempFile,String path ) throws IOException, IOException{
		// TODO Auto-generated method stub
     CompressPic mypic = new CompressPic(); 
      mypic.compressPic("d:\\", "d:\\", "ff.jpg", "r1.jpg", 400, 300, true); 
	}
}
