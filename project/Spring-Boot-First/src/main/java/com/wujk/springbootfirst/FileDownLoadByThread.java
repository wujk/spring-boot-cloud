package com.wujk.springbootfirst;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 *
 * @author : wujingkai
 * @Project: FileDownLoadByThread
 * @Package: com.test
 * @Date: 2020/1/3 17:43
 * @Description: 多线程下载
 *
 */
public class FileDownLoadByThread {

    private final int SUCCESS = 200;

    private final int SUCCESS_SPLIT = 206;

    /*
     * 线程数
     */
    private int threadNum = 5;

    /*
     * 文件名称
     */
    private String fileName;

    /*
     * 网络路径
     */
    private String url;

    /*
     * 分段的文件列表属性
     */
    private List<Model> fileList = new ArrayList<Model>();

    /*
     * 线程池
     */
    private ExecutorService pool;

    /*
     * 是否断点下载
     */
    private boolean autoResume = false;

    /*
     * 开启断点下载后,每10秒保存一次下载位置
     */
    private Long saveTime = 10 * 1000L;

    /**
     * @Author: wujingkai
     * @Description: 通过url 获取文件名称
     * @Date: 2020/1/6 11:23
     * @Param url: 下载路径地址
     * @return: java.lang.String
     */
    public static String getFileNameFromUrl(String url) {
        String[] strs = url.split("/");
        String name = strs[strs.length - 1];
        strs = name.split("\\?");
        name = strs[0];
        return name;
    }


    /**
     *
     * @author : wujingkai
     * @Project: FileDownLoadByThread
     * @Package: com.chinamobile.edge.kcs.util
     * @Date: 2020/1/6 11:26
     * @Description: 每一段文件的属性
     *
     */
    class Model {
        public long startIndex;       // 开始位置
        public long endIndex;         // 结束位置
        public String tempFile;       // 临时文件
        public String file;           // 真实文件
        public boolean isLast = false;// 是否是最后一段
    }

    public FileDownLoadByThread(String url, String fileName) {
        this(url, false, fileName);
    }

    public FileDownLoadByThread(String url, boolean autoResume, String fileName) {
        this(url, autoResume, 5, fileName);
    }

    public FileDownLoadByThread(String url, int threadNum, String fileName) {
        this(url, false, threadNum, fileName);
    }

    public FileDownLoadByThread(String url, boolean autoResume, int threadNum, String fileName) {
        this.threadNum = threadNum;
        this.url = url;
        this.autoResume = autoResume;
        this.fileName = fileName;
        pool = Executors.newFixedThreadPool(threadNum);
    }

    /**
     * @Author: wujingkai
     * @Description: 计算文件大小
     * @Date: 2020/1/6 11:10
     * @return: long
     */
    private long fileSize() {
        HttpURLConnection con = null;
        try {
            URL _url = new URL(url);
            con = (HttpURLConnection) _url.openConnection();
            con.setRequestMethod("GET");
            int status = con.getResponseCode();
            if (SUCCESS == status) {
                long length = con.getContentLength();
                return length;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }

    /**
     * @Author: wujingkai
     * @Description: 计算每个线程下载数据量
     * @Date: 2020/1/6 11:10
     * @Param length:
     * @return: void
     */
    private void calculation(long length) {
        long each = length / threadNum;
        for (int i = 0; i < threadNum; i++) {
            Model model = new Model();
            model.startIndex = i * each;
            model.endIndex = (i + 1) * each - 1;
            if (i == threadNum - 1) {
                model.endIndex = length;
                model.isLast = true;
            }
            model.file = fileName;
            File file = new File(fileName);
            File parent = file.getParentFile();
            file = new File(parent, model.startIndex + "." + model.endIndex
                    + "" + i + ".tmp");
            model.tempFile = file.getAbsolutePath();
            fileList.add(model);
        }
    }

    /**
     * @Author: wujingkai
     * @Description: 读取临时文件
     * @Date: 2020/1/6 11:11
     * @return: void
     */
    private void readTempFile() {
        if (!autoResume) {
            return;
        }
        for (int i = 0; i < fileList.size(); i++) {
            BufferedReader reader = null;
            try {
                Model model = fileList.get(i);
                if (model.tempFile == null) {
                    return;
                }
                System.out.println("读取临时文件：" + model.tempFile);
                File file = new File(model.tempFile);
                if (!file.exists()) {
                    continue;
                }
                reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream(file)));
                String line = null;
                String result = "";
                while ((line = reader.readLine()) != null) {
                    result += line.trim();
                }
                if (result != null) {
                    model.startIndex = Integer.parseInt(result.trim());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if ( reader != null) {
                    try {
                        reader.close();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * @Author: wujingkai
     * @Description: 保存临时文件
     * @Date: 2020/1/6 11:27
     * @Param file: 文件名称
     * @Param lentgh: 文件下载的文职
     * @return: void
     */
    private void saveTempFile(File file, String lentgh) {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file, "rwd");
            raf.write(lentgh.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (raf != null) {
                    raf.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean download() {
        boolean isOver = true;
        try {
            RandomAccessFile file = new RandomAccessFile(new File(fileName), "rw");
            long length = fileSize();  // 获取文件大小
            file.setLength(length);
            file.close();
            calculation(length);     // 计算多线程
            readTempFile();    // 读取临时文件
            List<Future<Boolean>> futures = new ArrayList<>();
            for (int i = 0; i < fileList.size(); i++) {
                Future<Boolean> future = pool.submit(new DownLoadThread(fileList.get(i)));
                futures.add(future);
            }
            pool.shutdown();
            for (Future<Boolean> future : futures) {
                if (!future.get()) {
                    isOver = false;
                    break;
                }
            }
            if (isOver) {
                System.out.println("download is over");
                // 删除临时文件
                deleteTempFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isOver;
    }

    public void deleteTempFile() {
        Iterator<Model> iterable = fileList.iterator();
        while (iterable.hasNext()) {
            Model model = iterable.next();
            File file = new File(model.tempFile);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * 下载线程
     * @author eeesys
     *
     */
    class DownLoadThread implements Callable<Boolean> {

        private Model model;

        public DownLoadThread(Model model) {
            this.model = model;
        }

        @Override
        public Boolean call() {
            HttpURLConnection con = null;
            RandomAccessFile file = null;
            InputStream is = null;
            Boolean result = false;
            try {
                URL _url = new URL(url);
                con = (HttpURLConnection) _url.openConnection();
                con.setRequestMethod("GET");
                if (model.isLast) {
                    con.addRequestProperty("Range", "bytes=" + model.startIndex + "-");
                } else {
                    con.addRequestProperty("Range", "bytes=" + model.startIndex + "-" + model.endIndex);
                }
                int status = con.getResponseCode();
                if (SUCCESS_SPLIT == status || SUCCESS == status) {
                    System.out.println(Thread.currentThread().getName() + "开始下载");
                    is = con.getInputStream();
                    file = new RandomAccessFile(new File(fileName), "rwd");
                    file.seek(model.startIndex);
                    int len = 0;
                    byte[] buf = new byte[1024 * 10];
                    int count = 0;
                    Long startTime = System.currentTimeMillis();
                    while((len = is.read(buf)) != -1) {
                        file.write(buf, 0, len);
                        count += len;
                        if (autoResume && model.tempFile != null) {
                            Long endTime = System.currentTimeMillis();
                            if (endTime - startTime > saveTime) {
                                saveTempFile(new File(model.tempFile), model.startIndex + count + "");
                                startTime = endTime;
                            }
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + "结束下载");
                    result = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                    if (file != null) {
                        file.close();
                    }
                    if (con != null) {
                        con.disconnect();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        new FileDownLoadByThread("http://localhost:8080/download", true, "D:/".concat("ideaIU-2019.3.exe")).download();
    }

}
