package com.baoge.springboot.untils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;


/**
 * 图片相关处理工具类
 *
 */
public class ImageUtils {


	private static String DIR = "images.dir.path";

	/**
	 *
	 * @param srcPath：图片磁盘路径，加上文件名
	 * @param destPath：输出图片的磁盘路径以及文件名
	 * @param ratio：宽高压缩比例，0-1直接的浮点数
	 * @return
	 */
	public static boolean scale(String srcPath,String destPath,Float ratio) {
		try {
			String mkdir = destPath.substring(0, destPath.lastIndexOf("/"));
			File file =new File(mkdir);
			// 如果文件夹不存在则创建
			if(!file.exists()  && !file .isDirectory()) {
			    file .mkdir();
			}
			File src = new File(srcPath);
			File dest = new File(destPath);
			BufferedImage apples = ImageIO.read(src);
			String suffix = destPath.substring(destPath.lastIndexOf(".") + 1);
			ImageIO.write(apples, suffix, dest);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static String upload(MultipartFile file) {
		String datePath = DateUtils.formatDate(DateUtils.long_filepatten, new Date());
		String fileName = datePath + RandomUtils.nextInt(1000000) + StrKit.getFileSuffix(file.getOriginalFilename());
		return upload(file,fileName);
	}
	public static String upload(MultipartFile file, String fileName) {
		String name = null;
		try {
			String path = DIR ;
			File targetFile = new File(path);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path + "/" + fileName));
//			name = Utils.uploadQiniu(fileName,path + "/"); TODO
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return name;
	}
}
