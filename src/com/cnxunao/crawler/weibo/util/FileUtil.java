package com.cnxunao.crawler.weibo.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public final class FileUtil {

	/**
	 * 文件夹下所有文件
	 * 
	 * @param includeSubDirectories
	 *            包含子目录
	 */
	public static List<File> listFiles(File directory,
			boolean includeSubDirectories) {
		List<File> files = new ArrayList<File>();
		innerListFiles(files, directory, includeSubDirectories);
		return files;
	}

	private static void innerListFiles(List<File> files, File directory,
			boolean includeSubDirectories) {
		File[] found = directory.listFiles();
		if (found != null)
			for (File file : found) {
				if (file.isDirectory()) {
					innerListFiles(files, file, includeSubDirectories);
					if (includeSubDirectories)
						files.add(file);
				} else {
					files.add(file);
				}
			}
	}

	/**
	 * 文件是否存在
	 */
	public static boolean exists(String filename) {
		return new File(filename).exists();
	}

	/**
	 * 删除文件
	 */
	public static boolean deleteFile(String filename) {
		return new File(filename).delete();
	}

	/**
	 * 删除目录
	 */
	public static void deleteDirectory(File dir) {
		if (!dir.exists())
			return;
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (File file : files)
				deleteDirectory(file);
		}
		dir.delete();
	}

	/**
	 * 清除目录下文件
	 * 
	 * @param keepSubDirectories
	 *            保留子目录
	 */
	public static void cleanDirectory(File dir, boolean keepSubDirectories) {
		if (!dir.exists() || !dir.isDirectory())
			return;
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				cleanDirectory(file, keepSubDirectories);
				if (!keepSubDirectories)
					file.delete();
			} else {
				file.delete();
			}
		}
	}

	/**
	 * 读文件
	 */
	public static String read(String filename)throws Exception
	{
		return read(new File(filename));
	}
	public static String read(File file) {
		BufferedReader bufr = null;
		StringBuffer buffer = new StringBuffer();
		try {
			bufr = new BufferedReader(new FileReader(file));
			String line;
			while ((line = bufr.readLine()) != null)
				buffer.append(line).append("\n");
		} catch (IOException e) {

		} finally {
			try {
				if (bufr != null)
					bufr.close();
			} catch (IOException e) {

			}
		}
		return buffer.toString();
	}

	/**
	 * 读文件
	 */
	public static byte[] readToByte(File file) throws IOException {
		long length = file.length();
		if (length > Integer.MAX_VALUE)
			throw new IOException("file too large");
		byte[] b = null;
		InputStream in = null;
		try {
			b = new byte[(int) length];
			in = new FileInputStream(file);
			int offset = 0, nRead = 0;
			while (offset < b.length
					&& (nRead = in.read(b, offset, b.length - offset)) != -1)
				offset += nRead;
		} catch (Exception e) {

		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {

			}
		}
		return b;
	}

	/**
	 * 写文件
	 * 
	 * @param replaceOnExists
	 *            文件存在时，true：替换、false：末尾处追加内容
	 */
	public static void write(String text, File file, boolean replaceOnExists) {
		FileWriter writer = null;
		try {
			/* 替换 */
			if (replaceOnExists) {
				writer = new FileWriter(file);
			}
			/* 末尾处追加内容，并添加修改时间 */
			else {
				writer = new FileWriter(file, true);
			}
			writer.write(text);
		} catch (IOException e) {

		} finally {
			try {
				if (writer != null) {
					writer.flush();
					writer.close();
				}
			} catch (IOException e) {

			}
		}
	}

	/**
	 * zip 压缩
	 * 
	 * @param deleteAfterPacking
	 *            压缩后删除原文件
	 */
	public static void zip(List<File> files, File zipFile,
			boolean deleteAfterPacking) {
		ZipOutputStream zipOut = null;
		try {
			zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
			byte[] bytes = new byte[1024 * 10];
			for (File file : files) {
				if (!file.exists())
					continue;
				ZipEntry entry = new ZipEntry(file.getName());
				zipOut.putNextEntry(entry);
				InputStream in = null;
				try {
					in = new FileInputStream(file);
					int len;
					while ((len = in.read(bytes)) != -1)
						zipOut.write(bytes, 0, len);
				} finally {
					try {
						if (in != null)
							in.close();
						if (deleteAfterPacking)
							file.delete();
					} catch (Exception e) {

					}
				}
			}
		} catch (Exception e) {

		} finally {
			try {
				if (zipOut != null) {
					zipOut.finish();
					zipOut.flush();
					zipOut.close();
				}
			} catch (Exception e) {

			}
		}
	}

	/**
	 * 文件或文件夹压缩
	 * 
	 * @param deleteAfterPacking
	 *            压缩后删除原文件
	 */
	public static void zipFile(File file, File zipFile,
			boolean deleteAfterPacking) {
		ZipOutputStream zipOut = null;
		try {
			zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
			// 从顶层开始打包
			zipFile(zipOut, file, "");
		} catch (IOException e) {

		} finally {
			try {
				if (zipOut != null) {
					zipOut.finish();
					zipOut.flush();
					zipOut.close();
				}
				if (deleteAfterPacking)
					deleteDirectory(file);
			} catch (IOException e) {

			}
		}
	}

	/**
	 * 递归打包
	 * 
	 * @param basePath
	 *            文件压缩根目录
	 */
	private static void zipFile(ZipOutputStream out, File file, String basePath) {
		// 压缩文件或文件夹
		String filename = basePath
				+ (basePath.length() == 0 ? "" : File.separator)
				+ file.getName();
		/* 目录 */
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files)
				zipFile(out, f, filename);
		}
		/* 文件 */
		else {
			InputStream in = null;
			try {
				out.putNextEntry(new ZipEntry(filename));
				in = new FileInputStream(file);
				byte[] b = new byte[1024];
				int len;
				while ((len = in.read(b)) != -1)
					out.write(b, 0, len);
			} catch (IOException e) {

			} finally {
				try {
					if (in != null)
						in.close();
				} catch (IOException e) {

				}
			}
		}
	}

	/**
	 * zip 解压
	 * 
	 * @param filepath
	 *            解压后文件存放路径
	 * @param deleteAfterUnpacking
	 *            解压后删除原文件
	 */
	public static void unzipFile(String filepath, File zipFile,
			boolean deleteAfterUnpacking) {
		ZipInputStream zipIn = null;
		try {
			zipIn = new ZipInputStream(new FileInputStream(zipFile));
			ZipEntry zEntry;
			while ((zEntry = zipIn.getNextEntry()) != null) {
				// 解压后绝对路径
				String filename = filepath + File.separator + zEntry.getName();
				/* 目录 */
				if (zEntry.isDirectory()) {
					new File(filename).mkdir();
				}
				/* 文件 */
				else {
					OutputStream out = null;
					try {
						out = new FileOutputStream(filename);
						byte[] b = new byte[1024];
						int len;
						while ((len = zipIn.read(b)) != -1) {
							out.write(b, 0, len);
							out.flush();
						}
					} catch (IOException e) {

					} finally {
						try {
							if (out != null)
								out.close();
						} catch (IOException e) {

						}
					}
				}
			}
		} catch (IOException e) {

		} finally {
			try {
				if (zipIn != null) {
					zipIn.closeEntry();
					zipIn.close();
				}
				if (deleteAfterUnpacking)
					zipFile.delete();
			} catch (IOException e) {

			}
		}
	}

	/**
	 * gzip 压缩数据 <br>
	 * string to byte array
	 */
	public static byte[] gzipData(String v, String charset) {
		if (v == null || v.length() == 0)
			return null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzipOut = null;
		try {
			gzipOut = new GZIPOutputStream(out);
			gzipOut.write(v.getBytes(charset));
		} catch (Exception e) {

		} finally {
			try {
				out.close();
				if (gzipOut != null) {
					gzipOut.finish();
					gzipOut.flush();
					gzipOut.close();
				}
			} catch (IOException e) {

			}
		}
		return out.toByteArray();
	}

	/**
	 * gzip 解压数据 <br>
	 * byte array to string
	 */
	public static String ungzipData(byte[] buffer, String charset)
			throws UnsupportedEncodingException {
		if (buffer == null || buffer.length == 0)
			return null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPInputStream gzipIn = null;
		try {
			gzipIn = new GZIPInputStream(new ByteArrayInputStream(buffer));
			byte[] b = new byte[1024];
			int len;
			while ((len = gzipIn.read(b)) != -1)
				out.write(b, 0, len);
		} catch (IOException e) {

		} finally {
			try {
				out.close();
				if (gzipIn != null)
					gzipIn.close();
			} catch (IOException e) {

			}
		}
		return out.toString(charset);
	}

	/**
	 * gzip 压缩文件
	 * 
	 * @param deleteAfterPacking
	 *            压缩后删除原文件
	 */
	public static void gzipFile(File file, File gzipFile,
			boolean deleteAfterPacking) {
		InputStream in = null;
		GZIPOutputStream gzipOut = null;
		try {
			in = new FileInputStream(file);
			gzipOut = new GZIPOutputStream(new FileOutputStream(gzipFile));
			byte[] b = new byte[1024];
			int len;
			while ((len = in.read(b)) != -1)
				gzipOut.write(b, 0, len);
		} catch (IOException e) {

		} finally {
			try {
				if (in != null)
					in.close();
				if (gzipOut != null) {
					gzipOut.finish();
					gzipOut.flush();
					gzipOut.close();
				}
				if (deleteAfterPacking)
					file.delete();
			} catch (IOException e) {

			}
		}
	}

	/**
	 * gzip 解压文件
	 * 
	 * @param deleteAfterUnpacking
	 *            解压后删除原文件
	 */
	public static void ungzipFile(File file, File gzipFile,
			boolean deleteAfterUnpacking) {
		GZIPInputStream gzipIn = null;
		OutputStream out = null;
		try {
			gzipIn = new GZIPInputStream(new FileInputStream(gzipFile));
			out = new FileOutputStream(file);
			byte[] b = new byte[1024];
			int len;
			while ((len = gzipIn.read(b)) != -1)
				out.write(b, 0, len);
		} catch (IOException e) {

		} finally {
			try {
				if (gzipIn != null)
					gzipIn.close();
				if (out != null) {
					out.flush();
					out.close();
				}
				if (deleteAfterUnpacking)
					gzipFile.delete();
			} catch (IOException e) {

			}
		}
	}
	
	/**
	 * 打包
	 * 
	 * @param xmlFilePath
	 *            临时文件夹，存放xml文件
	 * @param zipFilePath
	 *            压缩包存放路径
	 * @throws IOException 
	 */
	public static void saveToZip(String xmlFilePath, String zipFilePath) throws IOException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		zipFilePath += dateFormat.format(new Date()) + "\\";
		File file = new File(zipFilePath);
		if (!file.exists())
			//file.createNewFile();
			file.mkdirs();
		/*file = new File(xmlFilePath);
		
		if (!file.exists())
			file.mkdirs();
		File tempDir = new File(xmlFilePath);
		if (!tempDir.exists() || tempDir.isDirectory())
			return;
		File[] xmlFiles = tempDir.listFiles();
		if (xmlFiles == null || xmlFiles.length < 10)
			return;
		*/
		ZipOutputStream zos = null;
		File zipFile = null;
		while (zipFile == null || zipFile.exists()) {
			zipFile = new File(zipFilePath + System.currentTimeMillis() + ".zip");
		}
		try {
			zos = new ZipOutputStream(new FileOutputStream(zipFile));
			/*for (int i = 0; i < xmlFiles.length; i++) {
				if (i == 10)
					break;
				File xmlFile = xmlFiles[i];
				String fileName = xmlFile.getName();
				if (!fileName.endsWith(".xml"))
					continue;
				byte[] bytes = new byte[(int) xmlFile.length()];
				InputStream is = new FileInputStream(xmlFile);
				is.read(bytes);
				zos.putNextEntry(new ZipEntry(i + ".xml"));
				zos.write(bytes);
				is.close();
				xmlFile.delete();
			}*/
		} catch (Exception e) {
			
		} finally {
			try {
				zos.close();
			} catch (Exception e) {

			}
		}
	}

}
