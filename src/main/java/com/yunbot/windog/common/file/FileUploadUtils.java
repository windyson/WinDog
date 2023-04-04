package com.yunbot.windog.common.file;

import java.io.File;
import java.io.IOException;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;
import com.yunbot.windog.common.conf.FileConfig;
import com.yunbot.windog.common.conf.V2Config;
import com.yunbot.windog.common.exception.file.FileNameLengthLimitExceededException;
import com.yunbot.windog.util.StringUtils;

/**
 * 文件上传工具类
 * @author fuce 
 * @date: 2018年9月22日 下午10:33:23
 */
public class FileUploadUtils {

    private FileUploadUtils(){}
    
    /**
     * spring.servlet.multipart.maxFileSize
     */
    public static  long DEFAULT_MAX_SIZE=Long.valueOf(FileConfig.getMaxFileSize())*1024*1024;
    /**
     * 默认上传的地址
     */
    private static String defaultBaseDir = V2Config.getDefaultBaseDir();
    
    /**
     * 是否上传到static
     */
    private static String isstatic=V2Config.getIsstatic();
    
    /**
     * 默认的文件名最大长度
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;
    /**
     * 默认文件类型jpg
     */
    public static final String IMAGE_JPG_EXTENSION = ".jpg";

    private static int counter = 0;

    public static void setDefaultBaseDir(String defaultBaseDir)
    {
        FileUploadUtils.defaultBaseDir = defaultBaseDir;
    }

    public static String getDefaultBaseDir()
    {
        return defaultBaseDir;
    }

    public static String getIsstatic() {
		return isstatic;
	}

	public static void setIsstatic(String isstatic) {
		FileUploadUtils.isstatic = isstatic;
	}
	
	/**
	 * 静态文件上传后存放的目录
	 */
	public static String getRoot_dir() {
		String url=ClassUtils.getDefaultClassLoader().getResource("").getPath()+V2Config.getIsroot_dir();
		return url;
	}


	/**
     * 以默认配置进行文件上传
     *
     * @param file 上传的文件
     * @return 文件名称
     * @throws Exception
     */
    public static final String upload(MultipartFile file) throws IOException
    {
        try
        {
        	if("Y".equals(getIsstatic())) {//获取根目录
        		
        		 return upload(getRoot_dir(), file);
        	}else {//自定义目录
        		 return upload(getDefaultBaseDir(), file);
        	}
        }
        catch (Exception e)
        {
            throw new IOException(e);
        }
    }

    /**
     * 文件上传
     *
     * @param baseDir 相对应用的基目录
     * @param file 上传的文件
     * @param needDatePathAndRandomName 是否需要日期目录和随机文件名前缀
     * @param extension 上传文件类型
     * @return 返回上传成功的文件名
     * @throws FileSizeLimitExceededException 如果超出最大大小
     * @throws FileNameLengthLimitExceededException 文件名太长
     * @throws IOException 比如读写文件出错时
     */
    public static final String upload(String baseDir, MultipartFile file)
            throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException
    {
    	String fileName=file.getOriginalFilename();
    	// 获得文件后缀名称
    	String suffixName = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
    	if(StringUtils.isEmpty(suffixName)) {
    		//如果没有后缀默认后缀
    		suffixName=FileUploadUtils.IMAGE_JPG_EXTENSION;
    	}
    	
        int fileNamelength = fileName.length();
        if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH)
        {
            throw new FileNameLengthLimitExceededException(fileName, fileNamelength,
                    FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }

        assertAllowed(file);

        String new_fileName = encodingFilename(fileName, suffixName);

        File desc = getAbsoluteFile(baseDir, baseDir + new_fileName);
        file.transferTo(desc);
        return new_fileName;
    }

    private static final File getAbsoluteFile(String uploadDir, String filename) throws IOException
    {
        File desc = new File(File.separator + filename);

        if (!desc.getParentFile().exists())
        {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists())
        {
            desc.createNewFile();
        }
        return desc;
    }

    /**
     * 编码文件名
     */
    private static final String encodingFilename(String filename, String extension)
    {
        filename = filename.replace("_", " ");
        filename = new Md5Hash(filename + System.nanoTime() + counter++).toHex().toString() + extension;
        return filename;
    }

    /**
     * 文件大小校验
     *
     * @param file 上传的文件
     * @return
     * @throws FileSizeLimitExceededException 如果超出最大大小
     */
    public static final void assertAllowed(MultipartFile file) throws FileSizeLimitExceededException
    {
        long size = file.getSize();
        if (DEFAULT_MAX_SIZE != -1 && size > DEFAULT_MAX_SIZE)
        {
            throw new FileSizeLimitExceededException("超过默认大小", size, DEFAULT_MAX_SIZE);
        }
    }

}
