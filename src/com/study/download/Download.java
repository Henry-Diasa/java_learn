package com.study.download;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Download {
    private Integer threadNum = 10;

    public void download(String source, String targetDir) {
        InputStream is = null;
        OutputStream os = null;
        try {
            String fileName = source.substring(source.lastIndexOf('/') + 1);
            File targetFile = new File(targetDir + '/' + fileName);
            if (!targetFile.exists()) {
                targetFile.createNewFile();
            }

            URL url = new URL(source);
            URLConnection connection = url.openConnection();
            is = connection.getInputStream();
            os = new FileOutputStream(targetFile);

            byte[] bs = new byte[1024];
            int len = 0;
            while((len = is.read(bs))!=-1) {
                os.write(bs, 0, len);
            }
            System.out.println("[INFO]图片下载完毕:" + source + "\n\t  ->" + targetFile.getPath() + "(" + Math.floor(targetFile.length() / 1024) + "kb)");
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (os != null) {
                    os.close();
                }
                if(is!=null) {
                    is.close();
                }
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void multiDownloadFromFile(String targetDir, String downloadTxt) {
        File dir = new File(targetDir);
        if(!dir.exists()){
            dir.mkdirs();
            System.out.println("[INFO]发现下载目录[" + dir.getPath() + "]不存在,已自动创建");
        }

        List<String> resources = new ArrayList<>();
        BufferedReader reader = null;
        ExecutorService threadPool = null;
        try {
            reader = new BufferedReader(new FileReader(downloadTxt));
            String line = null;
            while((line = reader.readLine())!=null) {
                resources.add(line);
            }

            threadPool = Executors.newFixedThreadPool(this.threadNum);
            Download that = this;
            for(String res: resources) {
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        that.download(res, targetDir);
                    }
                });
            }
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            if(threadPool!=null) {
                threadPool.shutdown();
            }

            if(reader!=null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void start(String propDir) {
        File propFile = new File(propDir + "/config.properties");

        Properties properties = new Properties();
        Reader reader = null;

        try{
            reader = new FileReader(propFile);
            properties.load(reader);

            String threadNum = properties.getProperty("thread-num");
            this.threadNum = Integer.parseInt(threadNum);
            String targetDir = properties.getProperty("target-dir");


            this.multiDownloadFromFile(targetDir, propDir+"/download.txt");

        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            if(reader != null){
                try {
                    //关闭流
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Download download = new Download();
        download.start("/Users/fuqiang1/IdeaProjects/java_study/src");
    }


}
